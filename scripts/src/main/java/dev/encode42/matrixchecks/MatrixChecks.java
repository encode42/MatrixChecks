package dev.encode42.matrixchecks;

import dev.encode42.matrixchecks.task.yaml.MinifyTask;
import dev.encode42.matrixchecks.task.yaml.VariationsTask;
import dev.encode42.matrixchecks.util.RecursiveFile;

public class MatrixChecks {
    // This needs a massive cleanup, but it's working!

    public MatrixChecks() {
        // Run the tasks
        new VariationsTask(new RecursiveFile("../variations"));
        new MinifyTask(new RecursiveFile("../"), new RecursiveFile("../variations", true));
    }
}
