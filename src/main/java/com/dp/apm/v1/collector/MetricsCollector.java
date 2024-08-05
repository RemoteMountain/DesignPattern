package com.dp.apm.v1.collector;

import com.dp.apm.v1.storage.MetricsStorage;
import org.apache.commons.lang3.StringUtils;

/**
 * @author LiWang
 * @description: TODO
 * @date 2024/8/2 11:26
 */
public class MetricsCollector {

    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequest(requestInfo);
    }



}
