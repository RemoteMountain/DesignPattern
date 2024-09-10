package com.dp.apm.v1.collector;

import lombok.Data;

/**
 * @author LiWang
 * @description: api请求性能信息
 * @date 2024/8/5 13:54
 */
@Data
public class RequestInfo {

    private String apiName;
    private long timestamp;
    private double responseTime;
}
