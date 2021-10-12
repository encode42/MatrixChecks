package dev.encode42.matrixchecks.util;

import java.io.File;

public enum ValidFiles {
    /**
     * The primary checks file.
     */
    CHECKS("../checks.yml"),

    /**
     * The primary config file.
     */
    CONFIG("../config.yml"),

    /**
     * The primary language file.
     */
    LANGUAGE("../language.yml"),

    /**
     * A variation of another variation.
     */
    COMBO("");

    private final File file;

    /**
     * Get the path utilized by the file.
     * @return File instance utilized by the file type
     */
    public File getFile() {
        return this.file;
    }

    ValidFiles(String file) {
        this.file = new File(file);
    }
}
