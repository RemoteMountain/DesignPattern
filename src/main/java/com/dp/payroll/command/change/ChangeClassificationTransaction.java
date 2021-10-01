package com.dp.payroll.command.change;

import com.dp.payroll.Employee;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentschedule.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction{

    private int empId;

    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    public abstract PaymentClassification getClassification();

    public abstract PaymentSchedule getSchedule();

}
