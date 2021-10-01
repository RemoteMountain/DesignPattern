package com.dp.payroll.command.add;

import com.dp.payroll.paymentclassification.HourlyClassification;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentschedule.PaymentSchedule;
import com.dp.payroll.paymentschedule.WeeklySchedule;

public class AddHourlyEmployee extends AddEmployeeTransaction{

    private double hourRate;

    public void setHourRate(double hourRate) {
        this.hourRate = hourRate;
    }



    public AddHourlyEmployee(int empId, String name, String address, double hourRate) {
        super(empId, name, address);
        setHourRate(hourRate);
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(hourRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
