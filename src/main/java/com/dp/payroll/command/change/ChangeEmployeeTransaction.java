package com.dp.payroll.command.change;

import com.dp.payroll.Employee;
import com.dp.payroll.command.Transaction;
import com.dp.payroll.database.PayrollDatabase;

public abstract class ChangeEmployeeTransaction implements Transaction {

    private int empId;

    PayrollDatabase gPayrollDatabase = PayrollDatabase.getpayRollDBInstance();

    public void setEmpId(int empId) {
        this.empId = empId;
    }


    public ChangeEmployeeTransaction(int empId) {
        setEmpId(empId);
    }

    @Override
    public void execute() {
        Employee e = gPayrollDatabase.getEmployee(empId);
        if (e != null) {
            change(e);
        }
    }

    public abstract void change(Employee e);
}
