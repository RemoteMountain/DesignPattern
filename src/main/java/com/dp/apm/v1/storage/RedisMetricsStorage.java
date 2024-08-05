package com.dp.apm.v1.storage;


import com.dp.apm.v1.collector.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @author LiWang
 * @description: redis存储采集数据
 * @date 2024/8/2 11:27
 */
public class RedisMetricsStorage implements MetricsStorage {

    @Override
    public void saveRequest(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
