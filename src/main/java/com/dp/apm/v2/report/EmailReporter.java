package com.dp.apm.v2.report;

import com.dp.apm.v1.aggregate.RequestStat;
import com.dp.apm.v1.collector.RequestInfo;
import com.dp.apm.v1.storage.MetricsStorage;
import com.dp.apm.v2.aggregate.Aggregator;
import com.dp.apm.v2.viewer.StatViewer;

import java.util.*;

/**
 * @author LiWang
 * @description: 邮件显示 定时 统计前一天的指标
 * @date 2024/8/2 11:28
 */
public class EmailReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;


    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;

                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);

                Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInMillis);

                viewer.output(stats, startTimeInMillis, endTimeInMillis);

            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }
}