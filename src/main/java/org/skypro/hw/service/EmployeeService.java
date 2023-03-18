package org.skypro.hw.service;

import org.apache.commons.lang3.StringUtils;
import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeAlreadyAddedException;
import org.skypro.hw.exception.EmployeeNotFoundException;
import org.skypro.hw.exception.EmployeeStorageIsFullException;
import org.skypro.hw.exception.WrongEmployeeDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 2;

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        checkFirstAndLastName(firstName, lastName);

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
        }

        employees.add(employee);

        return employee;
    }

    public Employee find(String firstName, String lastName) {
        checkFirstAndLastName(firstName, lastName);
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

    public Employee remove(String firstName, String lastName) {
        checkFirstAndLastName(firstName, lastName);
        Employee employee = find(firstName, lastName);

        for (Employee e : employees) {
            if (e.equals(employee)) {
                return e;
            }
        }

        return employee;
    }

    public List<Employee> getAll() {
        return employees;
    }

    private void checkFirstAndLastName(String firstName, String lastName) {
        checkFirstNameOrLastName(firstName);
        checkFirstNameOrLastName(lastName);
    }

    private void checkFirstNameOrLastName(String name) {

        //Приходит не пустая строка
        if (StringUtils.isEmpty(name)) {
            throw new WrongEmployeeDataException("Имя или фамилия не могут быть пустой строкой");
        }

        //Данные сотрудников записываются с большой буквы
        if (!name.equals(StringUtils.capitalize(StringUtils.lowerCase(name)))) {
            throw new WrongEmployeeDataException("Имя или фамилия должны начинаться с заглавной буквы");
        }

        //В переданной строке содержатся только буквы (нужно доработать)
        if (!StringUtils.isAlpha(name)) {
            throw new WrongEmployeeDataException("Имя или фамилия должны содержать только буквы");
        }

        //В переданной строке содержатся только латинские буквы - доп требование для доработки
    }
}
