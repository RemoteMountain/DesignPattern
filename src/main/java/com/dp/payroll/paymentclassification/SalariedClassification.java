package com.dp.payroll.paymentclassification;

public class SalariedClassification implements PaymentClassification {

    /**
     * 每月工资
     */
    private double salary;

    public SalariedClassification(double salary) {
        setSalary(salary);
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }
}
