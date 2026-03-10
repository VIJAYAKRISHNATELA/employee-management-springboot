package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="employees")
public class Employee{


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message="Name cannot be empty")
@Column(name="name",nullable = false,length = 100)
private String name;

@Email(message = "Email must be valid")
@NotBlank(message="Email cannot be empty")
@Column(name="email",nullable = false,unique = true)
private String email;

@NotBlank(message = "Department cannot be empty")
@Column(name="department",nullable = false)
private String department;

    // Constructor with all fields
    public Employee(Long id, String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    // Default constructor
    public Employee() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }
}