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

public class VariationsTask extends CommonTask {
    public VariationsTask(RecursiveFile ...directories) {
        super(directories);
    }

    @Override
    protected void run(File file) {
        File overridesFile = new File(file, "overrides.yml");

        if (overridesFile.exists()) {
            YamlFile overrides = loadYaml(overridesFile);

            for (Map<?, ?> override : overrides.getMapList("overrides")) {
                Map<String, Object> parsed = MapUtil.toStandard(override);

                // Load the source
                File sourceFile = ValidFiles.valueOf(override.get("source").toString()).getFile();
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
                variation.set("variation", overrides.getString("name"));
                variation.setComment("variation", "Variation type utilized", CommentType.SIDE);

                Map<String, Object> flat = MapUtil.flatten(parsed);

                // Set each key in the variation from overrides
                for (Map.Entry<String, Object> entry : flat.entrySet()) {
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

            OmniLogger.info("Variations for " + file.getName() + " have been applied successfully!");
        }
    }

    /**
     * Check if a key is valid for override transfers.
     * @param key Key to check
     * @return Whether the key is valid
     */
    private boolean isValid(String key) {
         return !Util.isEqualSome(key, "source");
    }
}
