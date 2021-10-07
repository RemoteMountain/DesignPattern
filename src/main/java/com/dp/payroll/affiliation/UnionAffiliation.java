package com.dp.payroll.affiliation;

import com.dp.payroll.Paycheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnionAffiliation implements Affiliation {
    private List<ServiceCharge> scs = new ArrayList();

    private int memberId;
    private double charge;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public UnionAffiliation(int memberId, double charge) {
        setMemberId(memberId);
        setCharge(charge);
    }

    public void addServiceCharge(Date date, double charge) {
        scs.add(new ServiceCharge(date, charge));
    }

    public ServiceCharge getServiceCharge(Date date) {
        for (ServiceCharge sc : scs) {
            if (sc.getDate() == date){
                return sc;
            }
        }
        return null;
    }

    @Override
    public double calculateDeductions(Paycheck pc) {
        return 0;
    }
}
