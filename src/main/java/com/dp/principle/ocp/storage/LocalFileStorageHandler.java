package com.dp.principle.ocp.storage;

import java.io.File;

public class LocalFileStorageHandler extends  FileStorageHandler {


    public LocalFileStorageHandler(FileStorage fileStorage) {
        super(fileStorage);
    }

    public String upload(String bucket, File file) {
        if ("local".equals("")){
            return fileStorage.upload(bucket,file);
        }
        return "";
    }
}
