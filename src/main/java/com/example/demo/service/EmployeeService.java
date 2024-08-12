package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames={"employee"})
public interface EmployeeService {

    List<Employee> fetchEmployeeList();

    Employee getEmployeeById(Long id);


    public String deleteEmpById(Long id);

    public Employee createEmployee(Employee employee);

    public Employee updateEmployee(Employee employee, Long id);
}
