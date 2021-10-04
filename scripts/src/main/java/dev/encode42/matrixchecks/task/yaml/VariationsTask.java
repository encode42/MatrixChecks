package dev.encode42.matrixchecks.task.yaml;

import dev.encode42.matrixchecks.util.RecursiveFile;
import dev.encode42.matrixchecks.util.ValidFiles;
import org.simpleyaml.configuration.MemorySection;
import org.simpleyaml.configuration.comments.CommentType;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class VariationsTask extends CommonTask {
    public VariationsTask(RecursiveFile...directories) {
        super(directories);
    }

    @Override
    protected void run(File file) throws IOException {
        List<YamlFile> files = getFiles(file);

        if (!files.isEmpty()) {
            if (files.size() < 2) {
                return;
            }

            YamlFile override = files.get(0);
            YamlFile variations = files.get(1);

            variations.set("variation", override.getString("name"));
            variations.setComment("variation", "Variation type utilized", CommentType.SIDE);

            for (Map.Entry<String, Object> line : override.getValues(true).entrySet()) {
                if (isValid(line)) {
                    variations.set(line.getKey(), line.getValue());
                }
            }

            variations.save();
        }
    }

    private List<YamlFile> getFiles(File file) {
        File configuration = new File(file, "override.yml");

        List<YamlFile> files = new ArrayList<>();

        if (configuration.exists()) {
            // Get the override file
            YamlFile override = loadYaml(configuration);

            // Get the source file
            File sourceFile = ValidFiles.valueOf(override.getString("source")).getFile();
            YamlFile source = loadYaml(sourceFile);

            if (source != null) {
                // Create the new variation
                File variationFile = new File(file, sourceFile.getName());

                try {
                    source.copyTo(variationFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                YamlFile variation = loadYaml(variationFile);

                // Add them to the set
                if (variation != null) {
                    files.add(override);
                    files.add(variation);
                }
            }
        }

        return files;
    }

    private boolean isValid(Map.Entry<String, Object> section) {
         return !(section.getValue() instanceof MemorySection ||
                 section.getKey().equalsIgnoreCase("name") ||
                 section.getKey().equalsIgnoreCase("source"));
    }
}
