package com.dp.apm.report;

import com.dp.apm.aggregate.Aggregator;
import com.dp.apm.storage.MetricsStorage;
import com.dp.apm.storage.RedisMetricsStorage;
import com.dp.apm.viewer.ConsoleViewer;
import com.dp.apm.viewer.StatViewer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author LiWang
 * @description: 命令行显示 固定频率统计前多少秒的指标（每60s统计前60s的指标）
 * @date 2024/8/2 11:28
 */
public class ConsoleReporter extends ScheduledReporter {
    private ScheduledExecutorService executor;

    public ConsoleReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new ConsoleViewer());
    }

    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    // 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            long durationInMillis = durationInSeconds * 1000;
            long endTimeInMillis = System.currentTimeMillis();
            long startTimeInMillis = endTimeInMillis - durationInMillis;

            doStatAndReport(startTimeInMillis, endTimeInMillis);
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}


