package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save employee to database
//    public Employee saveEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }

    // Get all employees from database
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get single employee by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setDepartment(updatedEmployee.getDepartment());

        return employeeRepository.save(existing);
    }

    // Find employees by department
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    // Find employee by email
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // Search employees by name
    public List<Employee> searchEmployeesByName(String keyword) {
        return employeeRepository.findByNameContaining(keyword);
    }

    public Employee saveEmployee(EmployeeDto dto){
        Employee employee=new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setEmail(dto.getEmail());

        return employeeRepository.save(employee);
    }

    //Pagination
   public Page<Employee> getEmployees(Pageable pageable){
        return employeeRepository.findAll(pageable);
   }
}