package com.dp.ocp.storage;

import java.io.File;

public interface FileStorage {

    String upload(String bucket, File file);
}
