package com.dp.apm;

import com.dp.apm.aggregate.Aggregator;
import com.dp.apm.collector.MetricsCollector;
import com.dp.apm.collector.RequestInfo;
import com.dp.apm.report.ConsoleReporter;
import com.dp.apm.report.EmailReporter;
import com.dp.apm.storage.MetricsStorage;
import com.dp.apm.storage.RedisMetricsStorage;
import com.dp.apm.viewer.ConsoleViewer;
import com.dp.apm.viewer.EmailViewer;
import com.dp.apm.viewer.StatViewer;

/**
 * @author LiWang
 * @description: TODO
 * @date 2024/9/20 11:40
 */
public class PerfCounterTest {

    public static void main(String[] args) {
        MetricsStorage metricsStorage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        StatViewer consoleViewer = new ConsoleViewer();

        ConsoleReporter consoleReporter = new ConsoleReporter(metricsStorage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("lw@163.com");
        EmailReporter emailReporter = new EmailReporter(metricsStorage, aggregator, emailViewer);
        emailReporter.startDailyReport();
        // 收集接口访问数据
        MetricsCollector collector = new MetricsCollector(metricsStorage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
