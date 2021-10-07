package com.dp.payroll.command.pay;

import com.dp.payroll.Employee;
import com.dp.payroll.Paycheck;
import com.dp.payroll.command.Transaction;
import com.dp.payroll.database.PayrollDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaydayTransaction implements Transaction {
    private PayrollDatabase gPayrollDatabase = PayrollDatabase.getpayRollDBInstance();
    private Map<Integer,Paycheck> paychecks;


    private Date payDate;

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public PaydayTransaction(Date payDate) {
        setPayDate(payDate);
        paychecks = new HashMap<>();
    }

    @Override
    public void execute() {
        List<Employee> emps = gPayrollDatabase.getAllEmployee();
        for (Employee emp: emps) {
            if (emp.isPayDate(payDate)){
                Paycheck pc = new Paycheck(payDate);
                paychecks.put(emp.getEmpId(),pc);
                emp.payDay(pc);
            }

        }
    }

    public Paycheck getPayCheck(int empId) {
        return paychecks.get(empId);
    }
}
