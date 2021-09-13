package com.dp.payroll.command;

import com.dp.payroll.Employee;
import com.dp.payroll.database.PayrollDatabase;
import com.dp.payroll.paymentclassification.CommissionedClassification;
import com.dp.payroll.paymentclassification.SalesReceipt;

public class SalesReceiptTransaction implements Transaction{

    private PayrollDatabase gPayrollDatabase = PayrollDatabase.getpayRollDBInstance();

    private double sales;
    private int empId;

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public SalesReceiptTransaction(double sales, int empId) {
        setSales(sales);
        setEmpId(empId);
    }

    @Override
    public void execute() {
        Employee e = gPayrollDatabase.getEmployee(empId);
        if (e != null){
            CommissionedClassification cc = (CommissionedClassification)e.getClassification();
            if (cc != null){
                cc.addSalesReceipt(new SalesReceipt(sales));
            }else {
                throw new RuntimeException("Tired to add salesreceipt to non-commissioned employee.");
            }
        }else {
            throw new RuntimeException("No such employee.");
        }

    }
}
