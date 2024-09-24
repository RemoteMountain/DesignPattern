package com.dp.apm.formater;

import com.dp.apm.aggregate.RequestStat;

import java.util.Map;

/**
 * @author LiWang
 * @description: 显示格式
 * @date 2024/9/24 11:49
 */
public interface ViewFormatter {

    String format(Map<String, RequestStat> stats, long startTimeInMillis, long endTimeInMillis);
}
