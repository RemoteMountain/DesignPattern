package com.javatest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author:liwang
 * @createTime:2023/4/24 21:03
 * @version:1.0
 */
public class JavaTest {

    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        MyTask myTask = new MyTask();
        es.submit(myTask);
        es.execute(myTask);
        es.shutdown();
    }
}

class MyTask implements  Runnable{

    @Override
    public void run() {
        int a = 1/0;
        System.out.println("111111111111");
    }
}
