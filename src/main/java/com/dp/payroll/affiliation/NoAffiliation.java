package com.dp.payroll.affiliation;

import com.dp.payroll.Paycheck;

public class NoAffiliation implements Affiliation{

    @Override
    public double calculateDeductions(Paycheck pc) {
        return 0;
    }
}
