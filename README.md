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


### Day 2: Project Structure & Entity ✅

**What I Built:**
- ✅ Created package structure (controller, service, repository, entity)
- ✅ Created Employee entity class with id, name, email, department
- ✅ Created EmployeeController with REST endpoints
- ✅ Returned dummy employee data (no database yet)

**Key Learnings:**

1. **Entity Class:**
   - Represents a database table (will create actual table on Day 3)
   - Private fields with getters/setters for encapsulation
   - Need default constructor for Spring Boot/JPA

2. **Return Types:**
   - `List<Employee>` returns list of employee objects
   - Spring Boot automatically converts objects to JSON
   - No manual JSON writing needed!

3. **REST API Structure:**
   - `@RequestMapping("/api/employees")` at class level = base path
   - `@GetMapping("/all")` = endpoint path
   - Final URL: `/api/employees/all`

**API Endpoints:**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/hello` | Returns welcome message |
| GET    | `/api/employees/single` | Returns single employee |
| GET    | `/api/employees/all` | Returns list of employees |


1. Spring Boot Architecture ✅

Created proper package structure (entity, controller, service, repository)
Understanding the layers of the application

2. Entity Class ✅

Marks a database table (will create actual table on Day 3)
Properties = columns in the table
Private fields = Encapsulation = Security ✅

3. Constructors ✅

Empty constructor → Spring Boot/JPA needs it for object creation
Parameterized constructor → Easy object creation with values

4. Getters & Setters ✅

Small correction:

Getters → Return/read values (e.g., getName() returns name)
Setters → Set/initialize values (e.g., setName("John") sets name)

5. Controller - Single Employee API ✅

Return type: Employee (single object)
Created Employee object
Returned it → Spring converts to JSON

6. Controller - All Employees API ✅

Return type: List<Employee> (list of objects)
Created ArrayList to hold multiple employees
Added 4 employees
Returned list → Spring converts to JSON array

**Day 2 Status:** ✅ Complete
