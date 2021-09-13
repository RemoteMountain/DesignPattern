package com.dp.payroll.paymentclassification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HourlyClassification implements PaymentClassification {

    /**
     * 每小时工资
     */
    private Double hourlyRate;
    private List<TimeCard> timeCards = new ArrayList<>();

    public List<TimeCard> getTimeCards() {
        return timeCards;
    }

    public TimeCard getTimeCard(String date){
        for (TimeCard tc: timeCards) {
            if (tc.getDate().equals(date)){
                return tc;
            }
        }
        return null;
    }

    public void addTimeCard(TimeCard timeCard){
        timeCards.add(timeCard);
    }

    public HourlyClassification(double hourRate) {
        setHourlyRate(hourRate);
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourRate() {
        return this.hourlyRate;
    }
}
