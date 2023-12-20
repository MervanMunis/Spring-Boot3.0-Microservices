package com.microservice.employeeservice.service;

import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployee(Long id);

    List<EmployeeDto> getByDepartmentId(Long departmentId);

}
