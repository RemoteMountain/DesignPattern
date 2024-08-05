package com.dp.apm;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author LiWang
 * @description: 性能计数器
 * @date 2024/8/2 9:49
 */
public class Metrics {

    /**
     * 不是同时写入请求开始时间和请求响应时间，
     * 在多线程环境下，可能会造成两者不是一一对应的情况
     * 不过，目前统计的最大值、最小值、请求次数等 对两个map的顺序性没有要求
     * 上述问题，可忽略。
     *
     * 每个map，可以使用线程安全的ConcurrentHashMap
     */
    //接口响应时间 key-接口名称
    private Map<String, List<Long>> responseTimes;
    //接口请求时间戳
    private Map<String, List<Long>> timestamps;

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void recordResponseTime(String apiName, Long responseTime) {
        responseTimes.putIfAbsent(apiName, new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    public void recordTimestamp(String apiName, Long timestamp) {
        timestamps.putIfAbsent(apiName, new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.scheduleAtFixedRate(() -> {
            Gson gson = new Gson();
            Map<String, Map<String, Long>> stats = new HashMap<>();
            for (Map.Entry<String, List<Long>> entry : responseTimes.entrySet()) {
                String apiName = entry.getKey();
                List apiRespTimes = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("max", max(apiRespTimes));
                stats.get(apiName).put("avg", avg(apiRespTimes));
            }
            for (Map.Entry<String, List<Long>> entry : timestamps.entrySet()) {
                String apiName = entry.getKey();
                List<Long> apiTimestamps = entry.getValue();
                stats.putIfAbsent(apiName, new HashMap<>());
                stats.get(apiName).put("count", Integer.toUnsignedLong(apiTimestamps.size()));
            }
            System.out.println(gson.toJson(stats));
        }, 0, period, unit);
    }

    private Long max(List<Double> dataset) {
        return null;
    }

    private Long avg(List<Double> dataset) {
        return null;
    }
}
