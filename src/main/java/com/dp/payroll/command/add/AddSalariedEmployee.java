package com.dp.payroll.command.add;

import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentclassification.SalariedClassification;
import com.dp.payroll.paymentschedule.MonthlySchedule;
import com.dp.payroll.paymentschedule.PaymentSchedule;

public class AddSalariedEmployee extends AddEmployeeTransaction{

    private double salary;

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public AddSalariedEmployee(int empId, String name, String address, double salary) {
        super(empId,name,address);
        setSalary(salary);
    }

    @Override
    public PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

}
