package com.dp.payroll.paymentschedule;

import java.util.Date;

public class BiweeklySchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(Date date) {
        return false;
    }

    @Override
    public Date getPayPeriodStartDate(Date payDate) {
        return null;
    }
}
