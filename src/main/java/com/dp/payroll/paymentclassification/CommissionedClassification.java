package com.dp.payroll.paymentclassification;


import com.dp.payroll.Paycheck;

import java.util.ArrayList;
import java.util.List;

public class CommissionedClassification implements PaymentClassification {

    private double salary;
    private double commissionRate;
    private List<SalesReceipt> salesReceipts = new ArrayList();

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getCommissionRate() {
        return this.commissionRate;
    }

    public CommissionedClassification(){}


    public CommissionedClassification(double salary,double commissionRate) {
        setSalary(salary);
        setCommissionRate(commissionRate);
    }

    public void addSalesReceipt(SalesReceipt salesReceipt){
        salesReceipts.add(salesReceipt);
    }

    public SalesReceipt getSalesReceipt(double sales) {
        for (SalesReceipt salesReceipt: salesReceipts) {
            if (salesReceipt.getSales() == sales){
                return salesReceipt;
            }
        }
        return null;
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return 0;
    }
}
