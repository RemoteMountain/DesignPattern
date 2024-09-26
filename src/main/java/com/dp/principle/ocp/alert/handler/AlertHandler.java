package com.dp.principle.ocp.alert.handler;


import com.dp.principle.ocp.alert.ApiStatInfo;
import com.dp.principle.ocp.alert.notification.Notification;
import com.dp.principle.ocp.alert.rule.AlertRule;

public abstract class AlertHandler {

    protected AlertRule rule;
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
