package com.dp.payroll;

import com.dp.payroll.affiliation.Affiliation;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentmethod.PaymentMethod;
import com.dp.payroll.paymentschedule.PaymentSchedule;

import java.util.Date;

public class Employee {

    private int empId;
    private String name;
    private String address;
    private Affiliation affiliation;
    private Paycheck payday;


    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;

    public Employee(int empId, String name, String address) {
        setEmpId(empId);
        setName(name);
        setAddress(address);
    }

    public int getEmpId() {
        return empId;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Paycheck getPayday() {
        return payday;
    }

    public void setPayday(Paycheck payday) {
        this.payday = payday;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setClassification(PaymentClassification classification) {
        this.classification = classification;
    }

    public PaymentClassification getClassification() {
        return this.classification;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public PaymentSchedule getSchedule() {
        return this.schedule;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentMethod getMethod() {
        return this.method;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public boolean isPayDate(Date payDate) {
        return schedule.isPayDate(payDate);
    }

    public void payDay(Paycheck pc){
        double grossPay = classification.calculatePay(pc);
        double deductions = affiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.setNetPay(netPay);
        method.pay(pc);
    }
}
