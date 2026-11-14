# Day 2 — Period 1: Java & Spring Boot (2 Hours)

> **Target Audience:** Non-developers / mixed team  
> **Goal:** Demystify **Java** and **Spring Boot**—what they are, why enterprises rely on them for backends, and how a Spring Boot application is structured internally (Controller → Service → Repository).

---

### Slide 1: Java & Spring Boot — Inside the Backend
- **Slide Points:**
  - **Day 2 — Period 1:** Data & Backend Fundamentals
  - **Focus:** How modern enterprise backends are built using **Java** and **Spring Boot**
  - **What You'll Learn:**
    1. Why **Java** is one of the world's most trusted backend languages
    2. How **Spring Boot** turns Java into a fast REST API server
    3. The **3-Layer Pattern** inside almost every backend (`Controller → Service → Repository`)
- **Talking Points:**
  - *(0:00–0:05)* Welcome to Day 2! Yesterday we looked at the web from the outside—HTML, HTTP, architectures, and calling a FastAPI REST API. Today we step **inside the Backend** and see how enterprise teams build REST APIs with **Java & Spring Boot**.

---

### Slide 2: Bridge from Day 1 — Where Do Java & Spring Boot Live?
- **Slide Points:**
  - In a **Decoupled Web App**:
    - **Frontend (Browser):** Angular / React (HTML, CSS, TypeScript)
    - **Backend (Server):** **Java & Spring Boot** ← *We are here today!*
    - **Database:** PostgreSQL
  - **Spring Boot's Job:** Listen for HTTP requests (`GET`, `POST`, `PUT`, `DELETE`), check business rules, talk to the database, and return **JSON**.
- **Talking Points:**
  - *(0:05–0:10)* Remind participants of the 3 logical layers from Day 1 Period 2: Spring Boot lives in the **Business Logic Layer**—it's the brain sitting between the Frontend and the Database.

---

### Slide 3: What Is Java?
- **Slide Points:**
  - Created in **1995** (by Sun Microsystems, now maintained by Oracle & the open-source community)
  - **Core Philosophy:** *"Write Once, Run Anywhere"*
  - **Key Characteristics:**
    - **Strongly Typed:** Every variable has a clear type (`String`, `int`, `boolean`)
    - **Object-Oriented:** Models real-world concepts (`Employee`, `Order`, `Post`) as reusable blueprints
    - **Compiled & Garbage-Collected:** Fast execution with automatic memory cleanup
- **Talking Points:**
  - *(0:10–0:17)* Clarify a common non-developer confusion: **Java is NOT JavaScript!** They share four letters in their name for 1995 marketing reasons, just like *Car* and *Carpet*. JavaScript runs primarily in browsers; Java is a powerhouse for backend servers.

---

### Slide 4: Why Do Enterprises Love Java?
- **Slide Points:**
  - **1. Stability & Backward Compatibility:** Code written 10 years ago still runs on modern Java today
  - **2. Strict Safety (Catches Bugs Early):** The compiler catches typos and wrong data types *before* the app ever goes live
  - **3. Massive Performance & Scale:** Powers banking systems, **Netflix**, **Amazon**, **LinkedIn**, and **Uber** services
  - **4. Huge Ecosystem & Talent Pool:** Widely taught in schools and universities for **Object-Oriented Programming (OOP)**—almost any university tech student already knows Java, making hiring and onboarding much easier
- **Talking Points:**
  - *(0:17–0:24)* Why do banks, airlines, and large companies pick Java over newer languages? Because when an app handles salaries, invoices, or millions of users, **predictability and long-term support** matter more than hype.

---

### Slide 5: How Java Runs — Source Code → Bytecode → JVM
- **Slide Points:**
  - **Step 1 — Write Code (`Employee.java`):** Human-readable Java source file
  - **Step 2 — Compile (`javac Employee.java`):** Checks for errors and translates it into **Bytecode (`Employee.class`)**
  - **Step 3 — Run on the JVM (`java Employee` or `java -jar app.jar`):**
    - The **JVM (Java Virtual Machine)** is a universal translator installed on Windows, macOS, or Linux
    - Same `.class` / `.jar` file runs identically on a developer's laptop and a Linux cloud server!
  - **`.class` vs. `.jar` (What's the Difference?):**
    - **`.class`:** One single compiled Java file.
    - **`.jar` (Java Archive):** A ZIP-style bundle packaging **all `.class` files + libraries** into one runnable file (`java -jar app.jar`).
- **Talking Points:**
  - *(0:24–0:32)* Analogy: Bytecode is like **sheet music**, and the **JVM** is the **musician** on Windows, Mac, or Linux who knows how to read that exact sheet music and play it anywhere.
  - Point out that when deploying a Spring Boot backend, teams don't copy hundreds of `.class` files—they package everything into a single `.jar` file and run `java -jar app.jar`.

---

### Slide 6: Java Building Blocks — Classes & Objects
- **Slide Points:**
  - **Class = The Blueprint (Template):**
    - Defines what an `Employee` *has* (`firstName`, `email`, `salary`) and what it can *do*
  - **Object = A Real Instance Built From the Blueprint:**
    - `Employee #1`: `("Sarah", "sarah@company.com", 75000)`
    - `Employee #2`: `("Karim", "karim@company.com", 68000)`
  - **One Class, Unlimited Objects:** Just like one architectural blueprint can build 100 houses!
- **Talking Points:**
  - *(0:32–0:40)* Keep this visual and intuitive. You don't need to write classes from scratch—just recognize that when developers say *"the Employee class"* or *"an Order object"*, they mean the template vs. one specific record.

---

### Slide 7: Java Building Blocks — Types, Lists & Methods
- **Slide Points:**
  - **Common Data Types:**
    - `String` → Text (`"Sarah"`)
    - `int` / `long` → Whole numbers (`42`)
    - `double` / `BigDecimal` → Decimals / money (`75000.50`)
    - `boolean` → `true` or `false`
  - **Collections:** `List<Employee>` → A list containing multiple `Employee` objects
  - **Methods:** Named actions (e.g., `calculateBonus()`, `getFullName()`)
- **Talking Points:**
  - *(0:40–0:46)* Point out how readable Java is: `List<Employee>` literally means "a list of Employees." If someone tries to put a number into a `String` field, Java refuses to even compile.

---

### Slide 8: Java Packages vs. Frameworks
- **Slide Points:**
  - **What Is a Java Package?**
    - A **folder / namespace** that groups related Java classes together so files stay organized and names don't clash (e.g., `com.company.ems.service`).
    - Built-in & third-party examples: `java.util` (Lists/Maps), `java.time` (Dates), **Jackson** (JSON), **PDFBox** (PDFs).
  - **Package / Library vs. Framework (Key Difference):**
    - **Package / Library (A Tool *You* Call):** You control the program and call a package when you need one specific job (e.g., formatting a date or generating a PDF).
    - **Framework (The Skeleton That Calls *Your* Code):** Gives you the entire app structure, web server, and wiring—you plug your code into it (e.g., **Spring Boot**, **FastAPI**, **Angular**).
- **Talking Points:**
  - *(0:46–0:52)* Analogy: A **package/library** is like buying a **microwave** for your kitchen (you decide when to press Start). A **framework** is like renting a **fully equipped commercial kitchen** with rules for where every station goes!

---

### Slide 9: What Is a "Framework" & Why Not Plain Java?
- **Slide Points:**
  - **Plain Java** gives you the core language—not the production plumbing every web server needs
  - **Without a Framework, You Would Build From Scratch:**
    - **HTTP Server & Routing:** Listening on ports, parsing URLs, headers, and query params
    - **JSON Serialization & Validation:** Converting Java objects ↔ JSON and checking required fields
    - **Database Drivers:** connect to databases and make queries.
    - **Security & Auth:** Login, change passwords, check permissions.
    - **Caching & Performance:** Storing frequent results in memory to save time.
    - **Logging & periodic Jobs:** Tracking errors and running nightly background tasks
  - **A Framework** gives you all of this pre-built plumbing so developers focus purely on **business features**.
- **Talking Points:**
  - *(0:46–0:53)* Analogy: Plain Java is like lumber and nails; a framework is a pre-framed house with electricity and plumbing already installed.

---

### Slide 10: Popular Java Frameworks & What They Are Used For
- **Slide Points:**
  - **Web & Testing:**
    - **Spring Boot:** The **#1** framework for building enterprise **REST APIs**, web backends, and microservices
    - **Micronaut:** Lightweight framework built for **microservices**
    - **Jakarta EE (formerly Java EE):** Traditional framework used in large banking and government systems
    - **JUnit:** **Automated testing** framework used to verify that backend code works as expected
  - **Desktop & Mobile:**
    - **JavaFX & Swing:** Build **cross-platform desktop apps** (internal tools, POS)
    - **Android SDK:** **Android mobile apps** are built using Java (Kotlin)
- **Talking Points:**
  - *(0:53–0:58)* Java isn't just for servers! **Android** made Java one of the biggest mobile languages in history, and **JavaFX/Swing** still power desktop tools—including JetBrains IDEs like IntelliJ! While **Spring Boot** dominates web backends, it helps to recognize these names when you hear engineers mention them in meetings.

---

### Slide 11: Meet Spring & Spring Boot
- **Slide Points:**
  - **Spring Framework (2003):** Great Java Framework, but required lots of manual setup
  - **Spring Boot (2014):** Adds ready-to-drive convenience on top of Spring:
    - **Smart Defaults:** Replaces hundreds of lines of manual setup with automatic configuration
    - **Built-In Web Server:** Includes a mini web server inside the app—no separate server installation needed
    - **Ready-Made "Starter Packs":** Add one item like *"Web"* or *"Database"* instead of hunting down 15 compatible libraries
    - **One Simple Settings File:** Change ports, database URLs, or passwords in one place (`application.properties`)
    - **Built-In Health & Monitoring:** Instant status checks so cloud servers know the app is alive and healthy
- **Talking Points:**
  - *(0:53–1:00)* Think of **Spring** as the engine and **Spring Boot** as the complete car with keys in the ignition. Almost every new Java backend today is built with **Spring Boot**.

---

### Slide 12: Managing Dependencies — Maven (`pom.xml`) & Gradle
- **Slide Points:**
  - **Why Not Just Run `javac` and `java` Directly?**
    - `javac` / `java` works great for **1 or 2 simple files**, but a real Spring Boot app has **dozens of files and 50+ external libraries**
    - Doing that manually would mean downloading every `.jar` file by hand, fixing version clashes, and typing huge compile commands
  - **What Maven (`mvn`) Does in One Command:**
    - Reads your shopping list in **`pom.xml`** (e.g., `spring-boot-starter-web`, `postgresql`)
    - Automatically downloads all libraries (and the libraries *they* depend on)
    - Compiles every file, runs your tests, and bundles everything into one runnable `.jar` (`mvn spring-boot:run` or `mvn package`)
  - **Spring Initializr (`start.spring.io`):** Web tool to generate a fresh Maven/Gradle project in 10 seconds!
- **Talking Points:**
  - *(1:00–1:07)* Connect to what they saw in Day 1 (`uv` / `pyproject.toml` in Python): in Java, **Maven (`pom.xml`)** or **Gradle (`build.gradle`)** plays that exact same project-manager role so developers never have to juggle `.jar` files manually.

---

### Slide 13: Inside a Spring Boot App — The 3-Layer Pattern
- **Slide Points:**
  - Almost every Spring Boot backend organizes code into **3 clean layers**:
    1. **Controller Layer (The Receptionist):**
       - Receives HTTP requests (`/api/employees`), validates input, returns JSON + status code
    2. **Service Layer (The Manager):**
       - Enforces business rules (e.g., *"Email must be unique"*, *"Calculate salary raise"*)
    3. **Repository Layer (The Archivist):**
       - Talks directly to the Database (saves, finds, updates, deletes rows)
- **Talking Points:**
  - *(1:07–1:16)* This separation keeps code clean and testable. If the company changes how a bonus is calculated, you only touch the **Service**—not the URLs or the SQL!

---

### Slide 14: Layer 1 — The Controller (`@RestController`)
- **Slide Points:**
  - **What It Looks Like in Real Code:**
    ```java
    @RestController
    @RequestMapping("/api/employees")
    public class EmployeeController {

        @GetMapping
        public List<Employee> getAll() {
            return service.findAll();
        }

        @PostMapping
        public Employee create(@RequestBody Employee emp) {
            return service.save(emp);
        }
    }
    ```
  - **Notice:** `@GetMapping` handles `GET`, `@PostMapping` handles `POST`, and Spring Boot **automatically converts** Java objects to/from **JSON**!
- **Talking Points:**
  - *(1:16–1:25)* Walk through this snippet slowly—even non-developers can read `@RequestMapping("/api/employees")`, `@GetMapping`, and `@PostMapping`! Point out how clean it is.

---

### Slide 15: Layer 2 — The Service (`@Service`)
- **Slide Points:**
  - **Why Not Put Everything in the Controller?**
    - Controllers should only care about **HTTP** (URLs, status codes)
    - **Services** hold the **business logic**:
      - Checking permissions or duplicate emails
      - Combining data from multiple tables
      - Triggering notifications or calculations
  - **Example Rule:** *"Before saving a new employee, verify their department exists and email isn't already taken."*
- **Talking Points:**
  - *(1:25–1:32)* Give a relatable example: if a mobile app and a web app both trigger the same business rule, putting that rule in the **Service** guarantees both get the exact same behavior.

---

### Slide 16: Layer 3 — The Repository & JPA (`@Repository` / `@Entity`)
- **Slide Points:**
  - **The Challenge:** Java thinks in **Objects** (`Employee`), while PostgreSQL thinks in **Tables & Rows** (`employees`)
  - **The Bridge — ORM (Object-Relational Mapping) via Spring Data JPA:**
    - **`@Entity`** maps a Java class directly to a database table
    - **`@Repository`** gives you instant database methods with almost zero SQL:
      - `findAll()` → `SELECT * FROM employees`
      - `findById(42)` → `SELECT * FROM employees WHERE id = 42`
      - `save(emp)` → `INSERT` or `UPDATE`
      - `deleteById(42)` → `DELETE`
- **Talking Points:**
  - *(1:32–1:40)* Connect this to yesterday's `models.py` (SQLAlchemy) in Python! Both SQLAlchemy and Spring Data JPA solve the exact same problem: bridging code objects and database tables.

---

### Slide 17: Spring Boot Annotations (`@...`)
- **Slide Points:**
  - You've seen words starting with **`@`** everywhere in Spring Boot:
    - `@SpringBootApplication` → *"Start the whole app here"*
    - `@RestController` → *"This class handles REST API requests"*
    - `@Service` → *"This class holds business logic"*
    - `@Entity` → *"This class is a database table"*
  - **Think of Annotations as Sticky-Note Labels:** They tell Spring Boot what role each class plays without writing pages of setup code.
- **Talking Points:**
  - *(1:40–1:45)* Reassure participants: whenever they peek at Java code and see `@Something`, it's just a declarative label telling Spring Boot how to wire things together.

---

### Slide 18: Configuration (`application.properties` / `application.yml`)
- **Slide Points:**
  - Instead of hardcoding passwords or ports inside Java code, Spring Boot uses **one central config file**:
    ```properties
    server.port=8080
    spring.datasource.url=jdbc:postgresql://localhost:5432/ems_db
    spring.datasource.username=postgres
    spring.datasource.password=secret
    ```
  - **Why It Matters:** Same `.jar` file can run on a developer's laptop, a staging server, or production simply by swapping the config/environment variables!
- **Talking Points:**
  - *(1:45–1:52)* Point out `server.port=8080` and the database URL—they'll recognize these immediately when we connect to PostgreSQL in Period 2 and run Docker on Day 4.

---

### Slide 19: FastAPI (Day 1) vs. Spring Boot (Day 2) — Same Patterns!
- **Slide Points:**
  - **Yesterday's Python/FastAPI Demo** and **Today's Java/Spring Boot** follow the exact same blueprint:
    - **Route / Endpoint:** `@app.get("/posts")` ↔ `@GetMapping("/posts")`
    - **Data Model / Schema:** Pydantic `BaseModel` ↔ Java Class (`Employee`)
    - **Database ORM:** SQLAlchemy `Model` ↔ Spring Data JPA `@Entity`
    - **Auto API Docs:** FastAPI `/docs` ↔ Springdoc OpenAPI (`/swagger-ui.html`)
- **Talking Points:**
  - *(1:52–1:56)* This slide is an "aha!" moment: once you understand REST + 3-layer architecture in one stack, every other modern backend stack looks familiar.

---

### Slide 20: Quick Recap & Bridge to Period 2
- **Slide Points:**
  - **What We Learned:**
    - **Java + JVM:** Strongly typed, reliable enterprise language that runs anywhere
    - **Spring Boot:** Turns Java into a ready-to-run REST API with a built-in web server, starter packs, and Annotations (`@`)
    - **3-Layer Backend:** **Controller** (HTTP/JSON) → **Service** (Business Rules) → **Repository** (Database via JPA)
  - **Coming Up Next (Period 2 — 10:00 to 12:00):** *PostgreSQL & Data Modeling* — stepping into the **Data Layer** to see how tables, primary/foreign keys, and relationships actually work!
- **Talking Points:**
  - *(1:56–2:00)* Wrap up with Q&A and transition smoothly into Period 2 on PostgreSQL and data modeling.
