# Employee Management System

Production-grade REST API built with Spring Boot, featuring JWT authentication,
role-based access control, audit tracking, and full test coverage.


## Tech stack
Java 17 · Spring Boot 3.3 · Spring Security · JWT · MySQL · JUnit 5 · Mockito · Docker · Swagger/OpenAPI

## Key features
- JWT authentication with role-based access control (ADMIN creates/updates/deletes; USER reads)
- BCrypt password hashing
- Soft delete with `isActive` flag — records are never lost
- Audit timestamps (`createdAt`, `updatedAt`) on every employee record
- Pagination, sorting, keyword search, department filter
- Global exception handling with structured JSON error responses
- Interactive API docs at `/swagger-ui.html`
- Full unit and integration test coverage (Mockito + MockMvc)
- Dockerised — one command to run the entire stack

## Run with Docker (recommended)
```bash
cp .env.example .env          # fill in DB_PASSWORD and JWT_SECRET
docker compose up --build
# API:   http://localhost:8080
# Docs:  http://localhost:8080/swagger-ui.html
```

## Auth flow
```
POST /auth/register  {"username":"admin","password":"pass","role":"ADMIN"}
POST /auth/login     {"username":"admin","password":"pass"}
→ copy the JWT token from the response
All /api/employees/* endpoints need:  Authorization: Bearer <token>
```

## API endpoints
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /api/employees | USER, ADMIN |
| GET | /api/employees/{id} | USER, ADMIN |
| GET | /api/employees/search?keyword= | USER, ADMIN |
| GET | /api/employees/paged?page=0&size=10 | USER, ADMIN |
| POST | /api/employees | ADMIN only |
| PUT | /api/employees/{id} | ADMIN only |
| DELETE | /api/employees/{id} | ADMIN only (soft delete) |

## Run tests
```bash
cd demo && ./mvnw test
```
