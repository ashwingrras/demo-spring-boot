package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "employees")
    public List<Employee> fetchEmployeeList()
    {
        return employeeService.fetchEmployeeList();
    }

    @PostMapping("/create")
    public Employee createEmployee( @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);

    }

    @GetMapping("fetch/{id}")
    @Cacheable(value = "employee", key = "#id")
    //@Cacheable(value = "employee", key = "#id" , unless = "#employee.age > 30")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);

    }

    @PutMapping("update/{id}")
    @CachePut(cacheNames = "employee", key = "#id")
    /*
        @Caching(
             evict = {@CacheEvict(value = "employeeList", allEntries = true)},
             put = {@CachePut(value = "employee", key = "#id")}
        )
     */
    //@Caching( value = "employee", condition = "#employee.name == "ashwin""))
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employee, id);

    }

    @DeleteMapping("delete/{id}")
    @CacheEvict(cacheNames = "employee", key = "#id", beforeInvocation = true)
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
