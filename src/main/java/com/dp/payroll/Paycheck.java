package com.dp.payroll;

import java.util.Date;

public class Paycheck {
    private double grossPay;
    private double deductions;
    private double netPay;
    private String field;
    private Date payPeriodStartDate;
    private Date payDate;

    public Date getPayDate() {
        return payDate;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetpay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public double getGrossPay() {
        return 0;
    }

    public String getField(String disposition) {
        return "Hold";
    }

    public Date getPayPeriodStartDate() {
        return payPeriodStartDate;
    }

    public void setPayPeriodStartDate(Date payPeriodStartDate) {
        this.payPeriodStartDate = payPeriodStartDate;
    }

    public Date getPayPeriodEndDate() {
        return payDate;
    }

    public Paycheck(Date payPeriodStartDate, Date payDate) {
        setPayPeriodStartDate(payPeriodStartDate);
        setPayDate(payDate);
    }


}
