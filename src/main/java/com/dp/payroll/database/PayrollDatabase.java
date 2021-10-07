package com.dp.payroll.database;

import com.dp.payroll.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollDatabase {

    private Map<Integer, Employee> itsEmployees;
    private Map<Integer, Employee> itsMembers;
    private static PayrollDatabase gPayrollDatabase;

    private PayrollDatabase() {
        itsEmployees = new HashMap<>();
        itsMembers = new HashMap<>();
    }

    public static PayrollDatabase getpayRollDBInstance() {
        if (gPayrollDatabase == null) {
            gPayrollDatabase = new PayrollDatabase();
            return gPayrollDatabase;
        }
        return gPayrollDatabase;
    }


    public Employee getEmployee(int empId) {
        return itsEmployees.get(empId);
    }

    public void addEmployee(int empId, Employee e) {
        itsEmployees.put(empId, e);
    }

    public Employee deleteEmployee(int empId) {
        return itsEmployees.remove(empId);
    }

    public void clear() {
        itsEmployees.clear();
    }

    public Employee getUnionMember(int memberId) {
        return itsMembers.get(memberId);
    }

    public void addUnionMember(int memberId, Employee e) {
        itsMembers.put(memberId, e);
    }

    public List<Employee> getAllEmployee() {
        List<Employee> res = new ArrayList<>();
        for (Map.Entry<Integer, Employee> emps : itsEmployees.entrySet()) {
            res.add(emps.getValue());
        }
        return res;
    }
}
