package com.dp.payroll.affiliation;

import java.util.Date;

public class ServiceCharge {

    private Date date;
    private double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }


    public ServiceCharge(Date date, double amount) {
        setDate(date);
        setAmount(amount);
    }


}
