package com.microservice.departmentservice.service;

import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface IDepartmentService {

    List<DepartmentDto> getAllDepartments();

    DepartmentDto addDepartment(DepartmentDto DepartmentDto);

    DepartmentDto getDepartment(Long id);

    List<DepartmentDto> getAllDepartmentsWithEmployees();
}
