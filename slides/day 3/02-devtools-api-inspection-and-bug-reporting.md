# Day 3: Wire Investigation, DevTools & Bug Reporting — Afternoon Session (13:00 – 15:00)

**Title:** *Under the Hood: Chrome DevTools, REST API Inspection & Professional Bug Reporting*

> **Instructor Note (Speaker Notes Only — Not Shown on Slides):**
> - **13:00 – 13:25 (25 mins):** The Wire — REST APIs, Verbs & Status Codes (Slides 1–4)
> - **13:25 – 14:00 (35 mins):** Chrome DevTools Masterclass: Console & Network Tabs (Slides 5–7)
> - **14:00 – 14:35 (35 mins):** **Practical Missions 3 & 4:** Wire Inspection & Troubleshooting (Slide 9)
> - **14:35 – 15:00 (25 mins):** Golden Bug Reporting, Final Assessment & Wrap-Up (Slides 8 & 10)
> - **Key Theme:** A status code is evidence, NOT a complete diagnosis. Always inspect the Response body and never leak sensitive tokens or passwords in screenshots!

---

### Slide 1: Afternoon Session — Looking Under the Hood
- **Title:** REST APIs, Chrome DevTools & Professional Bug Reporting
- **Session:** Part 2 — Afternoon Session (13:00 – 15:00)
- **What We Shift To Now:**
  - Morning: *How the user operates the Angular interface.*
  - Afternoon: *How to put on your detective hat, inspect the invisible network wire, and prove exactly what went wrong.*
- **Talking Points (Speaker Notes):**
  - Welcome back from lunch! This morning you learned how Angular works on the surface.
  - This afternoon, we open the hood. You are going to learn how to see the invisible envelopes flying between Chrome and Spring Boot using tools already built into your browser.

---

### Slide 2: The Wire — REST API Communication & JSON
- **How Angular Talks to Spring Boot:**
  ```text
  [ Angular (:4200) ]  ---- HTTP Request (Verb + URL + JSON) ---->  [ Spring Boot (:8080) ]
  [ Browser Memory  ]  <--- HTTP Response (Status + JSON Body) ---   [ Application Server  ]
  ```
- **What is JSON?**
  - **J**ava**S**cript **O**bject **N**otation: lightweight, human-readable text format.
  - Example employee object on the wire:
    ```json
    {
      "id": 1,
      "matricule": "FIN-2026-001",
      "firstName": "Karim",
      "lastName": "BENALI",
      "salary": 145000.00,
      "status": "ACTIVE"
    }
    ```
- **Swagger UI (`http://localhost:8080/swagger-ui.html`):** The living contract of all backend endpoints.
- **Talking Points (Speaker Notes):**
  - Show Swagger UI briefly on screen and execute `GET /api/employees`.
  - Emphasize: "Notice that JSON is plain text. Even if you aren't a programmer, you can immediately read `"firstName": "Karim"` and `"salary": 145000`."

---

### Slide 3: The 4 Core HTTP Methods (Action Verbs)

| HTTP Method | Business Action | EMS Endpoint Example | Data Change? |
| :--- | :--- | :--- | :--- |
| **`GET`** | **Read / Search** records | `GET /api/employees?departmentId=1` | No (Read-only) |
| **`POST`** | **Create** a new record | `POST /api/employees` | Yes (Inserts row) |
| **`PUT`** | **Update / Replace** a record | `PUT /api/employees/8` | Yes (Updates row) |
| **`DELETE`** | **Remove** a record | `DELETE /api/employees/8` | Yes (Deletes row) |

- **Golden Rule:** In REST, the **URL** names the resource (`/api/employees`), and the **HTTP Verb** declares the action.
- **Talking Points (Speaker Notes):**
  - *[Laravel Context: Identical to `Route::apiResource('employees', ...)` mapping GET, POST, PUT, DELETE.]*
  - Point out that `GET` requests carry parameters in the URL (`?departmentId=1`), while `POST` and `PUT` carry a JSON **Payload** inside the request body.

---

### Slide 4: HTTP Status Codes — Your 3-Digit Compass

| Category | Code | Meaning | What Happened? |
| :--- | :--- | :--- | :--- |
| **2xx Success** | **`200 OK`** | Standard Success | Data retrieved or updated cleanly. |
| | **`201 Created`** | Resource Created | New employee saved to PostgreSQL (`POST`). |
| | **`204 No Content`** | Deleted Cleanly | Record deleted; empty body returned (`DELETE`). |
| **4xx Client** | **`400 Bad Request`** | Validation Error | Missing field, bad email, or negative salary. |
| | **`401 Unauthorized`** | Not Logged In | Missing or expired authentication token. |
| | **`403 Forbidden`** | Access Denied | Logged in, but user role lacks permission. |
| | **`404 Not Found`** | Missing Resource | Wrong URL or employee ID does not exist. |
| | **`409 Conflict`** | Duplicate Rule | Matricule or email already exists in database. |
| **5xx Server** | **`500 Server Error`** | Server Crash | Unhandled Java bug or database outage. |

- **Important Principle:** *A status code is evidence, NOT a complete diagnosis.* Always check the Response body for the exact error message!
- **Talking Points (Speaker Notes):**
  - Emphasize the difference between `4xx` and `5xx`: `4xx` means the backend caught an invalid request or permission rule (the server is healthy!). `5xx` means the backend crashed.

---

### Slide 5: Chrome Developer Tools (`F12`) — Your Technical Microscope
- **How to Open:** Press **`F12`** (or Right-Click $\rightarrow$ **Inspect**) in Google Chrome.
- **The Two Essential Tabs for Investigators:**
  1. **Console Tab (The Flight Recorder):**
     - Shows bright **red JavaScript errors** occurring inside the browser.
     - *Check here first if a button clicks and "nothing happens" or the screen goes white!*
  2. **Network Tab (The Wire Surveillance Camera):**
     - Records every HTTP envelope exchanged between Angular and Spring Boot.
     - **Two Mandatory Setup Steps:**
       - ✅ Check **"Preserve log"** (prevents losing evidence during navigation).
       - ✅ Click **"Fetch/XHR"** filter (hides images/CSS so you only see API calls).
- **Talking Points (Speaker Notes):**
  - Have everyone press `F12` right now and follow along on their screens.
  - Wait until every participant checks "Preserve log" and clicks "Fetch/XHR" before moving to Slide 6.

---

### Slide 6: Anatomy of a Network Request in DevTools
- **When You Click a Request Row (e.g., `employees`):**
  1. **Headers Tab:**
     - Exact **Request URL** (`http://localhost:8080/api/employees`)
     - **Request Method** (`POST`)
     - **Status Code** (`201 Created` or red `409 Conflict`)
     - **Request Headers** (e.g., `Authorization: Bearer ...`)
  2. **Payload Tab:** What Angular sent to the server (the JSON form data).
  3. **Response Tab:** What Spring Boot sent back (saved employee OR detailed JSON error).
  4. **Timing Tab:** How many milliseconds the backend took to respond.
- **Talking Points (Speaker Notes):**
  - Demonstrate clicking the "↻ Actualiser" button and opening the `employees` row in DevTools.
  - Show all 4 sub-tabs on the projector so participants know where to look during Mission 3.

---

### Slide 7: Authentication (`401`) vs. Authorization (`403`)
- **Authentication (AuthN) — *"Who are you?"***
  - Verifies identity using credentials (`admin` / `finances2026`).
  - Server issues a digitally signed **JWT Access Token** (`eyJhbGci...`).
  - Angular stores the token and an **HTTP Interceptor** attaches `Authorization: Bearer <token>` automatically to every API call.
  - *Failure:* **`401 Unauthorized`** (missing or expired token).
- **Authorization (AuthZ) — *"What are you allowed to do?"***
  - Checks user roles (`ROLE_ADMIN` vs `ROLE_AGENT`).
  - *Failure:* **`403 Forbidden`** (identity is known, but permission is denied).
- **Security Warning:** **NEVER** share raw passwords or unredacted Bearer tokens in screenshots or tickets! Mask them as `[REDACTED]`.
- **Talking Points (Speaker Notes):**
  - *[Laravel Context: Just like Laravel Sanctum issuing Bearer tokens, and Laravel Policies returning 403 Forbidden.]*
  - Use the Passport vs. Visa analogy: Authentication is your passport at the airport; Authorization is your visa allowing entry into restricted rooms.

---

### Slide 8: The Art of Professional Bug Reporting
- **Useless Report:** *"The employee screen is broken. Fix it ASAP."*
- **The Golden Bug Report (5 Essential Sections):**
  1. **Clear Title & Context:** Page URL, browser, user role, date/time.
  2. **Steps to Reproduce:** Numbered 1, 2, 3 click sequence.
  3. **Expected vs. Actual Behavior:** What should happen vs. what happened.
  4. **Technical Evidence (from DevTools):**
     - Console error (if any)
     - HTTP Method + Endpoint URL
     - HTTP Status Code
     - Sanitized Request Payload & Server Response JSON
  5. **Cropped, Redacted Screenshot.**
- **Talking Points (Speaker Notes):**
  - Tell the room: "When you send a report like this, developers don't ask follow-up questions—they locate and fix the bug in 15 minutes."

---

### Slide 9: Practical Missions 3 & 4 — Wire Inspection & Troubleshooting (14:00 – 14:35)
- **Time Allocation:** 35 Minutes
- **Mission 3 (Wire Inspection):**
  - With DevTools open (`Preserve log` + `Fetch/XHR`), create test employee `FIN-2026-009` (Lynda MEZIANI).
  - Record the URL, Method (`POST`), Status (`201`), Payload, and returned ID.
- **Mission 4 (4 Diagnostic Scenarios):**
  - **Scenario A:** Submit existing matricule `FIN-2026-001` $\rightarrow$ Capture `409 Conflict` JSON.
  - **Scenario B:** Submit invalid email (`bademail`) $\rightarrow$ Observe client-side block (0 network calls).
  - **Scenario C:** Log into **Atelier Sécurité (Jour 5)** (`admin` / `finances2026`) $\rightarrow$ Capture `Authorization: Bearer` header.
  - **Scenario D:** Delete a test record $\rightarrow$ Capture `DELETE` and `204 No Content`.
- **Talking Points (Speaker Notes):**
  - Guide participants through Missions 3 & 4 in `06-practical-missions.md` and their Workbook.
  - Walk the room to verify everyone sees the `409 Conflict` red row and the `Authorization: Bearer` header.

---

### Slide 10: Final Practical Assessment & Workshop Wrap-Up (14:35 – 15:00)
- **Final Assessment (15 Minutes):**
  - Review the 3 real-world diagnostic scenarios in your workbook:
    - **Scenario A:** Blocked Form (Validation)
    - **Scenario B:** Denied Deletion (`403 Forbidden`)
    - **Scenario C:** Server Crash (`500 Internal Server Error`)
  - Write **one complete, reproducible Golden Bug Report**.
- **Day 3 Core Takeaways:**
  1. Decoupled architecture separates UI (`:4200`), Logic (`:8080`), and DB (`:5432`).
  2. REST speaks `GET`, `POST`, `PUT`, `DELETE` using JSON and Status Codes.
  3. Chrome DevTools (`F12`) is your microscope for Console crashes and Network traffic.
  4. Evidence-backed bug reports turn frustration into fast engineering fixes!
- **Talking Points (Speaker Notes):**
  - Hand out or direct students to Section 6 of their Participant Workbook for the Final Assessment.
  - After 15 minutes, review one model bug report together, thank the 7 engineers for their great engagement, and wrap up Day 3!
