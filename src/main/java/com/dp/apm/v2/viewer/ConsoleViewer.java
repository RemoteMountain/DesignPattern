package com.dp.apm.v2.viewer;

import com.google.gson.Gson;

import java.util.Map;

/**
 * @author LiWang
 * @description: TODO
 * @date 2024/9/10 17:17
 */
public class ConsoleViewer implements StatViewer {
    @Override
    public void output(Map requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}
