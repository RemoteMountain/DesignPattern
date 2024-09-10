package com.dp.apm.v2.viewer;

import java.util.Map;

/**
 * @author LiWang
 * @description: TODO
 * @date 2024/9/10 17:16
 */
public interface StatViewer {

    void output(Map requestStats, long startTimeInMillis, long endTimeInMills);
}
