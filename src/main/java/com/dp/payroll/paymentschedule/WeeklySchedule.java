package com.dp.payroll.paymentschedule;

import java.util.Calendar;
import java.util.Date;

public class WeeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == 6;
    }

    @Override
    public Date getPayPeriodStartDate(Date payDate) {
        return null;
    }

}
