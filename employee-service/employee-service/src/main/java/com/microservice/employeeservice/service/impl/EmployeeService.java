package com.microservice.employeeservice.service.impl;

import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.exception.ResourceNotFoundException;
import com.microservice.employeeservice.model.Employee;
import com.microservice.employeeservice.repository.IEmployeeRepository;
import com.microservice.employeeservice.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeService implements IEmployeeService {
    private IEmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        Employee savedDepartment = employeeRepository.save(employee);
        return modelMapper.map(savedDepartment, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream().map((employee) -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

}
