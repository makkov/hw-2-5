package org.skypro.hw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.DepartmentSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.skypro.hw.entity.Department.DEPARTMENT_BY_ID;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        Integer departmentId = 1;
        float maxSalary = 50f;
        float minSalary = 30f;
        Employee employeeWithMaxSalary = new Employee("Ivan", "Ivanov", maxSalary, DEPARTMENT_BY_ID.get(departmentId));
        Employee employeeWithMinSalary = new Employee("Petr", "Petrov", minSalary, DEPARTMENT_BY_ID.get(departmentId));
        List<Employee> employees = List.of(employeeWithMaxSalary, employeeWithMinSalary);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        assertEquals(employeeWithMinSalary, actualEmployee);
        assertTrue(minSalary < maxSalary);
    }

    @Test
    void getEmployeeWithMinSalary_withDepartmentSearchException() {
        //Подготовка входных данных
        int departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";

        //Начало теста
        Exception exception = assertThrows(
                DepartmentSearchException.class,
                () -> departmentService.getEmployeeWithMinSalary(departmentId)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        Integer departmentId = 1;
        float maxSalary = 50f;
        float minSalary = 30f;
        Employee employeeWithMaxSalary = new Employee("Ivan", "Ivanov", maxSalary, DEPARTMENT_BY_ID.get(departmentId));
        Employee employeeWithMinSalary = new Employee("Petr", "Petrov", minSalary, DEPARTMENT_BY_ID.get(departmentId));
        List<Employee> employees = List.of(employeeWithMaxSalary, employeeWithMinSalary);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(employeeWithMaxSalary, actualEmployee);
        assertTrue(minSalary < maxSalary);
    }

    @Test
    void getEmployeeWithMaxSalary_withDepartmentSearchException() {
        //Подготовка входных данных
        int departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedMessage = "Департамент не найден";

        //Начало теста
        Exception exception = assertThrows(
                DepartmentSearchException.class,
                () -> departmentService.getEmployeeWithMaxSalary(departmentId)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getAll_success() {
        //Подготовка входных данных
        Integer departmentId = 1;
        List<Employee> employees = new ArrayList<>();
        Employee accounting1 = new Employee("Ольга", "Иванова", 10700f, DEPARTMENT_BY_ID.get(1));
        Employee accounting2 = new Employee("Иван", "Олегов", 10200f, DEPARTMENT_BY_ID.get(1));

        Employee it1 = new Employee("Петр", "Елкин", 200f, DEPARTMENT_BY_ID.get(2));
        Employee it2 = new Employee("Алексей", "Снигирь", 10211, DEPARTMENT_BY_ID.get(2));
        Employee it3 = new Employee("Мария", "Петрова", 99999, DEPARTMENT_BY_ID.get(2));

        Employee support1 = new Employee("Геннадий", "Хелпов", 9900, DEPARTMENT_BY_ID.get(3));
        Employee support2 = new Employee("Мария", "Сапортова", 9900, DEPARTMENT_BY_ID.get(3));
        Employee support3 = new Employee("Евгений", "Зинин", 9900, DEPARTMENT_BY_ID.get(3));
        Employee support4 = new Employee("Олеся", "Черненко", 9900, DEPARTMENT_BY_ID.get(3));

        employees.add(accounting1);
        employees.add(accounting2);
        employees.add(it1);
        employees.add(it2);
        employees.add(it3);
        employees.add(support1);
        employees.add(support2);
        employees.add(support3);
        employees.add(support4);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);
        Map<String, List<Employee>> expectedResult = new HashMap<>();
        String departmentName = DEPARTMENT_BY_ID.get(departmentId).getName();
        expectedResult.put(departmentName, List.of(accounting1, accounting2));

        //Начало теста
        Map<String, List<Employee>> actualResult = departmentService.getAll(departmentId);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAll_departmentIdIsNull() {
        //Подготовка входных данных
        Integer departmentId = null;
        List<Employee> employees = new ArrayList<>();
        Employee accounting1 = new Employee("Ольга", "Иванова", 10700f, DEPARTMENT_BY_ID.get(1));
        Employee accounting2 = new Employee("Иван", "Олегов", 10200f, DEPARTMENT_BY_ID.get(1));

        Employee it1 = new Employee("Петр", "Елкин", 200f, DEPARTMENT_BY_ID.get(2));
        Employee it2 = new Employee("Алексей", "Снигирь", 10211, DEPARTMENT_BY_ID.get(2));
        Employee it3 = new Employee("Мария", "Петрова", 99999, DEPARTMENT_BY_ID.get(2));

        Employee support1 = new Employee("Геннадий", "Хелпов", 9900, DEPARTMENT_BY_ID.get(3));
        Employee support2 = new Employee("Мария", "Сапортова", 9900, DEPARTMENT_BY_ID.get(3));
        Employee support3 = new Employee("Евгений", "Зинин", 9900, DEPARTMENT_BY_ID.get(3));
        Employee support4 = new Employee("Олеся", "Черненко", 9900, DEPARTMENT_BY_ID.get(3));

        employees.add(accounting1);
        employees.add(accounting2);
        employees.add(it1);
        employees.add(it2);
        employees.add(it3);
        employees.add(support1);
        employees.add(support2);
        employees.add(support3);
        employees.add(support4);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(employees);
        Map<String, List<Employee>> expectedResult = new HashMap<>();
        expectedResult.put("accounting", List.of(accounting1, accounting2));
        expectedResult.put("it", List.of(it1, it2, it3));
        expectedResult.put("support", List.of(support1, support2, support3, support4));

        //Начало теста
        Map<String, List<Employee>> actualResult = departmentService.getAll(departmentId);
        assertFalse(actualResult.isEmpty());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAll_emptyResult() {
        //Подготовка входных данных
        Integer departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        //Начало теста
        Map<String, List<Employee>> actualResult = departmentService.getAll(departmentId);
        assertTrue(actualResult.isEmpty());
    }
}
