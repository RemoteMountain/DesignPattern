package com.dp.principle.ocp.alert.handler.impl;


import com.dp.principle.ocp.alert.ApiStatInfo;
import com.dp.principle.ocp.alert.handler.AlertHandler;
import com.dp.principle.ocp.alert.notification.Notification;
import com.dp.principle.ocp.alert.notification.NotificationEmergencyLevel;
import com.dp.principle.ocp.alert.rule.AlertRule;

public class ErrorAlertHandler extends AlertHandler {

    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}
