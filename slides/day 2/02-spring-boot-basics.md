# Day 2 — Period 2: Spring Boot Basics

> **Target Audience:** Non-developers / mixed team  
> **Goal:** Give a simple, beginner-friendly tour of **Spring Boot**—what it adds on top of Spring, why we use Maven, and how every Spring Boot backend is organized into **Controller → Service → Repository**.

---

### Slide 1: Spring Boot Basics — Building Web Backends Faster
- **Slide Points:**
  - **Day 2 — Period 2:** From Plain Java to a Live REST API
  - **What You'll Learn:**
    1. What **Spring Boot** adds on top of plain Spring
    2. Why we use **Maven (`pom.xml`)** instead of `javac` directly
    3. The **3-Layer Backend Pattern** (`Controller → Service → Repository`)
    4. Sticky-note **Annotations (`@...`)** and **Configuration (`application.properties`)**
- **Talking Points:**
  - *(0:00–0:05)* Now that we understand Java's basics, let's see how **Spring Boot** turns Java into a real web backend—just like the FastAPI server we used on Day 1!

---

### Slide 2: Meet Spring & Spring Boot
- **Slide Points:**
  - **Spring Framework (2003):** The powerhouse Java engine—super flexible, but historically required lots of manual setup
  - **Spring Boot (2014):** Adds ready-to-drive convenience on top of Spring:
    - **Smart Defaults:** Replaces hundreds of lines of setup with automatic configuration
    - **Built-In Web Server:** Includes a mini web server inside the app—no separate server installation needed
    - **Ready-Made "Starter Packs":** Add one bundle like *"Web"* or *"Database"* instead of hunting down 15 compatible libraries
    - **One Simple Settings File:** Change ports, database URLs, or passwords in one place (`application.properties`)
    - **Built-In Health & Monitoring:** Instant status checks so cloud servers know the app is alive
- **Talking Points:**
  - *(0:05–0:12)* Analogy: **Spring** is the engine; **Spring Boot** is the complete car with keys in the ignition.

---

### Slide 3: Why We Use Maven (`pom.xml`) Instead of `javac` Directly
- **Slide Points:**
  - **Why Not Just Run `javac` and `java`?**
    - `javac` / `java` works fine for **1 or 2 simple files**, but a Spring Boot app has **dozens of files and 50+ external libraries**
    - Doing that by hand would mean downloading every `.jar` file manually and fixing version clashes
  - **What Maven (`mvn`) Does in One Command:**
    - Reads your shopping list in **`pom.xml`** (e.g., `spring-boot-starter-web`, `postgresql`)
    - Downloads all libraries automatically, compiles every file, runs tests, and builds one runnable `.jar` (`mvn spring-boot:run` or `mvn package`)
  - **Spring Initializr (`start.spring.io`):** Web tool to generate a fresh project in 10 seconds!
- **Talking Points:**
  - *(0:12–0:19)* Connect to Day 1 (`uv` / `pyproject.toml` in Python): **Maven (`pom.xml`)** is Java's project & library manager.

---

### Slide 4: Inside a Spring Boot App — The 3-Layer Pattern
- **Slide Points:**
  - Almost every Spring Boot backend splits its code into **3 simple roles**:
    1. **Controller (The Receptionist):** Receives HTTP requests (`/api/departments`), returns JSON + status code
    2. **Service (The Manager):** Enforces business rules (e.g., *"Email must be unique"*)
    3. **Repository (The Archivist):** Talks to the Database (saves, finds, updates, deletes rows)
- **Talking Points:**
  - *(0:19–0:26)* Keeping these 3 roles separate makes the code clean and easy to maintain: if a business rule changes, you only edit the **Service**—not the URLs or the database code.

---

### Slide 5: Layer 1 — The Controller (`@RestController`)
- **Slide Points:**
  - **The Front Door of Your Backend:**
    ```java
    @RestController
    @RequestMapping("/api/departments")
    public class DepartmentController {

        @GetMapping
        public List<DepartmentDto> getAllDepartments() {
            return departmentService.getAllDepartments();
        }
    }
    ```
  - **How Spring Knows When to Call It:**
    - `@RequestMapping("/api/departments")` sets the base URL
    - `@GetMapping` listens for HTTP `GET` requests on that URL and automatically turns the returned list into **JSON**!
- **Talking Points:**
  - *(0:26–0:34)* Walk through this real snippet from our project (`DepartmentController.java`): even without knowing Java, you can immediately spot `/api/departments` and `@GetMapping`!

---

### Slide 6: Layer 2 — The Service (`@Service`)
- **Slide Points:**
  - **Where Business Rules Live:**
    - Controllers should only handle **HTTP** (URLs and status codes)
    - **Services** enforce the rules of your business:
      - *"Does an employee with this email already exist?"*
      - *"Does this department ID exist before saving?"*
      - *"Calculate totals, bonuses, or permissions"*
- **Talking Points:**
  - *(0:34–0:40)* Think of the Service as the manager checking the rules before allowing anything to be saved in the database.

---

### Slide 7: Layer 3 — The Repository & Entities (`@Repository` / `@Entity`)
- **Slide Points:**
  - **Connecting Java to SQL Tables:**
    - **`@Entity` (The Table Blueprint):** A Java class mapped directly to a PostgreSQL table (e.g., `Employee` → `employees` table)
    - **`@Repository` (Ready-Made Database Helper):** Gives you instant methods with zero SQL:
      - `findAll()` → get all rows
      - `findById(42)` → get one row
      - `save(emp)` → insert or update
      - `deleteById(42)` → delete
    - **Custom Method Names:** Even `existsByEmailIgnoreCase(email)` writes the SQL check automatically!
- **Talking Points:**
  - *(0:40–0:47)* Connect to Day 1's `models.py` (SQLAlchemy): in Spring Boot, `@Entity` + `@Repository` let developers work with database tables using clean Java methods.

---

### Slide 8: Spring Boot Annotations (`@...`) — Sticky-Note Labels
- **Slide Points:**
  - Words starting with **`@`** act like sticky-note labels telling Spring Boot what each class does:
    - `@SpringBootApplication` → *"Start the whole app here"*
    - `@RestController` → *"This class handles REST API requests"*
    - `@Service` → *"This class holds business rules"*
    - `@Repository` → *"This class talks to the database"*
    - `@Entity` → *"This class represents a database table"*
- **Talking Points:**
  - *(0:47–0:51)* Reassure beginners: whenever you see `@Something` in Spring Boot, it's just a label telling Spring what role that piece plays.

---

### Slide 9: Configuration (`application.properties`)
- **Slide Points:**
  - Instead of hardcoding passwords or ports inside Java code, Spring Boot uses **one central settings file**:
    ```properties
    server.port=8080
    spring.datasource.url=jdbc:postgresql://localhost:5432/ems_db
    spring.datasource.username=postgres
    spring.datasource.password=secret
    ```
  - **Why It Matters:** Same `.jar` file runs on a laptop, test server, or cloud—just swap the settings!
- **Talking Points:**
  - *(0:51–0:55)* Point out `server.port=8080` and the PostgreSQL URL—we'll see databases up close in Period 2!

---

### Slide 10: FastAPI (Day 1) vs. Spring Boot (Day 2) & Next Steps
- **Slide Points:**
  - **Same Ideas, Different Languages:**
    - **Endpoint:** `@app.get("/posts")` (FastAPI) ↔ `@GetMapping("/api/departments")` (Spring Boot)
    - **DB Table Model:** SQLAlchemy `Model` ↔ JPA `@Entity`
    - **Auto Docs:** `/docs` ↔ `/swagger-ui.html`
  - **Up Next:** Hands-on **Java & Spring Boot Tasks** (reading & exploring real code), then **Period 2: PostgreSQL & Data Modeling**!
- **Talking Points:**
  - *(0:55–1:00)* Celebrate how familiar Spring Boot looks once you know REST APIs from Day 1, and transition into the student practice tasks.
