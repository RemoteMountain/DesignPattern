package com.dp.apm.v2.report;

import com.dp.apm.v1.aggregate.RequestStat;
import com.dp.apm.v1.collector.RequestInfo;
import com.dp.apm.v1.storage.MetricsStorage;
import com.dp.apm.v2.aggregate.Aggregator;
import com.dp.apm.v2.viewer.StatViewer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author LiWang
 * @description: 命令行显示 固定频率统计前多少秒的指标（每60s统计前60s的指标）
 * @date 2024/8/2 11:28
 */
public class ConsoleReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    // 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            long durationInMillis = durationInSeconds * 1000;
            long endTimeInMillis = System.currentTimeMillis();
            long startTimeInMillis = endTimeInMillis - durationInMillis;

            Map<String, List<RequestInfo>> requestInfos =
                    metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);

            Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInMillis);

            viewer.output(stats, startTimeInMillis, endTimeInMillis);
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}


