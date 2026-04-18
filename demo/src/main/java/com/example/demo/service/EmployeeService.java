package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findByIsActiveTrue();
    }
    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee with id {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id {}", id);
                    return new EmployeeNotFoundException("Employee not found with id: " + id);
                });
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        employee.setActive(false);
        employeeRepository.save(employee);
        log.info("Employee soft-deleted with id {}", id);
    }

    public Employee updateEmployee(Long id, EmployeeDto dto) {  // ← was Employee
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setDepartment(dto.getDepartment());
        existing.setSalary(dto.getSalary());
        existing.setJoinDate(dto.getJoinDate());
        return employeeRepository.save(existing);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public Employee getEmployeeByEmail(String email) {

        return employeeRepository.findByEmail(email);
    }

    public List<Employee> searchEmployeesByName(String keyword) {
        return employeeRepository.findByNameContaining(keyword);
    }

    public Employee saveEmployee(EmployeeDto dto){

        log.info("Creating new employee with email: {}", dto.getEmail());

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setJoinDate(dto.getJoinDate() != null ? dto.getJoinDate() : LocalDate.now());


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