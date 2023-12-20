package com.microservice.departmentservice.controller;

import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.departmentservice.service.IDepartmentService;
import com.microservice.employeeservice.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private IDepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto){
        LOGGER.info("Adding Department: {}", departmentDto);
        DepartmentDto savedDepartment = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        LOGGER.info("Department find");
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Long id){
        LOGGER.info("Department find: id={}", id);
        DepartmentDto departmentDto = departmentService.getDepartment(id);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping("/with-employees")
    public ResponseEntity<List<DepartmentDto>> getAllDepartmentsWithEmployees(){
        LOGGER.info("Department find");
        List<DepartmentDto> departments = departmentService.getAllDepartmentsWithEmployees();
        return ResponseEntity.ok(departments);
    }
}
