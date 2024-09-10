package com.dp.apm.v2.viewer;

import com.dp.apm.v1.report.EmailSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiWang
 * @description: TODO
 * @date 2024/9/10 17:17
 */
public class EmailViewer implements StatViewer {

    private EmailSender emailSender;
    private List toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender(/*省略参数*/);
    }

    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(Map requestStats, long startTimeInMillis, long endTimeInMills) {

    }
}
