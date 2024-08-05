package com.dp.apm.v1.storage;

import com.dp.apm.v1.collector.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @author LiWang
 * @description: 采集数据存储接口
 * @date 2024/8/2 11:27
 */
public interface MetricsStorage {
    void saveRequest(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
