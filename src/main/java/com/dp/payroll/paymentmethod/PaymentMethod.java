package com.dp.payroll.paymentmethod;

import com.dp.payroll.Paycheck;

public interface PaymentMethod {
    void pay(Paycheck pc);
}
