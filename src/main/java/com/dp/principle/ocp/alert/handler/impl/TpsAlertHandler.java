package com.dp.principle.ocp.alert.handler.impl;


import com.dp.principle.ocp.alert.ApiStatInfo;
import com.dp.principle.ocp.alert.handler.AlertHandler;
import com.dp.principle.ocp.alert.notification.Notification;
import com.dp.principle.ocp.alert.notification.NotificationEmergencyLevel;
import com.dp.principle.ocp.alert.rule.AlertRule;

public class TpsAlertHandler extends AlertHandler {

    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }


}
