package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeAlreadyAddedException;
import org.skypro.hw.exception.EmployeeNotFoundException;
import org.skypro.hw.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.skypro.hw.entity.Department.DEPARTMENT_BY_ID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 2;

    private static List<Employee> employees = new ArrayList<>();

    @Override
    public Employee add(String firstName, String lastName, float salary, int departmentId) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName, salary, DEPARTMENT_BY_ID.get(departmentId));

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
        }

        employees.add(employee);

        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = null;

        for (Employee e : employees) {
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                employee = e;
            }
        }

        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);

        for (Employee e : employees) {
            if (e.equals(employee)) {
                employees.remove(e);
                return e;
            }
        }

        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }
}
