package dev.encode42.matrixchecks.util;

import java.io.File;

public class IO {
    /**
     * Get a file's extension type.
     * @param file File to get extension from
     * @return File extension type
     */
    public static String getExtension(File file) {
        String fileName = file.getAbsoluteFile().getName();
        return fileName.substring(Math.max(fileName.indexOf(".") + 1, 0));
    }

    /**
     * Check if a file has a certain extension.
     * @param file File to check extension
     * @param type Type to check for
     * @return Whether the file type is the specified type
     */
    public static boolean isType(File file, String type) {
        return getExtension(file).equals(type);
    }
}
