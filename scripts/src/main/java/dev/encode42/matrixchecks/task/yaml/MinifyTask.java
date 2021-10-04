package dev.encode42.matrixchecks.task.yaml;

import dev.encode42.matrixchecks.util.RecursiveFile;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;

public class MinifyTask extends CommonTask {
    public MinifyTask(RecursiveFile ...directories) {
        super(directories);
    }

    @Override
    protected void run(File file) {
        YamlFile yamlFile = loadYaml(file);
        if (yamlFile != null) {
            System.out.println(yamlFile.getFilePath() + " would be minified, but I haven't set that up yet...");
        }
    }
}
