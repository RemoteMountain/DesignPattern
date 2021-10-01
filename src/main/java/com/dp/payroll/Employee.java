package com.dp.payroll;

import com.dp.payroll.affiliation.Affiliation;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentmethod.PaymentMethod;
import com.dp.payroll.paymentschedule.PaymentSchedule;

public class Employee {
    
    private int empId;
    private String name;
    private String address;
    private Affiliation affiliation;


    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;

    public Employee(int empId, String name, String address) {
        setEmpId(empId);
        setName(name);
        setAddress(address);
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
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
}
