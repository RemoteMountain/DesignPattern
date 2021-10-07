package com.dp.payroll.paymentclassification;

import com.dp.payroll.Paycheck;
import com.dp.payroll.util.DateUtils;

import java.util.Date;

public abstract class PaymentClassification {

    public abstract double calculatePay(Paycheck pc);


}
