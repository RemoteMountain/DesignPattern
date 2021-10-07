package com.dp.payroll.command.timecard;

import com.dp.payroll.Employee;
import com.dp.payroll.command.Transaction;
import com.dp.payroll.database.PayrollDatabase;
import com.dp.payroll.paymentclassification.HourlyClassification;
import com.dp.payroll.paymentclassification.TimeCard;

import java.util.Date;


public class TimeCardTransaction implements Transaction {

    private PayrollDatabase gPayrollDatabase = PayrollDatabase.getpayRollDBInstance();

    private Date date;
    private double hours;
    private int empId;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public TimeCardTransaction() {
    }

    public TimeCardTransaction(Date date, double hours, int empId) {
        setDate(date);
        setHours(hours);
        setEmpId(empId);
    }

    @Override
    public void execute() {
        Employee e = gPayrollDatabase.getEmployee(empId);
        if (e != null){
            HourlyClassification hc = (HourlyClassification)e.getClassification();
            if (hc != null){
                hc.addTimeCard(new TimeCard(date,hours));

            }else {
                throw new RuntimeException("Tired to add timecard to non-hourly employee.");
            }
        }else {
            throw new RuntimeException("No such employee.");
        }


    }
}
