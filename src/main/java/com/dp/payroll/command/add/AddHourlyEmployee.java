package com.dp.payroll.command.add;

import com.dp.payroll.paymentclassification.HourlyClassification;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentclassification.TimeCard;
import com.dp.payroll.paymentschedule.PaymentSchedule;
import com.dp.payroll.paymentschedule.WeeklySchedule;

import java.util.ArrayList;
import java.util.List;

public class AddHourlyEmployee extends AddEmployeeTransaction{

    private double hourRate;
    private List<TimeCard> timeCards = new ArrayList<>();

    public void setHourRate(double hourRate) {
        this.hourRate = hourRate;
    }

    public List<TimeCard> getTimeCards() {
        return timeCards;
    }

    public void addTimeCard(TimeCard timeCard){
        timeCards.add(timeCard);
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
