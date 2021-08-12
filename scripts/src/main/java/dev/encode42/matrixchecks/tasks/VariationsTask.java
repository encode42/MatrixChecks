package dev.encode42.matrixchecks.tasks;

import dev.encode42.matrixchecks.util.RecursiveFile;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;

public class VariationsTask extends Task {
    public VariationsTask(RecursiveFile...directories) {
        super(directories);
    }

    @Override
    protected void run(File file) {
        YamlFile yamlFile = loadYaml(file);
        if (yamlFile != null) {
            System.out.println(yamlFile.getFilePath() + " would be varied, but I haven't set that up yet...");
        }
    }
}
