package dev.encode42.matrixchecks;

import dev.encode42.copper.io.RecursiveFile;
import dev.encode42.copper.logger.OmniLogger;
import dev.encode42.matrixchecks.task.MinifyTask;
import dev.encode42.matrixchecks.task.VariationsTask;
import dev.encode42.matrixchecks.task.ZipTask;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        new MatrixChecks();
    }

    static class MatrixChecks {
        // This needs a massive cleanup, but it's working!

        public MatrixChecks() {
            OmniLogger.setLogger(Logger.getLogger(this.getClass().getName()), true);

            // Run the tasks
            new VariationsTask(new RecursiveFile("../variations", true));
            new MinifyTask(new RecursiveFile("../"), new RecursiveFile("../variations", true));
            new ZipTask(new RecursiveFile("../"), new RecursiveFile("../variations", true));
        }
    }
}