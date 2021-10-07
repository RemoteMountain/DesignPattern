package com.dp.payroll.paymentclassification;

import com.dp.payroll.Paycheck;
import com.dp.payroll.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dp.payroll.util.DateUtils.isInRange;

public class HourlyClassification implements PaymentClassification {

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
        Date payPeriod = pc.getPayDate();
        for (TimeCard tc : timeCards) {
            if (isInPayPeriod(tc, payPeriod)) {
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

    private boolean isInPayPeriod(TimeCard tc, Date payPeriod) {
        Date payPeriodEndDate = payPeriod;
        Date payPeriodStartDate = DateUtils.add(payPeriodEndDate, -5);
        Date timecardDate = tc.getDate();
        return isInRange(timecardDate, payPeriodStartDate, payPeriodEndDate);
    }
}
