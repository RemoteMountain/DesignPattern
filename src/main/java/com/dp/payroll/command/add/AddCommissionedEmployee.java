package com.dp.payroll.command.add;

import com.dp.payroll.paymentclassification.CommissionedClassification;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentclassification.SalesReceipt;
import com.dp.payroll.paymentschedule.BiweeklySchedule;
import com.dp.payroll.paymentschedule.PaymentSchedule;

import java.util.ArrayList;
import java.util.List;

public class AddCommissionedEmployee extends AddEmployeeTransaction{

    private double salary;
    private double commissionRate;

    public void setSales(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public AddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate) {
        super(empId,name,address);
        setSalary(salary);
        setSales(commissionRate);
    }

    @Override
    public PaymentClassification getClassification() {
        return new CommissionedClassification(salary,commissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }


}
