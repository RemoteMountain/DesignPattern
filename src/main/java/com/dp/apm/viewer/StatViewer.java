package com.dp.apm.viewer;

import java.util.Map;

/**
 * @author LiWang
 * @description: 统计数据显示接口
 * @date 2024/9/10 17:16
 */
public interface StatViewer {

    void output(Map requestStats, long startTimeInMillis, long endTimeInMills);

    void output(String statInfo, long startTimeInMillis, long endTimeInMills);
}
