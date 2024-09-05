package com.dp.payroll;

import com.dp.payroll.Employee;
import com.dp.payroll.Paycheck;
import com.dp.payroll.affiliation.ServiceCharge;
import com.dp.payroll.affiliation.UnionAffiliation;
import com.dp.payroll.command.add.AddCommissionedEmployee;
import com.dp.payroll.command.add.AddHourlyEmployee;
import com.dp.payroll.command.add.AddSalariedEmployee;
import com.dp.payroll.command.change.ChangeAddressEmploueeTransaction;
import com.dp.payroll.command.change.ChangeHourlyTransaction;
import com.dp.payroll.command.change.ChangeMemberTransaction;
import com.dp.payroll.command.change.ChangeNameEmploueeTransaction;
import com.dp.payroll.command.delete.DeleteEmployeeTransaction;
import com.dp.payroll.command.pay.PaydayTransaction;
import com.dp.payroll.command.salesreceipt.SalesReceiptTransaction;
import com.dp.payroll.command.servicecharge.ServiceChargeTransaction;
import com.dp.payroll.command.timecard.TimeCardTransaction;
import com.dp.payroll.database.PayrollDatabase;
import com.dp.payroll.paymentclassification.*;
import com.dp.payroll.paymentmethod.HoldMethod;
import com.dp.payroll.paymentmethod.PaymentMethod;
import com.dp.payroll.paymentschedule.BiweeklySchedule;
import com.dp.payroll.paymentschedule.MonthlySchedule;
import com.dp.payroll.paymentschedule.PaymentSchedule;
import com.dp.payroll.paymentschedule.WeeklySchedule;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class PayrollTest {

    PayrollDatabase gPayrollDatabase = PayrollDatabase.getpayRollDBInstance();

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
    public void testTimeCardTransaction() {
        int empId = 2;

        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        Date payDate = new Date(2021, 11, 12);
        TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId);
        tct.execute();

        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();
        Employee e = gPayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        HourlyClassification hc = (HourlyClassification) e.getClassification();
        assertNotNull(hc);

        TimeCard tc = hc.getTimeCard(payDate);
        assertNotNull(tc);
        assertEquals(8.0, tc.getHours(), 10);
    }

    @Test
    public void testSalesReceiptTransaction() {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bob", "Home", 2500, 3.2);
        t.execute();

        SalesReceiptTransaction srt = new SalesReceiptTransaction(8.0, empId);
        srt.execute();

        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();
        Employee e = gPayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        CommissionedClassification cc = (CommissionedClassification) e.getClassification();
        assertNotNull(cc);

        SalesReceipt sr = cc.getSalesReceipt(8.0);
        assertNotNull(sr);
        assertEquals(8.0, sr.getSales(), 10);
    }

    @Test
    public void testAddServiceCharge() throws ParseException {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 10.00);
        t.execute();

        PayrollDatabase gPayrollDatabase = t.getPayrollDatabase();
        Employee e = gPayrollDatabase.getEmployee(empId);

        int memberId = 1;
        UnionAffiliation af = new UnionAffiliation(memberId, 12.95);
        e.setAffiliation(af);

        gPayrollDatabase.addUnionMember(memberId, e);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-12");
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, date, 12.95);
        sct.execute();

        ServiceCharge sc = af.getServiceCharge(date);
        assertEquals(12.95, sc.getAmount(), 10);

    }

    @Test
    public void testChangeName() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 10.00);
        t.execute();

        ChangeNameEmploueeTransaction cnt = new ChangeNameEmploueeTransaction(empId, "Bob");
        cnt.execute();

        Employee e = gPayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.getName());
    }

    @Test
    public void testChangeAddress() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 10.00);
        t.execute();

        ChangeAddressEmploueeTransaction cat = new ChangeAddressEmploueeTransaction(empId, "NetBar");
        cat.execute();

        Employee e = gPayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals("NetBar", e.getAddress());
    }

    @Test
    public void testChangeHourlyTransaction() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Wawa", "Home", 2500, 10.00);
        t.execute();

        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.52);
        cht.execute();

        Employee e = gPayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        HourlyClassification hc = (HourlyClassification) e.getClassification();
        assertNotNull(hc);

        assertEquals(27.52, hc.getHourRate(), 10);

        WeeklySchedule ws = (WeeklySchedule) e.getSchedule();
        assertNotNull(ws);


    }

    @Test
    public void testPaySingleSalariedEmployee() throws ParseException {
        int empId = 2;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-30");

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        Paycheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);
        assertTrue(payDate == pc.getPayDate());
        assertEquals(1000.00, pc.getGrossPay(), 10);
        assertTrue("Hold" == pc.getField("Disposition"));
        assertEquals(0.0, pc.getDeductions(), 10);
        assertEquals(1000.00, pc.getNetpay(), 10);
    }

    @Test
    public void testPaySingleSalariedEmployeeOnWrongDate() throws ParseException {
        int empId = 2;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-29");

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        Paycheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    @Test
    public void testPaySingleHourlyEmployeeNoTimeCards() throws ParseException {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 10.00);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-12");
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 0.0);
    }

    private void validatePaycheck(PaydayTransaction pt, int empId, Date payDate, double pay) {
        Paycheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);
        assertTrue(pc.getPayPeriodEndDate() == payDate);
        assertTrue("Hold" == pc.getField("Disposition"));
        assertEquals(0.0, pc.getDeductions(), 10);
        assertEquals(pay, pc.getNetpay(), 10);
    }

    @Test
    public void testPaySingleHourlyEmployeeOneTimeCard() throws ParseException {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 15.25);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-12");
        TimeCardTransaction tct = new TimeCardTransaction(payDate, 2.0, empId);
        tct.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 30.5);

    }

    @Test
    public void testPaySingleHourlyEmployeeOvertimeTimeCard() throws ParseException {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 15.25);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-12");

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 9.0, empId);
        tct.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 30.5);

    }

    @Test
    public void testSingleHourlyEmployeeOnWrongDate() throws ParseException {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 15.25);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-12");

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 9.0, empId);
        tct.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        Paycheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    @Test
    public void testSingleHourlyEmployeeTwoTimeCards() throws ParseException {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 15.25);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-12");

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 2.0, empId);
        tct.execute();

        TimeCardTransaction tct2 = new TimeCardTransaction(new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-11"), 5.0, empId);
        tct2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePaycheck(pt, empId, payDate, 7 * 15.25);
    }

    @Test
    public void testSingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws ParseException {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Cici", "Home", 15.25);
        t.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-12");

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 2.0, empId);
        tct.execute();

        TimeCardTransaction tct2 = new TimeCardTransaction(new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-5"), 5.0, empId);
        tct2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePaycheck(pt, empId, payDate, 2 * 15.25);
    }

    @Test
    public void testSalariedUnionMemberDues() throws ParseException {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42);
        cmt.execute();

        Date payDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-30");
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePaycheck(pt, empId, payDate, 1000.00);
    }


}
