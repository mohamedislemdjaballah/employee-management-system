# Spring Boot Demo Setup Guide (From `start.spring.io` to Running REST API)

This step-by-step guide walks through creating, importing, building, and testing the **Company Demo REST API** from scratch — using a normal Java `ArrayList` instead of a database so you can focus on Models, Controllers, and HTTP endpoints.

---

## Step 1: Generate the Project on `https://start.spring.io/`

Open [https://start.spring.io/](https://start.spring.io/) and select:

| Setting | Value to Select | Why |
| :--- | :--- | :--- |
| **Project** | **Maven** | Manages dependencies and builds the project (`pom.xml`) |
| **Language** | **Java** | Programming language |
| **Spring Boot** | Latest stable version (not `SNAPSHOT`) | Stable release |
| **Group** | `com.example` | Base package |
| **Artifact** | `demo` | Project folder & app name |
| **Name** | `demo` | Display name |
| **Package name** | `com.example.demo` | Root Java package |
| **Packaging** | **Jar** | Standard runnable Spring Boot app |
| **Java** | `25` (or your installed JDK, e.g. `21`) | Matches your local JDK |

### Dependencies to Add
Click **ADD DEPENDENCIES...** and add:
1. **Spring Web** — **Required** (gives you `@RestController`, HTTP endpoints, JSON conversion, and the built-in Tomcat server on port `8080`).
2. **Spring Boot DevTools** — *Optional* (auto-restarts the app when you save changes).

> **Important:** Do **NOT** add Spring Data JPA or any database driver. We store data in an `ArrayList` in memory.

Click **GENERATE** and extract the downloaded `demo.zip` file.

---

## Step 2: Import the Project into Eclipse

In Eclipse, you import the folder from the UI (do not use right-click "Open with"):

1. Open **Eclipse** → **File** → **Import...**
2. Expand **Maven** → select **Existing Maven Projects** → click **Next**.
3. Click **Browse...** next to **Root Directory** and select the extracted `demo` folder (the folder containing `pom.xml`).
4. Make sure `/pom.xml` is checked, then click **Finish**.
5. Wait a few seconds (bottom-right progress bar) for Maven to download dependencies.

---

## Step 3: Project Structure

Inside `src/main/java/com/example/demo/`, we keep the structure simple with **2 folders**:

```text
src/main/java/com/example/demo/
├── DemoApplication.java             # Entry point (main) + holds our ArrayLists
├── model/                           # Plain Java classes (data shapes)
│   ├── Employee.java                # Guided Example 1
│   ├── Department.java              # Guided Example 2
│   └── Task.java                    # Student Exercise
└── controller/                      # REST API endpoints (@RestController)
    ├── EmployeeController.java      # Guided Example 1 (/api/employees)
    ├── DepartmentController.java    # Guided Example 2 (/api/departments)
    └── TaskController.java          # Student Exercise (/api/tasks)
```

---

## Step 4: Create the Models (`model/`)

We use regular Java classes with `public` fields and two constructors (an empty constructor for JSON `POST` requests, and a full constructor for creating sample items).

### 1. `model/Employee.java`
```java
package com.example.demo.model;

public class Employee {
    public int id;
    public String name;
    public String role;
    public int departmentId;

    public Employee() {
    }

    public Employee(int id, String name, String role, int departmentId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.departmentId = departmentId;
    }
}
```

### 2. `model/Department.java`
```java
package com.example.demo.model;

public class Department {
    public int id;
    public String name;
    public String location;

    public Department() {
    }

    public Department(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
```

---

## Step 5: Store & Pre-Fill the Lists in `DemoApplication.java`

Open `DemoApplication.java` and add `public static ArrayList<...>` fields so all sample data starts in one place inside `main()`:

```java
package com.example.demo;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Department> departments = new ArrayList<>();

    public static void main(String[] args) {
        departments.add(new Department(1, "Engineering", "Floor 3"));
        departments.add(new Department(2, "Design", "Floor 2"));

        employees.add(new Employee(1, "Alice", "Developer", 1));
        employees.add(new Employee(2, "Bob", "Designer", 2));

        SpringApplication.run(DemoApplication.class, args);
    }
}
```

---

## Step 6: Create the Controllers (`controller/`)

Each controller handles HTTP requests (`GET`, `POST`, `DELETE`) using `@RestController`, `@ResponseStatus`, and `for-each` loops.

### 1. `controller/EmployeeController.java`
```java
package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private int idCounter = 3;

    // GET http://localhost:8080/api/employees
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Employee> getAllEmployees() {
        return DemoApplication.employees;
    }

    // GET http://localhost:8080/api/employees/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable int id) {
        for (Employee employee : DemoApplication.employees) {
            if (employee.id == id) {
                return employee;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // POST http://localhost:8080/api/employees
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.id = idCounter;
        idCounter = idCounter + 1;
        DemoApplication.employees.add(employee);
        return employee;
    }

    // DELETE http://localhost:8080/api/employees/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        for (Employee employee : DemoApplication.employees) {
            if (employee.id == id) {
                DemoApplication.employees.remove(employee);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
```

### 2. `controller/DepartmentController.java`
Follows the exact same pattern for `DemoApplication.departments` at `/api/departments`.

---

## Step 7: Run & Test the API

1. In Eclipse, right-click **`DemoApplication.java`** → **Run As** → **Java Application** (or **Spring Boot App**).
2. The server starts on port **`8080`** by default.
3. Test the endpoints:
   * **Browser / `GET`:**
     * `http://localhost:8080/api/employees`
     * `http://localhost:8080/api/employees/1`
     * `http://localhost:8080/api/departments`
   * **Postman / `curl` `POST`:**
     ```bash
     curl -X POST http://localhost:8080/api/employees \
       -H "Content-Type: application/json" \
       -d '{"name":"Sarah","role":"Manager","departmentId":1}'
     ```
   * **Postman / `curl` `DELETE`:**
     ```bash
     curl -X DELETE http://localhost:8080/api/employees/1
     ```

---

## Step 8: Student Exercise — Add the `Task` Resource

Now it's your turn! Using `Employee` and `Department` as your guide, add a **`Task`** resource from scratch.

### Requirements
1. **Create `model/Task.java`:**
   - Fields (`public`):
     - `int id`
     - `String title`
     - `String status` *(e.g. `"TODO"`, `"IN_PROGRESS"`, `"DONE"`)*
     - `int employeeId`
   - Constructors:
     - Empty constructor: `public Task() {}`
     - Full constructor: `public Task(int id, String title, String status, int employeeId)`

2. **Update `DemoApplication.java`:**
   - Add `public static ArrayList<Task> tasks = new ArrayList<>();`
   - Add 2 sample tasks inside `main()` (with IDs `1` and `2`).

3. **Create `controller/TaskController.java`:**
   - Base path: `/api/tasks`
   - `private int idCounter = 3;`
   - Implement all 4 endpoints using `@ResponseStatus`, `for-each` loops, and `ResponseStatusException(HttpStatus.NOT_FOUND)`:
     - `GET /api/tasks` → return all tasks (`200 OK`)
     - `GET /api/tasks/{id}` → return one task by ID (`200 OK`), or `404 Not Found`
     - `POST /api/tasks` → assign `idCounter`, increment `idCounter`, add to `DemoApplication.tasks`, return the created task (`201 Created`)
     - `DELETE /api/tasks/{id}` → remove task by ID (`204 No Content`), or `404 Not Found`

### Test Checklist
- [ ] `GET http://localhost:8080/api/tasks` returns your 2 sample tasks as JSON.
- [ ] `GET http://localhost:8080/api/tasks/1` returns task `1`, and `/api/tasks/99` returns `404`.
- [ ] `POST http://localhost:8080/api/tasks` with `{"title":"Learn Spring Boot","status":"TODO","employeeId":1}` returns `201 Created` with `"id": 3`.
- [ ] `DELETE http://localhost:8080/api/tasks/1` returns `204 No Content`, and task `1` is gone from `GET /api/tasks`.

### Bonus Challenge
- Add **`PUT /api/tasks/{id}`** to update a task's `title` or `status` (e.g. from `"TODO"` to `"DONE"`).
