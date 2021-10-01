package com.dp.payroll.command.change;

import com.dp.payroll.paymentclassification.HourlyClassification;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentschedule.PaymentSchedule;
import com.dp.payroll.paymentschedule.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction{
    private int empId;
    private  double hourlyRate;

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public ChangeHourlyTransaction(int empId, double hourlyRate) {
        super(empId);
        setHourlyRate(hourlyRate);
    }


    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
