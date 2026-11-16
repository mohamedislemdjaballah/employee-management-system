# Deliverable 04: Technical Vocabulary Reference & Glossary
## Operational Glossary for Decoupled Web Applications (Angular + Spring Boot)

This reference guide provides clear, non-academic definitions for common terminology used in enterprise web applications.

### Participant Expectation Levels:
- **UNDERSTAND:** Core concept. Participants should be able to explain the principle in plain language.
- **RECOGNIZE:** Operational awareness. Participants should spot this term in screens, logs, or discussions.
- **REPORT:** Diagnostic evidence. Participants simply copy, paste, or note this value when filing a bug ticket.

*Instructor Note: Where useful, comparisons to familiar Laravel concepts are included to assist your explanations. Remember that while conceptual parallels exist, Spring Boot and Laravel differ in execution models and type systems.*

---

## 1. System Architecture

| Term | Simple Definition | Practical Example in EMS | Laravel Equivalent | Participant Level |
| :--- | :--- | :--- | :--- | :--- |
| **Frontend** | The visual user interface that runs inside the user's web browser. | The buttons, tables, and forms rendered on `http://localhost:4200`. | Blade templates / Vue / Livewire UI | **UNDERSTAND** |
| **Backend** | The server program that enforces business rules, security, and talks to the database. | The Spring Boot service running on `http://localhost:8080`. | Laravel core (`app/Http`, Controllers, Models) | **UNDERSTAND** |
| **Client** | Any device or program (like Google Chrome) that requests information from a server. | The user's laptop browser. | Web browser / HTTP client | **UNDERSTAND** |
| **Server** | A computer or process that listens for requests and serves responses. | The Tomcat web server embedded inside Spring Boot. | Nginx + PHP-FPM / `php artisan serve` | **UNDERSTAND** |
| **Database** | The persistent storage engine where records are permanently saved on disk. | PostgreSQL running on port `5432` with tables `departments` and `employees`. | MySQL / PostgreSQL via Eloquent | **UNDERSTAND** |
| **Decoupled Architecture** | An architecture where the frontend (Angular) and backend (Spring Boot) are completely separate programs communicating only via network APIs. | Angular and Spring Boot running on different ports (`:4200` and `:8080`). | Headless Laravel API (`routes/api.php`) + standalone SPA | **UNDERSTAND** |
| **Angular** | A modern Google framework used to build fast, rich Single Page Applications in the browser. | The framework powering our Employee Management interface. | Frontend framework (similar role to Vue.js or React) | **RECOGNIZE** |
| **Spring Boot** | A popular enterprise Java framework used to build secure, robust REST APIs and microservices. | The backend service managing employees and departments. | Laravel Framework | **RECOGNIZE** |

---

## 2. API Communication & The Network Wire

| Term | Simple Definition | Practical Example in EMS | Laravel Equivalent | Participant Level |
| :--- | :--- | :--- | :--- | :--- |
| **REST API** | A standardized, language-neutral way for applications to exchange data over HTTP. | The communication link between Angular and Spring Boot. | RESTful API (`routes/api.php`) | **UNDERSTAND** |
| **Endpoint** | A specific URL address on the server representing a business resource. | `http://localhost:8080/api/employees` | API Route (e.g. `/api/employees`) | **REPORT** |
| **HTTP Request** | The message sent from the browser to the server asking for data or submitting an action. | Angular asking for the list of departments. | Incoming `$request` | **RECOGNIZE** |
| **HTTP Response** | The message returned by the server containing data or a status code. | Spring Boot returning the list of 6 employees. | Outgoing `response()->json(...)` | **RECOGNIZE** |
| **GET** | The HTTP method used to read or fetch data without changing anything. | `GET /api/employees` | `Route::get(...)` | **UNDERSTAND** |
| **POST** | The HTTP method used to submit new data to create a new record. | `POST /api/employees` with a new employee JSON. | `Route::post(...)` | **UNDERSTAND** |
| **PUT** | The HTTP method used to update or replace an existing record. | `PUT /api/employees/5` to update salary. | `Route::put(...)` | **UNDERSTAND** |
| **PATCH** | An HTTP method used to modify only specific fields of a record. | `PATCH /api/employees/5` (updating only phone). | `Route::patch(...)` | **RECOGNIZE** |
| **DELETE** | The HTTP method used to permanently remove a record. | `DELETE /api/employees/5` | `Route::delete(...)` | **UNDERSTAND** |
| **JSON** | JavaScript Object Notation: a plain-text, human-readable format for structured data. | `{"id": 1, "firstName": "Karim"}` | JSON output (`json_encode()`) | **UNDERSTAND** |
| **Payload** | The actual data body sent inside an HTTP request. | The employee form data submitted on creation. | Request body (`$request->all()`) | **REPORT** |
| **Response Body** | The data returned by the server inside an HTTP response. | The JSON array displayed in the Network tab. | Response payload | **REPORT** |
| **API Versioning** | A prefix in the URL used to maintain backward compatibility as systems evolve. | `/api/v1/employees` vs `/api/v2/employees`. | API version prefix (`Route::prefix('v1')`) | **RECOGNIZE** |

---

## 3. Authentication & Authorization

| Term | Simple Definition | Practical Example in EMS | Laravel Equivalent | Participant Level |
| :--- | :--- | :--- | :--- | :--- |
| **Authentication (AuthN)** | Verifying the identity of a user ("Who are you?"). | Entering username `admin` and password `finances2026`. | User login (`Auth::attempt()`) | **UNDERSTAND** |
| **Authorization (AuthZ)** | Verifying what an authenticated user is permitted to do ("What can you do?"). | Checking if an authenticated clerk is allowed to delete an employee record. | Gates & Policies (`Gate::allows('delete')`) | **UNDERSTAND** |
| **Credentials** | The secret proof used to authenticate (username and password). | `admin` / `finances2026`. | User credentials array | **UNDERSTAND** |
| **Access Token (JWT)** | A digitally signed string issued after login that proves identity on subsequent API calls. | `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...` | API Token (Sanctum / Passport) | **RECOGNIZE** |
| **Role** | A named group representing organizational responsibilities. | `ROLE_ADMIN` vs `ROLE_AGENT`. | User role column or Spatie roles | **RECOGNIZE** |
| **Permission** | A granular right to execute a specific action. | `EMPLOYEE_DELETE` or `SALARY_VIEW`. | Specific policy permission | **RECOGNIZE** |
| **Session** | A temporary conversational state maintained between a user and an application. | Client session stored in browser memory. | Session storage / Cookie | **UNDERSTAND** |

---

## 4. Angular Concepts (Frontend)

| Term | Simple Definition | Practical Example in EMS | Laravel Equivalent | Participant Level |
| :--- | :--- | :--- | :--- | :--- |
| **Component** | A visual building block comprising HTML template, CSS styling, and logic. | `EmployeeListComponent` rendering the table. | Blade Component (`<x-employee-list />`) | **UNDERSTAND** |
| **Service** | A singleton background helper in Angular responsible for making HTTP calls. | `EmployeeService` calling `http://localhost:8080/api/employees`. | Dedicated Service class in `app/Services` | **RECOGNIZE** |
| **Routing** | The mechanism that maps a URL in the browser address bar to a specific screen component. | Navigating from `/employees` to `/employees/new`. | `routes/web.php` (client-side equivalent) | **UNDERSTAND** |
| **Form** | An interactive input area where users enter and validate data before submission. | The Create/Edit Employee form. | HTML Form / Livewire Form object | **UNDERSTAND** |
| **Validation (Client)** | Immediate checks performed in browser memory to guide the user. | Turning an email input red if the `@` symbol is missing. | Browser HTML5 validation / Alpine validation | **UNDERSTAND** |
| **Data Binding** | The automated synchronization between a component's data and what appears on screen. | Typing in the search box immediately updating the table rows. | Livewire `wire:model` / Alpine `x-model` | **RECOGNIZE** |

---

## 5. Spring Boot Concepts (Backend)

| Term | Simple Definition | Practical Example in EMS | Laravel Equivalent | Participant Level |
| :--- | :--- | :--- | :--- | :--- |
| **Controller** | The Java class that receives HTTP requests and returns JSON responses. | `EmployeeController.java` (`@RestController`). | Laravel Controller (`EmployeeController.php`) | **RECOGNIZE** |
| **Service (Backend)** | The layer where core business logic and transaction rules are executed. | `EmployeeService.java` checking matricule uniqueness. | Laravel Service class / Business Action | **RECOGNIZE** |
| **Repository** | The interface responsible for reading and writing data in PostgreSQL. | `EmployeeRepository.java` (`JpaRepository`). | Eloquent Model query builder | **RECOGNIZE** |
| **Entity** | A Java class that directly represents a relational table in PostgreSQL. | `Employee.java` mapping to the `employees` table. | Eloquent Model (`class Employee extends Model`) | **RECOGNIZE** |
| **Exception Handling** | A centralized mechanism that catches crashes or errors and formats clean JSON error responses. | `GlobalExceptionHandler.java` converting crashes into HTTP 400 or 409. | `app/Exceptions/Handler.php` | **RECOGNIZE** |
| **Filter / Middleware** | A security gatekeeper that intercepts requests before they reach the controller. | Spring Security filter checking JWT tokens. | Laravel HTTP Middleware | **RECOGNIZE** |

---

## 6. Troubleshooting & Diagnostics

| Term | Simple Definition | Practical Example in EMS | Laravel Equivalent | Participant Level |
| :--- | :--- | :--- | :--- | :--- |
| **Bug** | A defect in software code that causes it to produce an incorrect, unexpected, or unintended result. | A salary update saving the wrong numeric amount. | Bug / Issue | **UNDERSTAND** |
| **Validation Error** | A rejection triggered because user input violated a specified format or business rule. | "Le format de l'adresse email est invalide". | Validation error message | **UNDERSTAND** |
| **Network Error** | A failure where the browser could not establish an HTTP connection with the server. | Status code `(failed)` or `0` when port 8080 is down. | Connection refused / cURL error | **UNDERSTAND** |
| **Frontend Error** | A crash occurring inside the browser's JavaScript engine (visible in Console). | A button click failing because of an uncaught exception. | JavaScript console error | **UNDERSTAND** |
| **Backend Error** | A failure occurring on the application server (HTTP 500). | Spring Boot throwing a database connection timeout. | PHP 500 error / Laravel log entry | **UNDERSTAND** |
| **Console** | The browser tool that displays logging output, warnings, and JavaScript errors. | The Console tab in Chrome DevTools (`F12`). | Browser developer console | **UNDERSTAND** |
| **Stack Trace** | A detailed list of program method calls leading up to an unhandled crash. | Java stack trace visible in Spring Boot terminal logs. | Laravel ignition / Whoops error trace | **REPORT** |
| **Reproduction Steps** | The exact, sequential click-by-click instructions required to make a bug happen again. | "1. Go to /new. 2. Leave email blank. 3. Click Save." | Steps to reproduce in Jira | **UNDERSTAND** |
| **Regression** | A bug that causes a previously working feature to stop functioning after a change. | Adding a new department causing the employee table to stop loading. | Regression defect | **RECOGNIZE** |
