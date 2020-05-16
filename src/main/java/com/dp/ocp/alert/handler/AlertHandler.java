package com.dp.ocp.alert.handler;


import com.dp.ocp.alert.ApiStatInfo;
import com.dp.ocp.alert.notification.Notification;
import com.dp.ocp.alert.rule.AlertRule;

public abstract class AlertHandler {

    protected AlertRule rule;
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
