package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        List<Employee> allEmployees = employeeService.getAll();
        float minSalary = Float.MAX_VALUE;
        Employee employeeInDepartmentWithMinSalary = null;

        for (Employee e : allEmployees) {
            if (e.getDepartment().getId() == departmentId && e.getSalary() < minSalary) {
                minSalary = e.getSalary();
                employeeInDepartmentWithMinSalary = e;
            }
        }

        return employeeInDepartmentWithMinSalary;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        List<Employee> allEmployees = employeeService.getAll();
        float maxSalary = Float.MIN_VALUE;
        Employee employeeInDepartmentWithMaxSalary = null;

        for (Employee e : allEmployees) {
            if (e.getDepartment().getId() == departmentId && e.getSalary() > maxSalary) {
                maxSalary = e.getSalary();
                employeeInDepartmentWithMaxSalary = e;
            }
        }

        return employeeInDepartmentWithMaxSalary;
    }

    @Override
    public Map<String, List<Employee>> getAll(Integer departmentId) {
        List<Employee> allEmployees = employeeService.getAll();

        if (departmentId == null) {
            return groupEmployeeByDepartment(allEmployees);
        }

        List<Employee> result = new ArrayList<>();
        for (Employee e : allEmployees) {
            if (e.getDepartment().getId() == departmentId) {
                result.add(e);
            }
        }
        return groupEmployeeByDepartment(result);
    }

    private Map<String, List<Employee>> groupEmployeeByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> employeesByDepartment = new HashMap<>();
        for (Employee e : employees) {
            String departmentName = e.getDepartment().getName();
            if (employeesByDepartment.containsKey(departmentName)) {
                List<Employee> newList = employeesByDepartment.get(departmentName);
                newList.add(e);
                employeesByDepartment.put(departmentName, newList);
            } else {
                List<Employee> newList = new ArrayList<>();
                newList.add(e);
                employeesByDepartment.put(departmentName, newList);
            }
        }

        return employeesByDepartment;
    }

//    public float getTotalSalary() {
//        return 0;
//    }
//
//    public float getAverageSalary() {
//        return 0;
//    }
}
