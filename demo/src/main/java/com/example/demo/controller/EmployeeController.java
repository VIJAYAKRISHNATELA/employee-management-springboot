package com.example.demo.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create/Save employee to database
    @PostMapping
    public Employee createEmployee(@Valid @RequestBody EmployeeDto dto) {
        return employeeService.saveEmployee(dto);
    }

    // Get all employees from database
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get single employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully!";
    }

    // Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/paged")
    public Page<Employee> getEmployees(Pageable pageable){
        return employeeService.getEmployees(pageable);
    }

    @GetMapping("/sort/{field}")
    public List<Employee> getEmployeesSorted(@PathVariable String field){
        return employeeService.getEmployeesSortedBy(field);
    }
    @GetMapping("/sort")
    public List<Employee> getSortedEmployees(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return employeeService.getEmployeesSorted(field, direction);
    }


}