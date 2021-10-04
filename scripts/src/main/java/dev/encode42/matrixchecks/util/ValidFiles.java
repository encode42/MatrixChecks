package dev.encode42.matrixchecks.util;

import java.io.File;

public enum ValidFiles {
    CHECKS("../checks.yml"),
    CONFIG("../config.yml"),
    LANGUAGE("../language.yml");

    private final File file;

    public File getFile() {
        return this.file;
    }

    private ValidFiles(String file) {
        this.file = new File(file);
    }
}
