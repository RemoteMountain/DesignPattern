package com.dp.principle.ocp.storage;

import java.io.File;

public class QiniuFileStorageHandler extends  FileStorageHandler{

    public QiniuFileStorageHandler(FileStorage fileStorage) {
        super(fileStorage);
    }

    public String upload(String bucket, File file) {
        if ("qiniu".equals("")){
            return fileStorage.upload(bucket,file);
        }
        return "";
    }
}
