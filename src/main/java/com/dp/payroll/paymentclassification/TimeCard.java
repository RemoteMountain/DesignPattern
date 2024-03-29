package com.dp.payroll.paymentclassification;

import java.util.Date;

public class TimeCard {

    private Date date;
    private double hours;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }

    public TimeCard(Date date, double hour) {
        setDate(date);
        setHours(hour);
    }
}
