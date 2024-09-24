package com.dp.apm.formater;

import com.dp.apm.aggregate.RequestStat;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @author LiWang
 * @description: Json显示格式
 * @date 2024/9/24 11:49
 */
public class JsonViewFormatter implements ViewFormatter {

    @Override
    public String format(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMillis) {
        Gson gson = new Gson();
        return gson.toJson(requestStats);
    }
}
