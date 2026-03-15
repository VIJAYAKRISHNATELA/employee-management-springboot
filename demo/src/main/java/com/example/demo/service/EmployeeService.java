package com.example.demo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    //Logger
private static final Logger log=LoggerFactory.getLogger(EmployeeService.class);

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
        log.info("Fetching employee with id {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id {}", id);
                    return new EmployeeNotFoundException("Employee not found with id: " + id);
                });
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id {}",id);
        employeeRepository.deleteById(id);
        log.info("Employee deleted successfullly eith id{}",id);
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

        log.info("Creating new employee with email: {}", dto.getEmail());

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setEmail(dto.getEmail());

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee created successfully with id: {}", savedEmployee.getId());

        return savedEmployee;
    }

    //Pagination
   public Page<Employee> getEmployees(Pageable pageable){
        return employeeRepository.findAll(pageable);
   }

   public List<Employee> getEmployeesSortedBy(String field){
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC,field));
   }

    public List<Employee> getEmployeesSorted(String field, String direction) {

        if(direction.equalsIgnoreCase("desc")){
            return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, field));
        }

        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

}