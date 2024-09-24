package com.dp.apm.formater;

import com.dp.apm.aggregate.RequestStat;

import java.util.Map;

/**
 * @author LiWang
 * @description: html显示格式
 * @date 2024/9/24 11:49
 */
public class HtmlViewFormatter implements ViewFormatter {

    @Override
    public String format(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
