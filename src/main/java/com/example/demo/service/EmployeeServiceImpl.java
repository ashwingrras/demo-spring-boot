package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> fetchEmployeeList() {
        return (List<Employee>)
                employeeRepository.findAll();
    }

    // Get
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // delete document by ID 
    @Override
    public String deleteEmpById(Long id) {
        employeeRepository.deleteById(id);
        return "Employee with ID " + id + " has been deleted successfully.";
    }

    // create new Employee 
    // if any exception comes while create new employee than we will handle that using try catch
    @Override
    public Employee createEmployee(Employee employee) {

        Employee createdEmp = null;
        try {
            createdEmp = employeeRepository.save(employee);
        } catch (Exception e) {
            System.out.println(" Exception is " + e);
        }

        return createdEmp;
    }

    // update existing employee detail
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
