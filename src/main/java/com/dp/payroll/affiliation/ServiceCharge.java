package com.dp.payroll.affiliation;

public class ServiceCharge {

    private long date;
    private double amount;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }


    public ServiceCharge(long date, double amount) {
        setDate(date);
        setAmount(amount);
    }


}
