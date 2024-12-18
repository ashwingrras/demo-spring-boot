package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

//@Cacheable
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Cacheable(value = "employees", sync = true)
    @Override
    public List<Employee> fetchEmployeeList() {
        return (List<Employee>)
                employeeRepository.findAll();
    }

    @Cacheable(value = "employees", key = "#id")
    // Get
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // delete document by ID 
    //@CacheEvict(value = "employees", allEntries = true)
    @CacheEvict(value = "employees", key = "#id")
    @Override
    public String deleteEmpById(Long id) {
        employeeRepository.deleteById(id);
        return "Employee with ID " + id + " has been deleted successfully.";
    }

    // create new Employee 
    // if any exception comes while create new employee than we will handle that using try catch
    @Cacheable(value = "employees", key = "#employee.id")
    @Override
    public Employee createEmployee(Employee employee) {

        Employee createdEmp = null;
        try {
            System.out.println(" inside createEmployee "+employee.getName());
            createdEmp = employeeRepository.save(employee);
            System.out.println("  createdEmp "+employee.getName()+", "+employee.getId());
        } catch (Exception e) {
            System.out.println(" Exception is " + e);
        }

        return createdEmp;
    }

    // update existing employee detail
    @CachePut(value = "employees", key = "#id")
    @Override
    public Employee updateEmployee(Employee employee, Long id) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setOccupation(employee.getOccupation());

            existingEmployee = employeeRepository.save(existingEmployee);
        }

        return existingEmployee;
    }

}
