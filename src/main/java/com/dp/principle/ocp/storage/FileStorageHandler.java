package com.dp.principle.ocp.storage;

import java.io.File;

public abstract class FileStorageHandler {

    protected FileStorage fileStorage;

    public FileStorageHandler(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public abstract String upload(String bucket, File file);
}
