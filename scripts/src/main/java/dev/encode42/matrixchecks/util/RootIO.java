package dev.encode42.matrixchecks.util;

import java.io.File;
import java.io.IOException;

public class RootIO {
    public static String getRelativeDirectory(File root, File file) {
        StringBuilder targetPath = new StringBuilder();
        File currentPath = file.getParentFile();

        try {
            while (!currentPath.getCanonicalPath().equals(root.getCanonicalPath())) {
                targetPath.insert(0, File.separator + currentPath.getName());
                currentPath = currentPath.getParentFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return targetPath.toString();
    }
}
