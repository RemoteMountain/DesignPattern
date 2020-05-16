package com.dp.ocp.storage;

import java.io.File;

public class ExamController {

    public void addExam(File file){
        new ExamService().addExam(file);
    }
}
