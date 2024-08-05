package com.dp.apm.v1.aggregate;

import lombok.Data;

/**
 * @author LiWang
 * @description: api统计数据
 * @date 2024/8/5 14:20
 */
@Data
public class RequestStat {
    private long maxResponseTime;
    private long minResponseTime;
    private long avgResponseTime;
    private long p999ResponseTime;
    private long p99ResponseTime;
    private long count;
    private long tps;
}
