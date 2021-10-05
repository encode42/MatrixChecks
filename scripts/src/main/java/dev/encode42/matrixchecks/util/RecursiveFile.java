package dev.encode42.matrixchecks.util;

import java.io.File;

/**
 * A file with a recursive holder.
 */
public class RecursiveFile extends File {
    boolean recursive;

    /**
     * Creates a new RecursiveFile instance by converting the given pathname string into an abstract pathname.
     * If the given string is the empty string, then the result is the empty abstract pathname.
     * @param pathname A pathname string
     * @param recursive Whether the file is recursive
     */
    public RecursiveFile(String pathname, boolean recursive) {
        super(pathname);
        this.recursive = recursive;
    }

    /**
     * Creates a new RecursiveFile instance by converting the given pathname string into an abstract pathname.
     * If the given string is the empty string, then the result is the empty abstract pathname.
     * @param pathname A pathname string
     */
    public RecursiveFile(String pathname) {
        this(pathname, false);
    }

    /**
     * @return Whether the file is recursive
     */
    public boolean isRecursive() {
        return recursive;
    }

    /**
     * @param recursive Sets whether the file is recursive
     */
    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }
}
