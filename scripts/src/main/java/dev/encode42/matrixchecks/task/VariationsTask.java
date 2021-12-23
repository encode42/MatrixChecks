package dev.encode42.matrixchecks.task;

import dev.encode42.copper.io.RecursiveFile;
import dev.encode42.copper.logger.OmniLogger;
import dev.encode42.copper.util.MapUtil;
import dev.encode42.copper.util.Util;
import dev.encode42.matrixchecks.util.ValidFiles;
import org.simpleyaml.configuration.comments.CommentType;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VariationsTask extends CommonTask {
    public VariationsTask(RecursiveFile ...directories) {
        super(directories);
    }

    @Override
    protected void run(File file) {
        File overridesFile = new File(file, "overrides.yml");

        if (!file.isDirectory() || !overridesFile.exists()) {
            return;
        }

        // Get the overrides of the file
        List<Map<String, Object>> parsed = getOverrides(overridesFile);
        List<Map<String, Object>> allOverrides = new ArrayList<>();

        for (Map<String, Object> override : parsed) {
            // Load the source
            ValidFiles from = ValidFiles.valueOf(override.get("source").toString());

            // Process combos
            if (from == ValidFiles.COMBO) {
                List<Map<String, Object>> comboOverrides = new ArrayList<>();

                if (override.get("from") instanceof List list) {
                    for (Object comboPath : list) {
                        // Get the overrides for the file
                        File comboFile = new File(file, String.valueOf(comboPath));

                        // todo: dupe code
                        File comboOverridesFile = new File(comboFile, "overrides.yml");

                        if (!file.isDirectory() || !overridesFile.exists()) {
                            return;
                        }

                        // Add the overrides to the combos
                        comboOverrides.addAll(getOverrides(comboOverridesFile, String.valueOf(override.get("name"))));
                    }
                }

                // Ensure all file combos are combined
                for (ValidFiles validFile : ValidFiles.values()) {
                    if (!validFile.name().equals("COMBO")) {
                        Set<Map<String, Object>> combos = new HashSet<>();

                        // Add all combos for file type
                        for (Map<String, Object> combo : comboOverrides) {
                            if (validFile.name().equals(combo.get("source"))) {
                                combos.add(combo);
                            }
                        }

                        // Merge the maps
                        allOverrides.add(MapUtil.merge(combos));
                    }
                }
            } else {
                allOverrides.add(override);
            }
        }

        // Process all the overrides
        for (Map<String, Object> override : allOverrides) {
            File sourceFile = ValidFiles.valueOf(override.get("source").toString()).getFile();
            processOverride(file, sourceFile, override);
        }

        OmniLogger.info("Variations for " + file.getName() + " have been applied successfully!");

    }

    /**
     * Get the overrides for a file.
     * @param file File to get
     * @param name Name to override
     * @return Overrides for the file
     */
    private List<Map<String, Object>> getOverrides(File file, String name) {
        YamlFile overrides = loadYaml(file);
        List<Map<String, Object>> parsed = new ArrayList<>();

        // Parse the overrides
        for (Map<?, ?> map : overrides.getMapList("overrides")) {
            Map<String, Object> override = MapUtil.toStandard(map);

            // Override the name
            if (name != null) {
                override.put("name", name);
            }

            // Flatten and add
            parsed.add(MapUtil.flatten(override));
        }

        return parsed;
    }

    /**
     * Get the overrides for a file.
     * @param file File to get
     * @return Overrides for the file
     */
    private List<Map<String, Object>> getOverrides(File file) {
        return getOverrides(file, null);
    }

    /**
     * Process the overrides.
     * @param file Parent file to process from
     * @param sourceFile Source file to copy from
     * @param override Overrides to process
     */
    private void processOverride(File file, File sourceFile, Map<String, Object> override) {
        YamlFile source = loadYaml(sourceFile);

        // Copy the source
        File variationFile = new File(file, sourceFile.getName());

        try {
            source.copyTo(variationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the variation
        YamlFile variation = loadYaml(variationFile);
        variation.set("variation", override.get("name"));
        variation.setComment("variation", "Variation type utilized", CommentType.SIDE);

        // Set each key in the variation from overrides
        for (Map.Entry<String, Object> entry : override.entrySet()) {
            if (isValid(entry.getKey())) {
                variation.set(entry.getKey(), entry.getValue());
            }
        }

        // Save the variation
        try {
            variation.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if a key is valid for override transfers.
     * @param key Key to check
     * @return Whether the key is valid
     */
    private boolean isValid(String key) {
         return !Util.isEqualSome(key, "name", "source");
    }

    @Override
    protected void finish() {
        OmniLogger.info("Finished variation of files.");
    }
}
