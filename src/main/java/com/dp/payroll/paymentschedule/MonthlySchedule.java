package com.dp.payroll.paymentschedule;


import java.util.Calendar;
import java.util.Date;

public class MonthlySchedule implements PaymentSchedule {

    public boolean isPayDate(Date payDate) {
        return isLastDayOfMonth(payDate);

    }

    private boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
}
