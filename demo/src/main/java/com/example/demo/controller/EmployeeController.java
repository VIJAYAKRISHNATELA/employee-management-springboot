package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Employees", description = "Employee management APIs")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @PostMapping
    @Operation(summary = "Create a new employee (ADMIN only)")
    public Employee createEmployee(@Valid @RequestBody EmployeeDto dto) {
        return employeeService.saveEmployee(dto);
    }

    @Operation(summary = "Get all active employees")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully!";
    }



    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDto dto) {   // ← was Employee
        Employee updated = employeeService.updateEmployee(id, dto);
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

    @GetMapping("/department/{dept}")
    public List<Employee> getByDepartment(@PathVariable String dept){
        return employeeService.getEmployeesByDepartment(dept);
    }

    @GetMapping("/email")
        public Employee getByEmail(@RequestParam String email){
            return employeeService.getEmployeeByEmail(email);
        }

     @GetMapping("/search")
    public List<Employee> search(@RequestParam String keyword){
        return employeeService.searchEmployeesByName(keyword);
     }


}