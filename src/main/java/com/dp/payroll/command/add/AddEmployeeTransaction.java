package com.dp.payroll.command.add;

import com.dp.payroll.Employee;
import com.dp.payroll.command.Transaction;
import com.dp.payroll.database.PayrollDatabase;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentmethod.HoldMethod;
import com.dp.payroll.paymentmethod.PaymentMethod;
import com.dp.payroll.paymentschedule.PaymentSchedule;

public abstract class AddEmployeeTransaction implements Transaction {

    PayrollDatabase gPayrollDatabase;


    private int empId;
    private String name;
    private String address;

    public AddEmployeeTransaction(int empId, String name, String address) {
        gPayrollDatabase = new PayrollDatabase();
        setEmpId(empId);
        setName(name);
        setAddress(address);
    }

    @Override
    public void execute() {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();
        Employee e = new Employee(empId, name, address);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setMethod(pm);
        gPayrollDatabase.addEmployee(empId,e);
    }

    public abstract PaymentClassification getClassification();

    public abstract PaymentSchedule getSchedule();


    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PayrollDatabase getPayrollDatabase() {
        return this.gPayrollDatabase;
    }

}
