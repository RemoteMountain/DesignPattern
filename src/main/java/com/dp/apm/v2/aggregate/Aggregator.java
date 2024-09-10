package com.dp.apm.v2.aggregate;

import com.dp.apm.v1.aggregate.RequestStat;
import com.dp.apm.v1.collector.RequestInfo;

import java.util.*;

/**
 * @author LiWang
 * @description: 指标聚合统计
 * @date 2024/8/2 11:31
 */
public class Aggregator {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> stats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            stats.put(apiName, requestStat);
        }
        return stats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            respTimes.add(requestInfo.getResponseTime());
        }

        Collections.sort(respTimes, (o1, o2) -> {
            double diff = o1 - o2;
            if (diff < 0.0) {
                return -1;
            } else if (diff > 0.0) {
                return 1;
            } else {
                return 0;
            }
        });

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps(tps(respTimes.size(), durationInMillis / 1000));
        return requestStat;
    }

    private Double max(List<Double> dataset) {
        double maxRespTime = Double.MIN_VALUE;
        for (Double respTime : dataset) {
            maxRespTime = Math.max(maxRespTime, respTime);
        }
        return maxRespTime;
    }

    private Double min(List<Double> dataset) {
        double minRespTime = Double.MAX_VALUE;
        for (Double respTime : dataset) {
            minRespTime = Math.min(minRespTime, respTime);
        }
        return minRespTime;
    }

    private Double avg(List<Double> dataset) {
        double sumRespTime = 0D;
        for (Double respTime : dataset) {
            sumRespTime += respTime;
        }
        return sumRespTime / dataset.size();
    }

    private long tps(int count, double duration) {
        return (long) (count / duration);
    }

    private Double percentile999(List<Double> dataset) {
        int idx999 = (int) (dataset.size() * 0.999);
        return dataset.get(idx999);
    }

    private Double percentile99(List<Double> dataset) {
        int idx99 = (int) (dataset.size() * 0.99);
        return dataset.get(idx99);
    }

    private double percentile(List<Double> dataset, double ratio) {
        int idx = (int) (dataset.size() * ratio);
        return dataset.get(idx);
    }
}

