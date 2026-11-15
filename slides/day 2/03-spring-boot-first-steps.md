# Spring Boot First Steps: Generating & Structuring a REST API (No Database)

This guide walks through creating a simple Spring Boot REST API from scratch using [start.spring.io](https://start.spring.io/).  
Instead of a database, we store data in a normal Java `List` (`ArrayList`) in memory so you can focus on how REST APIs, models, services, and controllers work.

---

## Step 1: Generate the Project on `https://start.spring.io/`

Open [https://start.spring.io/](https://start.spring.io/) and choose the following settings:

| Setting | Value to Select | Why |
| :--- | :--- | :--- |
| **Project** | **Maven** | Manages dependencies and builds the project (`pom.xml`) |
| **Language** | **Java** | Programming language |
| **Spring Boot** | Latest stable version (e.g. `3.x` or `4.x`, not `SNAPSHOT`) | Stable release |
| **Group** | `com.example` | Base organization package |
| **Artifact** | `demo` | Project folder & app name |
| **Name** | `demo` | Display name |
| **Package name** | `com.example.demo` | Root Java package |
| **Packaging** | **Jar** | Standard runnable Spring Boot app |
| **Java** | `21` (or `25` if installed) | Matches your local JDK |

### Dependencies to Add
Click **ADD DEPENDENCIES...** and add:

1. **Spring Web** (`spring-boot-starter-web`) — **Required**
   - Gives you `@RestController`, HTTP endpoints (`GET`, `POST`, `PUT`, `DELETE`), automatic JSON conversion, and the built-in Tomcat web server.
2. **Spring Boot DevTools** — *Optional*
   - Automatically restarts the app when you save changes.

> **Important:** Do **NOT** add Spring Data JPA, H2, PostgreSQL, or MySQL. We are storing data in a simple Java `List` in memory.

Click **GENERATE**, extract the downloaded `.zip` file, and open the extracted `demo` folder in your IDE (VS Code or IntelliJ).

---

## Step 2: Understand the Generated Folder Structure

```text
demo/
├── pom.xml                                  # Project config & dependencies (check for spring-boot-starter-web)
├── mvnw / mvnw.cmd                          # Maven wrapper to run the project without installing Maven
├── HELP.md                                  # Useful Spring reference links
└── src/
    ├── main/
    │   ├── java/com/example/demo/
    │   │   └── DemoApplication.java         # Entry point with main() and @SpringBootApplication
    │   └── resources/
    │       └── application.properties       # App settings (e.g., server.port=8080)
    └── test/
        └── java/com/example/demo/
            └── DemoApplicationTests.java    # Basic startup test
```

---

## Step 3: Target Structure (Folders & Files to Add)

Inside `src/main/java/com/example/demo/`, organize your code into 3 folders (packages):

```text
src/main/java/com/example/demo/
├── DemoApplication.java
├── model/                  # Plain Java classes (data shapes)
│   ├── Employee.java       # Resource 1 (guided)
│   ├── Department.java     # Resource 2 (guided)
│   └── Task.java           # Resource 3 (student exercise)
├── service/                # Holds the Java List<...> and logic
│   ├── EmployeeService.java
│   ├── DepartmentService.java
│   └── TaskService.java    # Student exercise
└── controller/             # REST API endpoints (@RestController)
    ├── EmployeeController.java
    ├── DepartmentController.java
    └── TaskController.java # Student exercise
```

---

## Step 4: The API Resources

We will build **2 resources together** as examples, and leave a **3rd resource** for students to implement on their own.

### Resource 1 (Guided): `Employee` — `/api/employees`
- **Model fields:** `id` (`Long`), `name` (`String`), `role` (`String`), `departmentId` (`Long`)
- **Storage:** `private final List<Employee> employees = new ArrayList<>();` inside `EmployeeService`
- **Endpoints:**
  - `GET /api/employees` — List all employees
  - `GET /api/employees/{id}` — Get one employee by ID
  - `POST /api/employees` — Add a new employee to the list
  - `DELETE /api/employees/{id}` — Remove an employee from the list

### Resource 2 (Guided): `Department` — `/api/departments`
- **Model fields:** `id` (`Long`), `name` (`String`), `location` (`String`)
- **Storage:** `private final List<Department> departments = new ArrayList<>();` inside `DepartmentService`
- **Endpoints:**
  - `GET /api/departments` — List all departments
  - `GET /api/departments/{id}` — Get one department by ID
  - `POST /api/departments` — Add a new department to the list
  - `DELETE /api/departments/{id}` — Remove a department from the list

### Resource 3 (Student Exercise): `Task` — `/api/tasks`
Students follow the exact same `model` -> `service` -> `controller` pattern to build task tracking for employees:
- **Model fields:** `id` (`Long`), `title` (`String`), `status` (`String` — e.g. `"TODO"`, `"IN_PROGRESS"`, `"DONE"`), `employeeId` (`Long`)
- **Storage:** `private final List<Task> tasks = new ArrayList<>();` inside `TaskService`
- **Required Endpoints:**
  - `GET /api/tasks` — List all tasks
  - `GET /api/tasks/{id}` — Get one task by ID
  - `POST /api/tasks` — Create a new task
  - `DELETE /api/tasks/{id}` — Delete a task
- **Bonus Challenge:**
  - `PUT /api/tasks/{id}` — Update a task's `status` (e.g., from `"TODO"` to `"DONE"`)
  - `GET /api/tasks?employeeId=1` — Filter tasks assigned to a specific employee

---

## Step 5: Run and Test the Project

1. Open a terminal inside the `demo/` folder and run:
   - **Linux / macOS:** `./mvnw spring-boot:run`
   - **Windows:** `mvnw.cmd spring-boot:run`
2. Once started, the API is available at `http://localhost:8080`:
   - Open `http://localhost:8080/api/employees` in your browser to test `GET`.
   - Use Postman, Thunder Client (VS Code), or `curl` to test `POST` and `DELETE`.
