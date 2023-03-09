package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeAlreadyAddedException;
import org.skypro.hw.exception.EmployeeNotFoundException;
import org.skypro.hw.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 2;

    private final Map<Integer, Employee> employeeByHashCode = new HashMap<>();

    public Employee add(String firstName, String lastName) {


        if (employeeByHashCode.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Хранилище сотрудников переполнено");
        }

        int employeeKey = getEmployeeKey(firstName, lastName);

        if (employeeByHashCode.containsKey(employeeKey)) {
            throw new EmployeeAlreadyAddedException("В хранилище уже есть такой сотрудник");
        }

        Employee employee = new Employee(firstName, lastName);

        employeeByHashCode.put(employeeKey, employee);

        return employee;
    }

    public Employee find(String firstName, String lastName) {
        int employeeHashCode = getEmployeeKey(firstName, lastName);
        Employee employee = employeeByHashCode.get(employeeHashCode);
        presentCheck(employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        int employeeHashCode = getEmployeeKey(firstName, lastName);
        Employee employee = employeeByHashCode.remove(employeeHashCode);
        presentCheck(employee);
        return employee;
    }

    public List<Employee> getAll() {
        return employeeByHashCode.values()
                .stream().toList();
    }

    private int getEmployeeKey(String firstName, String lastName) {
        return Objects.hash(firstName, lastName);
    }

    private void presentCheck(Employee employee) {
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
}
