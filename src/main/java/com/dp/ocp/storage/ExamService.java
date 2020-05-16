package com.dp.ocp.storage;

import java.io.File;
import java.util.List;

public class ExamService {

    private List<FileStorageHandler> fileStorageHandlers;

    public void addExam(File file) {
        for (FileStorageHandler fileStorageHandler:fileStorageHandlers) {
            fileStorageHandler.upload("",file);
        }
    }
}
