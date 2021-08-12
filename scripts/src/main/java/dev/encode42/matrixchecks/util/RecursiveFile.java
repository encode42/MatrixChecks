package dev.encode42.matrixchecks.util;

import java.io.File;

/**
 * A file with a recursive holder.
 */
public class RecursiveFile extends File {
    boolean recursive;

    public RecursiveFile(String pathname, boolean recursive) {
        super(pathname);
        this.recursive = recursive;
    }

    public RecursiveFile(String pathname) {
        this(pathname, false);
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }
}
