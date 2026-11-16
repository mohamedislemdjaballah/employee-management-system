# Deliverable 07: Quizzes & Knowledge Checks (With Instructor Answer Key)
## Sectional Knowledge Checks for the 6-Hour Workshop

These short quizzes are designed to reinforce concepts without intimidating participants. Each quiz takes 3–5 minutes and can be conducted as an interactive group discussion or individual check.

---

## Quiz 1: Web Architecture & Application Roles (Morning Checkpoint)

### Questions

#### 1. In our decoupled architecture, does the Angular frontend communicate directly with the PostgreSQL database?
- [ ] A) Yes, Angular sends SQL queries directly to port 5432.
- [ ] B) No, Angular communicates only with Spring Boot using REST APIs (HTTP/JSON).
- [ ] C) Yes, but only when deleting records.
- [ ] D) Only if pgAdmin is installed on the user's computer.

#### 2. What is the main difference between a traditional website (like old JSP/PHP portals) and a modern Single Page Application (Angular)?
- [ ] A) Single Page Applications cannot connect to databases.
- [ ] B) Traditional websites are always faster.
- [ ] C) In a Single Page Application, the page loads once and updates dynamically in browser memory without full-page reloads.
- [ ] D) Single Page Applications do not use HTML or CSS.

#### 3. Why are Angular (port 4200) and Spring Boot (port 8080) separated into two different programs?
*(True / False)*
- **3.1 True or False:** If the Angular frontend crashes in the user's browser, the Spring Boot server automatically crashes as well.
- **3.2 True or False:** Separating the frontend and backend allows the same Spring Boot API to serve web users, mobile apps, and other Ministry portals simultaneously.

---

### Instructor Answer Key & Explanations (Quiz 1)

1. **Correct Answer: B**
   - *Explanation:* The browser NEVER talks directly to PostgreSQL. Doing so would expose database credentials to anyone pressing F12. All database interactions are securely handled by Spring Boot.
2. **Correct Answer: C**
   - *Explanation:* SPAs load `index.html` once and swap visual components in browser RAM, eliminating the blank white screen and page reloading of traditional websites.
3. **True / False Answers:**
   - **3.1 FALSE:** The frontend and backend are completely decoupled programs. A browser crash has zero impact on the server.
   - **3.2 TRUE:** Because Spring Boot exposes standardized JSON over HTTP, any client (Angular, Android app, Treasury reporting service) can consume it.

---

## Quiz 2: REST APIs & HTTP Protocol (Early Afternoon Checkpoint)

### Questions

#### 1. Which HTTP method is used when you want to retrieve a list of employees without modifying anything?
- [ ] A) `POST`
- [ ] B) `DELETE`
- [ ] C) `GET`
- [ ] D) `PUT`

#### 2. When creating a new employee in the database, which HTTP status code indicates that the record was successfully created?
- [ ] A) `200 OK`
- [ ] B) `201 Created`
- [ ] C) `404 Not Found`
- [ ] D) `500 Server Error`

#### 3. What does an HTTP `409 Conflict` status code typically indicate in our Employee Management System?
- [ ] A) The PostgreSQL database was deleted.
- [ ] B) The user submitted a matricule or email that already exists in the database.
- [ ] C) The user forgot their password.
- [ ] D) The computer has no internet connection.

#### 4. Which of the following is written in valid JSON format?
```text
Option A:  <employee><id>1</id><name>Karim</name></employee>
Option B:  SELECT * FROM employees WHERE id = 1;
Option C:  {"id": 1, "firstName": "Karim", "salary": 145000}
Option D:  employee(id=1, name=Karim)
```

---

### Instructor Answer Key & Explanations (Quiz 2)

1. **Correct Answer: C (`GET`)**
   - *Explanation:* `GET` is safe and idempotent. It only reads data.
2. **Correct Answer: B (`201 Created`)**
   - *Explanation:* While `200 OK` means success generally, standard REST semantics dictate that creating a new resource returns `201 Created`.
3. **Correct Answer: B**
   - *Explanation:* `409 Conflict` indicates a business constraint collision, such as trying to insert a duplicate unique matricule.
4. **Correct Answer: Option C**
   - *Explanation:* Option A is XML, Option B is SQL, Option D is pseudocode. Option C is valid JSON key-value pairs.

---

## Quiz 3: DevTools, Validation & Security (Late Afternoon Checkpoint)

### Questions

#### 1. An end-user clicks the "Save" button and complains that the screen does nothing. Which Chrome DevTools tab should you check FIRST for red error messages?
- [ ] A) The Network tab
- [ ] B) The Console tab
- [ ] C) The Elements tab
- [ ] D) The Memory tab

#### 2. Why can't software developers rely ONLY on frontend validation (Angular) to protect the application?
- [ ] A) Angular cannot validate email addresses.
- [ ] B) Frontend validation runs inside the user's browser, which can be bypassed or tampered with by any technical user or external script.
- [ ] C) Frontend validation makes the computer run out of memory.
- [ ] D) PostgreSQL does not allow frontend validation.

#### 3. What is the fundamental difference between Authentication and Authorization?
- [ ] A) Authentication verifies *who you are*; Authorization verifies *what you are allowed to do*.
- [ ] B) Authentication is for databases; Authorization is for web pages.
- [ ] C) Authentication returns HTTP 403; Authorization returns HTTP 401.
- [ ] D) They are identical terms with no difference.

#### 4. If an authenticated user attempts to delete an employee record and receives an `HTTP 403 Forbidden` status code, what does this indicate?
- [ ] A) The server is offline.
- [ ] B) The user's password was wrong.
- [ ] C) The user is logged in, but their user role does not have permission to delete records.
- [ ] D) The employee record was already deleted.

#### 5. Which of the following should NEVER be included unredacted in a public bug report or chat channel?
- [ ] A) The HTTP status code (e.g. `400 Bad Request`).
- [ ] B) The endpoint URL (e.g. `http://localhost:8080/api/employees`).
- [ ] C) The user's raw password or unredacted secret access token.
- [ ] D) The step-by-step reproduction instructions.

---

### Instructor Answer Key & Explanations (Quiz 3)

1. **Correct Answer: B (Console tab)**
   - *Explanation:* If a button click triggers no network activity at all, a client-side JavaScript error likely halted execution. The Console tab shows this in bright red.
2. **Correct Answer: B**
   - *Explanation:* Client-side checks are for user guidance (UX). The backend (Spring Boot `@Valid`) must always act as the authoritative security gatekeeper.
3. **Correct Answer: A**
   - *Explanation:* Authentication = Identity verification (Passport). Authorization = Permission check (Visa).
4. **Correct Answer: C**
   - *Explanation:* `401` = Unauthenticated (no valid identity). `403` = Forbidden (identity is known, but permission is denied).
5. **Correct Answer: C**
   - *Explanation:* Never share passwords, secret tokens, or private citizen data. Always redact or replace with `[REDACTED]`.
