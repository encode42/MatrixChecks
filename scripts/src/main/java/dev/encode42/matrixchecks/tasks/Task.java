package dev.encode42.matrixchecks.tasks;

import dev.encode42.matrixchecks.util.IO;
import dev.encode42.matrixchecks.util.RecursiveFile;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class Task {
    protected final RecursiveFile[] directories;

    /**
     * A task to run.
     * @param directories Directories to iterate
     */
    public Task(RecursiveFile ...directories) {
        this.directories = directories;
        iterate();
    }

    /**
     * Iterate through the directories.
     */
    protected void iterate() {
        for (RecursiveFile directory : directories) {
            try {
                // Walk the directory
                Files.walk(directory.toPath(), directory.isRecursive() ? Integer.MAX_VALUE : 1).forEach(path -> {
                    // Run the task
                    run(path.toFile());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load a YAML file.
     * @param file File to load
     * @return Loaded YAML file
     */
    protected YamlFile loadYaml(File file) {
        if (!IO.isType(file, "yml")) {
            return null;
        }

        YamlFile yamlFile = new YamlFile(file);

        try {
            yamlFile.load();
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return yamlFile;
    }

    /**
     * Primary task method to run.
     * @param file File to run with
     */
    protected abstract void run(File file);
}
