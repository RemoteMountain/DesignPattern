package com.dp.payroll.paymentclassification;


public class CommissionedClassification implements PaymentClassification {

    private double commissionRate;

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getCommissionRate() {
        return this.commissionRate;
    }

    public CommissionedClassification(double commissionRate) {
        setCommissionRate(commissionRate);
    }
}
