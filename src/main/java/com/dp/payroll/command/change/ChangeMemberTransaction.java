package com.dp.payroll.command.change;

import com.dp.payroll.Employee;
import com.dp.payroll.affiliation.UnionAffiliation;

public class ChangeMemberTransaction extends ChangeEmployeeTransaction{

    private int empId;
    private int memberId;
    private double charge;

    public void setEmpId(int empId) {
        this.empId = empId;
    }



    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public ChangeMemberTransaction(int empId, int memberId, double charge) {
        super(empId);
        setMemberId(memberId);
        setCharge(charge);
    }

    @Override
    public void change(Employee e) {
        e.setAffiliation(new UnionAffiliation(memberId,charge));
    }
}
