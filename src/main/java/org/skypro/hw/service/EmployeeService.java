package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 2;

    private final Employee[] employees = new Employee[MAX_EMPLOYEES_COUNT];

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                break;
            }
        }

        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = null;
        for (Employee e : employees) {
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                employee = e;
            }
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(employee)) {
                employees[i] = null;
            }
        }

        return employee;
    }

    public Employee[] getAll() {
        return employees;
    }
}
