package com.dp.payroll.command.change;

import com.dp.payroll.Employee;

public class ChangeNameEmploueeTransaction extends ChangeEmployeeTransaction {
    private int empId;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public ChangeNameEmploueeTransaction(int empId, String name) {
        super(empId);
        setName(name);
    }

    @Override
    public void change(Employee e) {
        e.setName(name);
    }
}
