package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {

    private final ArrayList<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = null;
        for (Employee value : employees) {
            if (firstName.equals(value.getFirstName()) && lastName.equals(value.getLastName())) {
                employee = value;
            }
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        employees.remove(find(firstName, lastName));
            return employee;
    }

    public ArrayList<Employee> getAll() {
        return employees;
    }
}
