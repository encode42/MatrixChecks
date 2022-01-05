package dev.encode42.matrixchecks;

import dev.encode42.copper.io.RecursiveFile;
import dev.encode42.copper.logger.OmniLogger;
import dev.encode42.matrixchecks.task.MinifyTask;
import dev.encode42.matrixchecks.task.VariationsTask;
import dev.encode42.matrixchecks.task.ZipTask;

public class MatrixChecks {
    public MatrixChecks() {
        OmniLogger.setLogger(System.out, System.err, true);

        // Run the tasks
        new VariationsTask(new RecursiveFile("../variations", true));
        new MinifyTask(new RecursiveFile("../"), new RecursiveFile("../variations", true));
        new ZipTask(new RecursiveFile("../"), new RecursiveFile("../variations", true));
    }
}
