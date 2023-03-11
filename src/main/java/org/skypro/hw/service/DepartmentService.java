package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMinSalary(int departmentId);

    Employee getEmployeeWithMaxSalary(int departmentId);

    Map<String, List<Employee>> getAll(Integer departmentId);
}
