# Instructor Daily Teaching Playbook (5-Day Conceptual Guide)
**Target:** 7 Senior Enterprise Engineers  
**Focus:** Understanding Pre-Existing Decoupled Enterprise Systems  
**Stack:** Spring Boot 3 (Eclipse) + PostgreSQL (TablePlus) + Angular 18 (VS Code)

---

## Daily Schedule Structure (8:30 AM – 4:30 PM)

| Time Slot | Format | Focus |
| :--- | :--- | :--- |
| **08:30 – 10:00** | Interactive Lecture (Presentation Slides) | Core architectural concepts & mental models |
| **10:00 – 10:15** | *Coffee Break* | Informals, answering questions |
| **10:15 – 12:00** | Live System Walkthrough & Code Inspection | Opening Eclipse / TablePlus / VS Code, tracing files |
| **12:00 – 13:00** | *Lunch Break* | |
| **13:00 – 15:00** | Guided Hands-on Lab & Exploration | Students trace existing code & test API endpoints |
| **15:00 – 15:15** | *Short Break* | |
| **15:15 – 16:30** | Debrief, "What If" Scenarios & Git Save-Point | Discussing edge cases, questions, switching Git branch |

---

## DAY 1: Decoupled Architecture, REST APIs & Microservices

### 1. Instructor Mental Model & Analogy
- **The Restaurant Analogy:**
  - **PostgreSQL** is the raw food pantry / storage room.
  - **Spring Boot** is the kitchen: it holds recipes (business logic), cooks food safely, and guarantees hygiene (validation & security).
  - **Angular** is the dining room and waitstaff: it presents the menu nicely and serves food to the customer.
  - *Key Rule:* The customer (Browser) never walks into the kitchen or pantry directly. Everything passes through the waiter (HTTP REST API).
- **Monolith vs Microservices:**
  - Monolith = One big restaurant kitchen with all chefs under one roof.
  - Microservices = A food court with 5 separate specialized shops (Burger shop, Pizza shop, Drink shop). Great if you have 50 chefs and 1,000 customers/min, but much more coordination overhead.

### 2. Live Demo Script (Step-by-Step)
1. Double-click `start-all.bat` on your laptop projector screen.
2. Show that **two separate ports** are running:
   - `http://localhost:4200` (Angular)
   - `http://localhost:8080` (Spring Boot)
3. Open **TablePlus** and show the database running on port `5432`.
4. Open **Swagger UI** (`http://localhost:8080/swagger-ui.html`) and execute `GET /api/employees`.
5. Point out the raw JSON returned and say:
   > *"Notice that Spring Boot didn't send HTML, colors, or fonts. It sent raw data. Now look at port 4200: Angular is consuming this exact same JSON to draw cards, tables, and buttons."*

### 3. Key Files to Inspect Together
- In **Eclipse**:
  - [`backend/src/main/resources/application.yml`](../backend/src/main/resources/application.yml) $\rightarrow$ Show database URL, credentials, and port `8080`.
  - [`backend/src/main/java/com/finances/ems/controller/EmployeeController.java`](../backend/src/main/java/com/finances/ems/controller/EmployeeController.java) $\rightarrow$ Point at `@RestController`, `@GetMapping`, and `@PostMapping`.
- In **Browser**:
  - Open Chrome DevTools (`F12`) $\rightarrow$ Network tab $\rightarrow$ Click Refresh on Angular $\rightarrow$ Show the `GET /api/employees` HTTP request on the wire.

### 4. Guided Discussion Questions for the 7 Engineers
- *"Why can't we let Angular connect directly to PostgreSQL via JDBC?"*  
  *(Answer: Security! Any user can press F12 and see database passwords in JavaScript).*
- *"What is the difference between an RPC style API `/deleteEmployee?id=3` and REST `/api/employees/3` with HTTP DELETE?"*

---

## DAY 2: What an ORM Does & Database Persistence (JPA / Hibernate)

### 1. Instructor Mental Model & Analogy
- **The Translator Analogy:**
  - Java thinks in **Object Graphs** (an `Employee` object has a reference to a `Department` object).
  - PostgreSQL thinks in **Flat Relational Tables** (rows, columns, foreign keys).
  - An ORM (JPA/Hibernate) is a tireless bilingual translator: it turns Java objects into SQL rows and SQL rows into Java objects automatically.

### 2. Live Demo Script (Step-by-Step)
1. Open **TablePlus** side-by-side with **Eclipse**.
2. Show `Employee.java` in Eclipse:
   - Point to `@Entity`, `@Table(name = "employees")`, and `@Id`.
3. Switch to TablePlus:
   - Show the table `employees` and explain how each Java property matches a SQL column.
4. Point to `@ManyToOne` in `Employee.java`:
   - Show how Java holds a `Department` object, while TablePlus holds a `department_id` foreign key.
5. In Swagger UI, execute `POST /api/employees` to insert a new employee.
6. Switch to Eclipse Console:
   - Show the SQL `INSERT INTO employees ...` logged by Hibernate.
7. Switch to TablePlus:
   - Refresh the table to show the new record!

### 3. Key Files to Inspect Together
- In **Eclipse**:
  - [`Department.java`](../backend/src/main/java/com/finances/ems/entity/Department.java) & [`Employee.java`](../backend/src/main/java/com/finances/ems/entity/Employee.java)
  - [`EmployeeRepository.java`](../backend/src/main/java/com/finances/ems/repository/EmployeeRepository.java) $\rightarrow$ Show `existsByEmailIgnoreCase` and explain how Spring derives SQL from method names!
  - [`EmployeeService.java`](../backend/src/main/java/com/finances/ems/service/EmployeeService.java) $\rightarrow$ Point to `@Transactional`.

### 4. Guided Discussion Questions for the 7 Engineers
- *"What happens if the second database operation in a `@Transactional` method fails?"*  
  *(Answer: Spring catches the exception and issues a `ROLLBACK` to PostgreSQL).*
- *"Why do we use `record EmployeeResponseDto` instead of sending `@Entity Employee` directly to Angular?"*  
  *(Answer: Decoupling! If you alter a database column name, the JSON contract remains stable).*

---

## DAY 3: Frontend SPA Architecture & Async vs Sync (JavaScript vs Java)

### 1. Instructor Mental Model & Analogy
- **The Post Office Counter vs The Fast-Food Buzzer:**
  - **Java (Sync / Multi-Threaded):** Like an enterprise office with 100 dedicated counter agents. Agent #1 handles customer #1. If customer #1 takes 10 seconds to read a paper, Agent #1 waits patiently. Other agents handle other customers.
  - **JavaScript in Browser (Async / Single-Threaded):** Like a single-cashier burger restaurant with 100 customers. The cashier cannot wait 5 minutes while the kitchen cooks your burger—the whole line would freeze! Instead, the cashier gives you a **buzzer** (an `Observable` / `Promise`), serves the next customer, and when your burger is ready, the buzzer rings (Callback / `.subscribe()`).

### 2. Live Demo Script (Step-by-Step)
1. Open **VS Code** $\rightarrow$ `frontend/src/app/components/employee-list/employee-list.component.ts`.
2. Add the 3 `console.log` statements around `.subscribe()`:
   ```typescript
   console.log("== 1. Sending HTTP Request ==");
   this.employeeService.getEmployees().subscribe({
     next: (data) => console.log("== 2. JSON Data Arrived! ==")
   });
   console.log("== 3. Method finished ==");
   ```
3. Open browser console (`F12`), reload page:
   - Show students the output: `1`, then `3`, then `2`!
   - Explain why: JavaScript did **not wait** for the network; it scheduled the callback and kept the UI alive!

### 3. Key Files to Inspect Together
- In **VS Code**:
  - [`frontend/src/index.html`](../frontend/src/index.html) $\rightarrow$ Show the empty `<app-root></app-root>` tag.
  - [`frontend/src/app/app.routes.ts`](../frontend/src/app/app.routes.ts) $\rightarrow$ Client-side routing table.
  - [`frontend/src/app/components/employee-list/employee-list.component.html`](../frontend/src/app/components/employee-list/employee-list.component.html) $\rightarrow$ Control flow (`@for`, `@if`).
  - [`frontend/src/app/services/employee.service.ts`](../frontend/src/app/services/employee.service.ts) $\rightarrow$ `@Injectable` and `HttpClient`.

### 4. Guided Discussion Questions for the 7 Engineers
- *"If you navigate from `/employees` to `/employees/new`, did the browser request a new HTML page from a web server?"*  
  *(Answer: No! Angular swapped the component in browser RAM).*
- *"What happens if you don't call `.subscribe()` on an Angular `http.get()`?"*  
  *(Answer: Nothing! Observables are lazy; without a subscriber, no network request is sent).*

---

## DAY 4: End-to-End Request Lifecycle & "The Code Detective"

### 1. Instructor Mental Model & Analogy
- **The Bank Teller & Security Vault:**
  - The browser form is the teller counter where a customer fills out a deposit slip.
  - The teller (Angular) checks if you signed the slip (Client Validation).
  - The security guard and vault officer (Spring Boot `@Valid` & Service) independently verify the money before putting it in the safe (PostgreSQL).
  - If a hacker brings a forged slip bypassing the teller, the vault officer still catches it!

### 2. Live Demo Script (Step-by-Step)
1. Open the Employee Form at `http://localhost:4200/employees/new`.
2. Open Chrome DevTools (`F12`) $\rightarrow$ Network tab.
3. Submit a form with an invalid email:
   - Show how Angular displays red validation text immediately (Client Validation).
4. Now open **Swagger UI** on port `8080`:
   - Send the same invalid email directly to `POST /api/employees`.
   - Show the `400 Bad Request` with field errors from `GlobalExceptionHandler.java` (Server Validation).
5. Open TablePlus:
   - Prove that no row was created.
6. Now submit valid data from Angular:
   - Trace the `POST` in DevTools Network tab.
   - Trace the `INSERT` in Eclipse console.
   - Trace the new row in TablePlus.

### 3. Key Files to Inspect Together
- In **VS Code**:
  - [`employee-form.component.ts`](../frontend/src/app/components/employee-form/employee-form.component.ts)
  - [`employee-form.component.html`](../frontend/src/app/components/employee-form/employee-form.component.html)
- In **Eclipse**:
  - [`EmployeeRequestDto.java`](../backend/src/main/java/com/finances/ems/dto/EmployeeRequestDto.java)
  - [`GlobalExceptionHandler.java`](../backend/src/main/java/com/finances/ems/exception/GlobalExceptionHandler.java)
  - [`EmployeeService.java`](../backend/src/main/java/com/finances/ems/service/EmployeeService.java)

### 4. Guided Discussion Questions for the 7 Engineers
- *"If a developer needs to add a 'birthDate' field to an existing application, what is the exact order of files they must edit?"*  
  *(Answer: Database table $\rightarrow$ Entity $\rightarrow$ DTOs $\rightarrow$ Service mapping $\rightarrow$ TypeScript interface $\rightarrow$ HTML Form).*

---

## DAY 5: Enterprise Security & Stateless Authentication (JWT)

### 1. Instructor Mental Model & Analogy
- **The Hotel Keycard vs The Airport Boarding Pass:**
  - **Stateful Session (Hotel Keycard):** When you lose your keycard, the hotel clerk checks their computer database of registered rooms. The server must remember every active guest.
  - **Stateless Token (Airport Boarding Pass with QR Code):** The gate agent's scanner doesn't maintain a database of 100,000 passengers in RAM. It simply scans the digitally signed barcode on your ticket to mathematically verify its authenticity.

### 2. Live Demo Script (Step-by-Step)
1. Open `http://localhost:4200` $\rightarrow$ Click **Atelier Sécurité (Jour 5)**.
2. Open Chrome DevTools (`F12`) $\rightarrow$ Network tab.
3. Log in with `admin` / `finances2026`:
   - Inspect the `POST /api/auth/login` response in DevTools.
   - Show the token string: `eyJhbGciOiJIUzI1Ni...`
4. Copy the token into **jwt.io** (or decode in browser console using `atob()`):
   - Show the students the Header, the Payload claims (`sub: "admin"`, `role: "ROLE_ADMIN"`), and the Signature.
5. In Angular, click **Tester un appel API avec ce jeton**:
   - Inspect the `GET /api/employees` request header in DevTools Network tab.
   - Highlight: `Authorization: Bearer <token>`.
6. Open [`auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts) in VS Code:
   - Show how 15 lines of code attach this header to all outgoing requests automatically!

### 3. Key Files to Inspect Together
- In **VS Code**:
  - [`auth.service.ts`](../frontend/src/app/services/auth.service.ts)
  - [`auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts)
  - [`login-demo.component.ts`](../frontend/src/app/components/login-demo/login-demo.component.ts)
- In **Eclipse**:
  - [`AuthDemoController.java`](../backend/src/main/java/com/finances/ems/controller/AuthDemoController.java)
  - [`CorsConfig.java`](../backend/src/main/java/com/finances/ems/config/CorsConfig.java)

### 4. Guided Discussion Questions for the 7 Engineers
- *"Why shouldn't you store employee passwords inside a JWT payload?"*  
  *(Answer: The payload is only Base64 encoded, not encrypted. Anyone who intercepts the token can read it).*
- *"What is CORS and why did your browser block port 8080 until we added `CorsConfig.java`?"*  
  *(Answer: Same-Origin Policy in browsers prevents malicious websites from reading data from another port).*
