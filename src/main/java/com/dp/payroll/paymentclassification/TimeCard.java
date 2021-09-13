package com.dp.payroll.paymentclassification;

import java.util.Date;

public class TimeCard {

    private String date;
    private double hours;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }

    public TimeCard(String date, double hour) {
        setDate(date);
        setHours(hour);
    }
}
