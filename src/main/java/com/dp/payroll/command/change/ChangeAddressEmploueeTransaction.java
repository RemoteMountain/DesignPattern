package com.dp.payroll.command.change;

import com.dp.payroll.Employee;

public class ChangeAddressEmploueeTransaction extends ChangeEmployeeTransaction {

    private int empId;
    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    public ChangeAddressEmploueeTransaction(int empId, String address){
        super(empId);
        setAddress(address);
    }

    @Override
    public void change(Employee e) {
        e.setAddress(address);
    }
}
