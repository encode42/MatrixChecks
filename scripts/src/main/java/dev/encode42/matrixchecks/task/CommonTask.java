package dev.encode42.matrixchecks.task;

import dev.encode42.copper.io.IO;
import dev.encode42.copper.io.RecursiveFile;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class CommonTask {
    protected final RecursiveFile[] directories;

    /**
     * A task to run.
     * @param iterate Whether to iterate on initialization
     * @param directories Directories to iterate
     */
    public CommonTask(boolean iterate, RecursiveFile ...directories) {
        this.directories = directories;

        if (iterate) {
            iterate();
        }
    }

    /**
     * A task to run.
     * @param directories Directories to iterate
     */
    public CommonTask(RecursiveFile ...directories) {
        this(true, directories);
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
                    try {
                        run(path.toFile());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        finish();
    }

    /**
     * Load a YAML file.
     * @param file File to load
     * @param withComments Whether to load the file with comments
     * @return Loaded YAML file
     */
    public static YamlFile loadYaml(File file, boolean withComments) {
        if (!IO.isType(file, "yml")) {
            return null;
        }

        YamlFile yamlFile = new YamlFile(file);

        try {
            if (withComments) {
                yamlFile.loadWithComments();
            } else {
                yamlFile.load();
                yamlFile.options().header("");
            }
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return yamlFile;
    }

    /**
     * Load a YAML file.
     * @param file File to load
     * @return Loaded YAML file
     */
    public static YamlFile loadYaml(File file) {
        return loadYaml(file, true);
    }

    /**
     * Primary task method to run.
     * @param file File to run with
     */
    protected abstract void run(File file);

    /**
     * Method called when the task is finished.
     */
    protected abstract void finish();
}
