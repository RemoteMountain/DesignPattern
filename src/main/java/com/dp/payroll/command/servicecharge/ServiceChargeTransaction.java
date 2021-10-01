package com.dp.payroll.command.servicecharge;

import com.dp.payroll.Employee;
import com.dp.payroll.affiliation.Affiliation;
import com.dp.payroll.affiliation.UnionAffiliation;
import com.dp.payroll.command.Transaction;
import com.dp.payroll.database.PayrollDatabase;

public class ServiceChargeTransaction implements Transaction {
    private int memberId;
    private long date;
    private double charge;

    private PayrollDatabase gPayrollDatabase = PayrollDatabase.getpayRollDBInstance();

    public ServiceChargeTransaction(int memberId, long date, double charge) {
        setMemberId(memberId);
        setDate(date);
        setCharge(charge);
    }

    @Override
    public void execute() {
        Employee e = gPayrollDatabase.getUnionMember(getMemberId());
        Affiliation af = e.getAffiliation();
        UnionAffiliation uaf = (UnionAffiliation) af;
        uaf.addServiceCharge(getDate(), getCharge());
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
}
