package com.microservice.departmentservice.service.impl;

import com.microservice.departmentservice.client.IEmployeeClient;
import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.departmentservice.exception.ResourceNotFoundException;
import com.microservice.departmentservice.model.Department;
import com.microservice.departmentservice.repository.IDepartmentRepository;
import com.microservice.departmentservice.service.IDepartmentService;
import com.microservice.employeeservice.model.Employee;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private IDepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    private IEmployeeClient employeeClient;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + id));
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartmentsWithEmployees() {
        List<Department> departments = departmentRepository.findAll();

        departments.forEach((department) ->
                department.setEmployeeString(
                        employeeClient.getByDepartmentId(department.getId()).stream()
                                .map(Employee::getPosition).toString()));

        return departments.stream().map((department) -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }
}
