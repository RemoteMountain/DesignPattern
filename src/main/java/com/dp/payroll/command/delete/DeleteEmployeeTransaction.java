package com.dp.payroll.command.delete;

import com.dp.payroll.command.Transaction;
import com.dp.payroll.database.PayrollDatabase;

public class DeleteEmployeeTransaction implements Transaction {

    private int empId;
    private PayrollDatabase gPayrollDatabase = PayrollDatabase.getpayRollDBInstance();

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public DeleteEmployeeTransaction(){

    }

    public DeleteEmployeeTransaction(int empId){
        setEmpId(empId);
    }

    @Override
    public void execute() {
        gPayrollDatabase.deleteEmployee(empId);
    }
}
