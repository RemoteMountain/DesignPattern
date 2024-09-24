package com.dp.apm.report;

import com.dp.apm.aggregate.Aggregator;
import com.dp.apm.formater.JsonViewFormatter;
import com.dp.apm.formater.ViewFormatter;
import com.dp.apm.storage.MetricsStorage;
import com.dp.apm.storage.RedisMetricsStorage;
import com.dp.apm.viewer.ConsoleViewer;
import com.dp.apm.viewer.StatViewer;


/**
 * @author LiWang
 * @description: 网页统计
 * @date 2024/9/24 11:33
 */
public class WebReporter extends ScheduledReporter {

    public WebReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new ConsoleViewer(), new JsonViewFormatter());
    }

    public WebReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer, ViewFormatter viewFormatter) {
        super(metricsStorage, aggregator, viewer, viewFormatter);
    }

    public void webReport(long startTimeInMillis, long endTimeInMillis) {
        doStatAndReport(startTimeInMillis, endTimeInMillis);
    }
}
