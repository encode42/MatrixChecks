package dev.encode42.matrixchecks.task;

import dev.encode42.copper.io.RecursiveFile;
import dev.encode42.copper.logger.OmniLogger;
import dev.encode42.copper.util.Util;
import dev.encode42.matrixchecks.util.RootIO;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.File;

public class ZipTask extends CommonTask {
    public ZipTask(RecursiveFile ...directories) {
        super(false, directories);

        File root = new File("../");
        File zipFile = new File(root, "MatrixChecks.zip");
        zipFile.delete();

        OmniLogger.info("Creating a zip archive...");

        iterate();
    }

    @Override
    protected void run(File file) {
        if (isValid(file)) {
            try {
                // Get the target directory structure
                File root = new File("../");
                String targetPath = RootIO.getRelativeDirectory(root, file);

                // Create the zip parameters
                ZipParameters parameters = new ZipParameters();
                parameters.setCompressionMethod(CompressionMethod.DEFLATE);
                parameters.setCompressionLevel(CompressionLevel.MAXIMUM);

                // Add the file to the zip
                File zipFile = new File(root, "MatrixChecks.zip");
                ZipFile zip = new ZipFile(zipFile);
                zip.addFile(file, parameters);

                // Move the file to the right directory
                zip.renameFile(file.getName(), targetPath + File.separator + file.getName());
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finish() {
        OmniLogger.info("Zip archive created!");
    }

    /**
     * Check if a filename is valid for the zip.
     * @param file File to check
     * @return Whether the filename is valid
     */
    private boolean isValid(File file) {
        return !file.isDirectory() && !Util.isEqualSome(file.getName(), "readme.md", "overrides.yml");
    }
}
