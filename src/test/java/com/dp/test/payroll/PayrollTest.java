package com.dp.test.payroll;

import com.dp.payroll.Employee;
import com.dp.payroll.command.add.AddSalariedEmployee;
import com.dp.payroll.database.PayrollDatabase;
import com.dp.payroll.paymentclassification.PaymentClassification;
import com.dp.payroll.paymentclassification.SalariedClassification;
import com.dp.payroll.paymentmethod.HoldMethod;
import com.dp.payroll.paymentmethod.PaymentMethod;
import com.dp.payroll.paymentschedule.MonthlySchedule;
import com.dp.payroll.paymentschedule.PaymentSchedule;
import org.junit.Test;

import static org.junit.Assert.*;

public class PayrollTest {

    @Test
    public void testAddSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();

        Employee e = gPayrollDatabase.getEmployee(empId);
        assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        SalariedClassification sc = (SalariedClassification) pc;
        assertEquals(1000.00, sc.getSalary(),10);

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
    }

    public void deleteEmployee(){

    }
}
