# Employee Management System (Spring Boot + JWT)

## 🚀 Overview

A secure backend application built using Spring Boot that provides employee management APIs with JWT-based authentication and role-based authorization.

---

## 🔐 Features

* User Registration & Login
* JWT Authentication (Stateless)
* Password Encryption using BCrypt
* Role-based Authorization (ADMIN / USER)
* CRUD Operations on Employees
* Global Exception Handling
* DTO Pattern Implementation

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* JWT (io.jsonwebtoken)

---

## 🔑 Authentication Flow

1. User registers with username & password
2. Password is encrypted using BCrypt
3. User logs in → receives JWT token
4. Token is sent in Authorization header
5. Server validates token and allows access

---

## 📌 API Endpoints

### 🔐 Auth APIs

* POST /auth/register → Register user
* POST /auth/login → Get JWT token

### 👨‍💼 Employee APIs

* GET /api/employees → Get all employees
* GET /api/employees/{id} → Get employee by ID
* POST /api/employees → Create employee
* PUT /api/employees/{id} → Update employee
* DELETE /api/employees/{id} → Delete employee

---

## 🔒 Security

* Stateless session (JWT)
* No default Spring Security login
* Role-based access control

---

## 🧠 What I Learned

* Implementing JWT authentication
* Securing APIs using Spring Security
* Password hashing using BCrypt
* Designing layered architecture (Controller-Service-Repository)
* Handling exceptions globally

---

## ▶️ How to Run

1. Clone the repository
2. Configure MySQL in application.properties
3. Run the application
4. Use Postman to test APIs

---

## 📬 Author

Vijay
