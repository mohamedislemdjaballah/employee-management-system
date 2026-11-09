# Live Demo & System Inspection Guide
## How to Run, Trace & Inspect the Application Live in Front of the Students

This guide gives you the exact mouse clicks, keyboard shortcuts, and inspection procedures to prove each architectural concept live on the projector.

---

## 1. Golden Setup: The 4-Window Screen Layout

Before the students arrive each morning, arrange your projector screen with these 4 windows:

```text
+-----------------------------------+-----------------------------------+
|  WINDOW 1: Visual Studio Code     |  WINDOW 2: Eclipse IDE (STS)      |
|  (Frontend Angular 18 Code)       |  (Backend Spring Boot 3 & Console)|
+-----------------------------------+-----------------------------------+
|  WINDOW 3: Google Chrome          |  WINDOW 4: TablePlus              |
|  - Tab A: http://localhost:4200   |  (PostgreSQL tables, columns, rows|
|  - Tab B: Swagger UI (:8080)      |   live updates & FK relationships)|
|  - F12 Network Tab open!          |                                   |
+-----------------------------------+-----------------------------------+
```

---

## 2. Live Demo Script 1: Proving the REST Contract (Day 1)

### Objective:
Prove to students that Spring Boot is an independent data engine that does not know or care about Angular or HTML.

### Step-by-Step Actions:
1. Open Chrome to **`http://localhost:8080/swagger-ui.html`**.
2. Click on the **`GET /api/employees`** accordion $\rightarrow$ Click **Try it out** $\rightarrow$ Click **Execute**.
3. Point out:
   - **Request URL:** `http://localhost:8080/api/employees`
   - **Response Code:** `200 OK`
   - **Response Body:** Pure JSON array of 6 objects.
4. Show students that you can test search filters directly in Swagger:
   - In the `search` parameter, type `Karim` $\rightarrow$ Click **Execute**.
   - Show that only Karim Benali is returned.
5. In the `departmentId` parameter, type `1` (SIT) $\rightarrow$ Click **Execute**.
   - Show how query parameters filter the JSON payload.
6. Now switch to Tab A: **`http://localhost:4200`** (Angular):
   - Show the same 6 employees displayed in a responsive table with status badges.
   - Conclude: *"Swagger and Angular are simply two different consumers of the exact same REST API."*

---

## 3. Live Demo Script 2: Proving What an ORM Does (Day 2)

### Objective:
Prove that `employeeRepository.save(employee)` in Java generates a real SQL `INSERT` statement in PostgreSQL.

### Step-by-Step Actions:
1. Open **TablePlus** connected to `localhost:5432` $\rightarrow$ Open table `employees`.
   - Count the rows: exactly 6 rows.
2. In **Eclipse**, open the Console tab. Ensure you can see the latest log lines.
3. In **Swagger UI** (or Angular), scroll to `POST /api/employees` $\rightarrow$ Click **Try it out**.
4. Paste this test JSON:
   ```json
   {
     "matricule": "FIN-2026-999",
     "firstName": "Tahar",
     "lastName": "ZEROUAL",
     "email": "t.zeroual@finances.gov",
     "phone": "0559 99 99 99",
     "jobTitle": "Chef de Service Sécurité",
     "salary": 160000,
     "hireDate": "2026-11-01",
     "status": "ACTIVE",
     "departmentId": 1
   }
   ```
5. Click **Execute** $\rightarrow$ Observe `201 Created` with generated `id: 7`.
6. Switch immediately to **Eclipse Console**:
   - Scroll down to the Hibernate logs:
     ```sql
     Hibernate: 
         insert 
         into
             employees
             (department_id, email, first_name, hire_date, job_title, last_name, matricule, phone, salary, status) 
         values
             (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
     ```
   - Highlight the `insert into employees ...` and explain:
     > *"We did not write any SQL query. Spring Data JPA generated this exact SQL dialect for PostgreSQL."*
7. Switch to **TablePlus** $\rightarrow$ Press `Ctrl + R` (Refresh):
   - Point to row 7: `FIN-2026-999 | Tahar ZEROUAL`!

---

## 4. Live Demo Script 3: Proving Non-Blocking JavaScript (Day 3)

### Objective:
Prove to legacy engineers why JavaScript must be asynchronous while Java is synchronous.

### Step-by-Step Actions:
1. In **VS Code**, open `frontend/src/app/components/employee-list/employee-list.component.ts`.
2. Locate `loadEmployees()`:
   ```typescript
   console.log("==> 1. Calling employeeService.getEmployees()");
   this.employeeService.getEmployees(...).subscribe({
     next: (data) => {
       console.log("==> 3. Observable emitted! JSON received with", data.length, "employees");
       this.employees = data;
     }
   });
   console.log("==> 2. loadEmployees() function finished executing!");
   ```
3. Save the file (Angular hot-reloads in 1 second).
4. In Chrome (`http://localhost:4200`), open DevTools (`F12`) $\rightarrow$ Click the **Console** tab.
5. Click the **↻ Actualiser** button in the app.
6. Show the console output to the class:
   ```text
   ==> 1. Calling employeeService.getEmployees()
   ==> 2. loadEmployees() function finished executing!
   ==> 3. Observable emitted! JSON received with 6 employees
   ```
7. Ask the class: *"Why did line 2 print BEFORE line 3?"*
8. Explain the **Event Loop**:
   - Line 1 dispatches the background HTTP call.
   - Line 2 finishes synchronously to keep the browser UI running at 60 frames/second.
   - Line 3 is executed asynchronously only when PostgreSQL and Spring Boot have sent the bytes back over the network!

---

## 5. Live Demo Script 4: Tracing the Full Request Wire (Day 4)

### Objective:
Trace a live user submission through the Chrome DevTools Network tab.

### Step-by-Step Actions:
1. In Chrome, open `http://localhost:4200/employees/new`.
2. Press `F12` $\rightarrow$ Open the **Network** tab $\rightarrow$ Click **Fetch/XHR** filter $\rightarrow$ Check **Preserve log**.
3. Fill in the form and click **Créer l'agent**.
4. In the Network tab list, look at the last item: `employees` with Method `POST` and Status `201`.
5. Click on that request and inspect the 4 key tabs:
   - **Headers:** Show `Request URL: http://localhost:8080/api/employees`, `Request Method: POST`, `Status Code: 201 Created`.
   - **Payload:** Show the JSON sent from Angular.
   - **Response:** Show the JSON returned from Spring Boot with the new database ID.
   - **Timing:** Show the breakdown: DNS lookup, Initial connection, Waiting for server response (TTFB), and Content download.

---

## 6. Live Demo Script 5: Proving JWT & HTTP Interceptors (Day 5)

### Objective:
Demonstrate how an Angular HTTP Interceptor automatically attaches the JWT Bearer token to requests.

### Step-by-Step Actions:
1. In Chrome (`http://localhost:4200`), navigate to **Annuaire des Agents**.
   - In DevTools Network tab, inspect the `GET /api/employees` request.
   - Point out that there is **no Authorization header** yet.
2. In the navbar, click **Atelier Sécurité (Jour 5)** (`http://localhost:4200/login-demo`).
3. Click **Obtenir un Jeton JWT** (using `admin` / `finances2026`).
   - Show the token string displayed in the cyan code block.
4. Copy the token string. Open **`https://jwt.io`** in a new tab:
   - Paste the token into the Encoded box.
   - Show the decoded **Payload** on the right:
     ```json
     {
       "sub": "admin",
       "role": "ADMIN_DRH",
       "iat": 1731178800
     }
     ```
   - Explain that anyone can read the claims, but only the server with the secret key can forge the signature!
5. Now click **Tester un appel API avec ce jeton** (which returns to the employee list).
6. In DevTools Network tab, click the new `GET /api/employees` request:
   - Scroll down to **Request Headers**:
   - Highlight:
     `Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`
7. In VS Code, open [`auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts) and show lines 13–19:
   > *"Notice how the component did not add this header. The Interceptor intercepted the request in flight and added the token automatically!"*
