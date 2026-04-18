# Employee Management System

REST API built with Spring Boot featuring JWT authentication,
role-based access control, soft delete, audit tracking, and full test coverage.

## Tech stack
Java 17 · Spring Boot 3.3 · Spring Security · JWT · MySQL · JUnit 5 · Mockito · Docker · Swagger/OpenAPI

## Features
- **JWT authentication** — stateless, token-based, role embedded in claim
- **Role-based access control** — ADMIN creates/updates/deletes; USER reads only
- **BCrypt** password hashing on all stored credentials
- **Soft delete** — employees marked `isActive=false`, never permanently removed
- **Audit timestamps** — `createdAt` and `updatedAt` auto-managed on every record
- **Pagination, sorting, keyword search, department filter**
- **Global exception handling** — structured JSON errors with correct HTTP status codes
- **Swagger/OpenAPI** — interactive API docs at `/swagger-ui.html`
- **Full test coverage** — Mockito unit tests + MockMvc integration tests
- **Dockerised** — one command runs the full stack

## Run with Docker
```bash
cp .env.example .env        # fill in DB_PASSWORD and JWT_SECRET
docker compose up --build
# API  → http://localhost:8080
# Docs → http://localhost:8080/swagger-ui.html
```

## Run locally (without Docker)
```bash
# 1. Create a MySQL database named employee_db
# 2. Set env vars: DB_URL, DB_USERNAME, DB_PASSWORD, JWT_SECRET
cd demo && ./mvnw spring-boot:run
```

## Authentication
```
POST /auth/register   {"username":"admin","password":"pass","role":"ADMIN"}
POST /auth/login      {"username":"admin","password":"pass"}
→ copy the JWT from the response

All /api/employees/* require:   Authorization: Bearer <token>
```

## API reference

| Method | Endpoint | Role |
|--------|----------|------|
| POST | /auth/register | Public |
| POST | /auth/login | Public |
| GET | /api/employees | USER, ADMIN |
| GET | /api/employees/{id} | USER, ADMIN |
| GET | /api/employees/search?keyword= | USER, ADMIN |
| GET | /api/employees/department/{dept} | USER, ADMIN |
| GET | /api/employees/paged?page=0&size=10 | USER, ADMIN |
| POST | /api/employees | ADMIN only |
| PUT | /api/employees/{id} | ADMIN only |
| DELETE | /api/employees/{id} | ADMIN only — soft delete |

## Run tests
```bash
cd demo && ./mvnw test
```

## Author
Vijay Krishna Tela — [GitHub](https://github.com/VIJAYAKRISHNATELA) · [LinkedIn](https://www.linkedin.com/in/vijayakrishna-tela)
