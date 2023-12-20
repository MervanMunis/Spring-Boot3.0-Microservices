package com.microservice.departmentservice.client;

import com.microservice.employeeservice.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;


@HttpExchange
public interface IEmployeeClient {

    @GetExchange("/employee/department/{departmentId}")
    public List<Employee> getByDepartmentId(@PathVariable("departmentId") Long departmentId);

}
