package com.dp.ocp.alert.rule;

public class AlertRule {

    private String api;
    private long requestCount;
    private long errorCount;
    private long durationOfSeconds;
    private long timeoutCount;

    public AlertRule getMatchedRule(String api){
        return null;
    }

    public long getMaxErrorCount(){
        return 1;
    }

    public long getMaxTps(){
        return 1;
    }

}
