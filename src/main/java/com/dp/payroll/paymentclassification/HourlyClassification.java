package com.dp.payroll.paymentclassification;

import com.dp.payroll.Paycheck;
import com.dp.payroll.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HourlyClassification extends PaymentClassification {

    /**
     * 每小时工资
     */
    private Double hourlyRate;
    private List<TimeCard> timeCards = new ArrayList<>();

    public List<TimeCard> getTimeCards() {
        return timeCards;
    }

    public TimeCard getTimeCard(Date date) {
        for (TimeCard tc : timeCards) {
            if (tc.getDate().equals(date)) {
                return tc;
            }
        }
        return null;
    }

    public void addTimeCard(TimeCard timeCard) {
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

    @Override
    public double calculatePay(Paycheck pc) {
        double res = 0;
        for (TimeCard tc : timeCards) {
            if (DateUtils.isInPayPeriod(tc.getDate(), pc.getPayPeriodStartDate(),pc.getPayPeriodEndDate())) {
                res += calculatePayForTimeCard(tc);
            }
        }
        return res;
    }

    private double calculatePayForTimeCard(TimeCard tc) {
        double hours = tc.getHours();
        double overtime = Math.max(0.0, hours - 8.0);
        double straightTime = hours - overtime;
        return straightTime * hourlyRate + overtime * hourlyRate * 1.5;
    }

}
