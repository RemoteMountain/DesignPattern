package com.dp.apm.v1.aggregate;

import com.dp.apm.v1.collector.RequestInfo;

import java.util.Collections;
import java.util.List;

/**
 * @author LiWang
 * @description: 指标聚合统计
 * @date 2024/8/2 11:31
 */
public class Aggregator {

    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationInSeconds) {
        long maxRespTime = Long.MIN_VALUE;
        long minRespTime = Long.MAX_VALUE;
        long avgRespTime = -1;
        long p999RespTime = -1;
        long p99RespTime = -1;
        long sumRespTime = 0;
        long count = 0;
        for (RequestInfo requestInfo : requestInfos) {
            ++count;
            long respTime = requestInfo.getResponseTime();
            if (maxRespTime < respTime) {
                maxRespTime = respTime;
            }
            if (minRespTime > respTime) {
                minRespTime = respTime;
            }
            sumRespTime += respTime;
        }
        if (count != 0) {
            avgRespTime = sumRespTime / count;
        }
        long tps = count / durationInSeconds * 1000;
        Collections.sort(requestInfos, (o1, o2) -> {
            double diff = o1.getResponseTime() - o2.getResponseTime();
            if (diff < 0.0) {
                return -1;
            } else if (diff > 0.0) {
                return 1;
            } else {
                return 0;
            }
        });
        int idx999 = (int) (count * 0.999);
        int idx99 = (int) (count * 0.99);
        if (count != 0) {
            p999RespTime = requestInfos.get(idx999).getResponseTime();
            p99RespTime = requestInfos.get(idx99).getResponseTime();
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setCount(count);
        requestStat.setTps(tps);
        return requestStat;
    }
}

