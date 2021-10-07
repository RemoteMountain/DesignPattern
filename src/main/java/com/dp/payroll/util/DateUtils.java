package com.dp.payroll.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date add(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static boolean isInRange(Date date, Date startDate, Date endDate) {
        if (date.getTime() == startDate.getTime() ||
                date.getTime() == endDate.getTime()) {
            return true;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        if (calendar.after(begin) && calendar.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
