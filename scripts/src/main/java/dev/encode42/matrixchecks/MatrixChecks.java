package dev.encode42.matrixchecks;

import dev.encode42.matrixchecks.tasks.MinifyTask;
import dev.encode42.matrixchecks.tasks.VariationsTask;
import dev.encode42.matrixchecks.util.RecursiveFile;

public class MatrixChecks {
    public MatrixChecks() {
        // Run the tasks
        new VariationsTask(new RecursiveFile("../checks.yml"));
        new MinifyTask(new RecursiveFile("../"), new RecursiveFile("../variations", true));
    }
}
