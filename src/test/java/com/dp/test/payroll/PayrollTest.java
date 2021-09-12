package com.dp.test.payroll;

import com.dp.payroll.Employee;
import com.dp.payroll.command.TimeCardTransaction;
import com.dp.payroll.command.add.AddCommissionedEmployee;
import com.dp.payroll.command.add.AddHourlyEmployee;
import com.dp.payroll.command.add.AddSalariedEmployee;
import com.dp.payroll.command.delete.DeleteEmployeeTransaction;
import com.dp.payroll.database.PayrollDatabase;
import com.dp.payroll.paymentclassification.*;
import com.dp.payroll.paymentmethod.HoldMethod;
import com.dp.payroll.paymentmethod.PaymentMethod;
import com.dp.payroll.paymentschedule.BiweeklySchedule;
import com.dp.payroll.paymentschedule.MonthlySchedule;
import com.dp.payroll.paymentschedule.PaymentSchedule;
import com.dp.payroll.paymentschedule.WeeklySchedule;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

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
        assertEquals(1000.00, sc.getSalary(), 10);

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
    }

    @Test
    public void testAddHourlyEmployee() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 10.00);
        t.execute();

        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();

        Employee e = gPayrollDatabase.getEmployee(empId);
        assertEquals("Cici", e.getName());

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        assertEquals(10.00, hc.getHourRate(), 10);

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
    }

    @Test
    public void testAddCommissionedEmployee() {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Wawa", "Home", 2500, 10.00);
        t.execute();

        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();

        Employee e = gPayrollDatabase.getEmployee(empId);
        assertEquals("Wawa", e.getName());

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        assertEquals(10.00, cc.getCommissionRate(), 10);

        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule bs = (BiweeklySchedule) ps;

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
    }

    @Test
    public void deleteEmployee() {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();
        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();
        {
            Employee e = gPayrollDatabase.getEmployee(empId);
            assertNotNull(e);
        }

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();
        {
            Employee e = gPayrollDatabase.getEmployee(empId);
            assertNull(e);
        }

    }

    @Test
    public void testTimeCardTransaction(){
        int empId = 2;
        Date date = new Date("2021-09-11");
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        TimeCardTransaction tct = new TimeCardTransaction(date.getTime(), 8.0, empId);
        tct.execute();

        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();
        Employee e = gPayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        HourlyClassification hc = (HourlyClassification) e.getClassification();
        assertNotNull(hc);

        TimeCard tc = hc.getTimeCard(date.getTime());
        assertNotNull(tc);
        assertEquals(8.0,tc.getHours());
    }
}
