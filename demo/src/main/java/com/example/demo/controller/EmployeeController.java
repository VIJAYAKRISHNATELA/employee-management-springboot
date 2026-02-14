package com.example.demo.controller;

import com.example.demo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("/single")

    public Employee getSingleEmployee(){
        Employee emp=new Employee(1L,"John Doe","john@example.com","IT");
        return emp;
    }


@GetMapping("/all")

public List<Employee> getAllEmployee(){
        List<Employee> employees=new ArrayList<>();

        employees.add(new Employee(1L,"John Doe","JohnDoe@example.com","IT"));
        employees.add(new Employee(2L,"Jane smith","janesmith@example.com","HR"));
        employees.add(new Employee(3L,"Mike Johnson","mike@example.com","Finance"));
    employees.add(new Employee(4L,"Sarah ","sarah@example.com","Marketing"));

    return employees;
}
}