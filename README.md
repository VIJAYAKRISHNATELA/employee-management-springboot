# Employee Management System - Spring Boot

## 📚 Day 1: Project Setup & First API ✅

### What I Built:
Created a project using Spring Initializr with the following dependencies:
- Spring Web
- Spring Data JPA  
- Spring MySQL Driver

### Challenge Faced:
When trying to run the code, I got a **database configuration error** because I added Spring MySQL but didn't connect it to an actual database.

**Solution:** Added **H2 in-memory database** as a temporary dependency to run the application without MySQL setup.

### Key Learnings:

**1. @RestController vs @Controller:**
- `@RestController` = `@Controller` + `@ResponseBody`
- `@RestController` returns the method string directly in HTTP response
- `@Controller` is useful for web pages (returns view names)
- **For RESTful APIs, we definitely need @RestController**

**2. Git Version Control:**
- Initialized a repository for version control to track changes
- Used `git add .` to add all files to the staging area
- Used `git commit` to commit the changes
- Created a GitHub repository to connect the local project repo to GitHub

---

## 🛠️ Technologies Used
- Java 25
- Spring Boot 4.0.2
- Spring Web
- Spring Data JPA
- H2 Database (temporary)
- MySQL (will configure on Day 3)

---

## 🚀 API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/hello` | Returns welcome message |

---

## 📅 21-Day Learning Plan Status
- [x] Day 1: Setup + First API
- [ ] Day 2: Project Structure + Entity
- [ ] Day 3-21: More features coming...

---

**Author:** Vijayakrishna Tela  
**Status:** Final Year Student | Learning Spring Boot | Seeking Entry-Level Opportunities
