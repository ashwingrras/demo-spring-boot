package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employee")
@Cacheable
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> fetchEmployeeList()
    {
        return employeeService.fetchEmployeeList();
    }

    @PostMapping("/create")
    public Employee createEmployee( @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);

    }

    @GetMapping("fetch/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);

    }

    @PutMapping("update/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employee, id);

    }

    @DeleteMapping("delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id)
    {
        return employeeService.deleteEmpById(id);
    }
}

// http://localhost:8080/api/employee/employees

// http://localhost:8080/api/employee/create

//http://localhost:8080/api/employee/fetch/1

// http://localhost:8080/api/employee/update/1

// http://localhost:8080/api/employee/delete/1
