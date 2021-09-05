package com.dp.payroll.database;

import com.dp.payroll.Employee;

import java.util.HashMap;
import java.util.Map;

public class PayrollDatabase {

    private Map<Integer, Employee> itsEmployees;

    public PayrollDatabase(){
        itsEmployees = new HashMap<>();
    }


    public Employee getEmployee(int empId) {
        return itsEmployees.get(empId);
    }

    public void addEmployee(int empId, Employee e) {
        itsEmployees.put(empId,e);
    }
}
