package dev.encode42.matrixchecks.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dev.encode42.copper.io.IO;
import dev.encode42.copper.io.RecursiveFile;
import dev.encode42.copper.logger.OmniLogger;
import dev.encode42.copper.util.Util;
import dev.encode42.matrixchecks.util.RootIO;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;

public class MinifyTask extends CommonTask {
    public MinifyTask(RecursiveFile ...directories) {
        super(directories);
    }

    @Override
    protected void run(File file) {
        if (!isValid(file.getName())) {
            return;
        }

        // Target directories
        File root = new File("../");
        File cloudFile = new File(root, "cloud");

        YamlFile yamlFile = loadYaml(file, false);
        if (yamlFile != null) {
            // Save the YAML file to a string
            String contents;
            try {
                contents = yamlFile.saveToString();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            if (contents.isBlank()) {
                return;
            }

            // Hack time! Convert YAML to JSON
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            ObjectMapper jsonWriter = new ObjectMapper();

            Object object;
            String json;
            try {
                object = yamlReader.readValue(contents, Object.class);
                json = jsonWriter.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return;
            }

            // Further minification
            String sanitized = json.replaceAll("([{,])\"(.*?)\"", "$1$2");

            // Get the target cloud path
            String targetPath = RootIO.getRelativeDirectory(root, file);

            // Create and write to the file
            File target = new File(cloudFile, targetPath.toString());
            target.mkdirs();

            File minified = new File(target, file.getName());
            IO.writeFile(minified, sanitized, true);

            OmniLogger.info(file.getName() + " has been minified successfully!");
        }
    }

    /**
     * Check if a filename is valid for minification.
     * @param filename Filename to check
     * @return Whether the filename is valid
     */
    private boolean isValid(String filename) {
        return !(Util.isEqualSome(filename, "overrides.yml", "source"));
    }

    @Override
    protected void finish() {
        OmniLogger.info("Finished minification of files.");
    }
}
