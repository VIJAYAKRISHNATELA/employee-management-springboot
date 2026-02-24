package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save employee to database
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees from database
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get single employee by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Update employee  ← NEW METHOD!
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        // Find existing employee
        Employee existing = employeeRepository.findById(id).orElse(null);

        if (existing == null) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        // Update fields
        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setDepartment(updatedEmployee.getDepartment());

        // Save and return
        return employeeRepository.save(existing);
    }
}