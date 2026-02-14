package com.example.demo.entity;

public class Employee{
    private Long id;
    private String name;
    private String email;
    private String Department;


    public Employee(Long id,String name, String email, String Department){
        this.id=id;
        this.name=name;
        this.email=email;
        this.Department=Department;
    }

    public Employee(){

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getDepartment(){
        return Department;
    }

    public void setDepartment(String Department){
        this.Department=Department;
    }
}