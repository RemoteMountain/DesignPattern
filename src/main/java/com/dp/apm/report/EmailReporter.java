package com.dp.apm.report;

import com.dp.apm.aggregate.Aggregator;
import com.dp.apm.storage.MetricsStorage;
import com.dp.apm.storage.RedisMetricsStorage;
import com.dp.apm.viewer.EmailViewer;
import com.dp.apm.viewer.StatViewer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author LiWang
 * @description: 邮件显示 定时 统计前一天的指标
 * @date 2024/8/2 11:28
 */
public class EmailReporter extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    public EmailReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new EmailViewer());
    }


    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }

    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay(new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;

                doStatAndReport(startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    protected Date trimTimeFieldsToZeroOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 这里可以获取当前时间
        calendar.setTime(date);
        // 重新设置时间
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}