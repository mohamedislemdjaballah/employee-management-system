# Deliverable 05: Chrome Developer Tools Investigation Guide
## The Non-Developer's Field Guide to Inspecting Web Applications

Google Chrome Developer Tools (DevTools) is an advanced technical diagnostic instrument built into every modern computer. As an auditor, project manager, or technical lead, DevTools allows you to observe what is happening "under the hood" of any web application.

---

## 1. Opening & Configuring Developer Tools

### How to Open DevTools:
- **Keyboard Shortcut:** Press **`F12`** on Windows (or **`Ctrl + Shift + I`**).
- **Mouse Method:** Right-click anywhere on the web page and select **Inspect**.

### How to Dock DevTools:
1. In the top-right corner of the DevTools panel, locate the **Three Vertical Dots (⋮)**.
2. Under **Dock side**, select:
   - **Dock to bottom** (Recommended for wide screens) OR
   - **Dock to right** (Recommended for tall monitors).

---

## 2. Tool 1: The Console Tab (The Flight Recorder)

### What is the Console?
The Console is the browser's black-box recorder. It logs everything that happens inside the browser's JavaScript engine.

```text
[Console View]
> Navigated to http://localhost:4200/employees
⚠️ [Warning] Performance notice: font loading slow
❌ [Error] TypeError: Cannot read properties of undefined (reading 'salary')
    at EmployeeListComponent.loadEmployees (employee-list.component.ts:48)
```

### What Do the Colors Mean?
- **White / Grey text:** Standard informational logs emitted by the application.
- **Yellow text:** Warnings. The application is still running, but a sub-optimal condition was detected.
- **Bright Red text:** **CRITICAL JAVASCRIPT ERROR.** A script crashed inside the user's browser.

### When Should You Look at the Console?
Whenever an end-user complains:
- *"I clicked the button and absolutely nothing happened."*
- *"The screen went completely white."*
- *"The loading spinner has been spinning for 20 minutes."*

### What Technical Evidence Should You Capture?
1. The **exact red error message** (e.g., `TypeError: Cannot read properties of undefined`).
2. The **file name and line number** on the right side (e.g., `employee-list.component.ts:48`).
3. Take a cropped screenshot of the red text.

---

## 3. Tool 2: The Network Tab (The Wire Surveillance Camera)

### What is the Network Tab?
The Network tab records every single message (HTTP request and response) exchanged between the Angular frontend in your browser and the Spring Boot backend on the server.

### Essential Pre-Flight Setup (Do This Every Time!):
1. Click the **Network** tab at the top of DevTools.
2. Check the box labeled **"Preserve log"** (top toolbar).  
   *Why? By default, Chrome clears the network history if a page changes. Checking "Preserve log" ensures your evidence is not lost.*
3. Click the filter button labeled **"Fetch/XHR"**.  
   *Why? Websites download hundreds of images, icons, and styling sheets. "Fetch/XHR" filters out all images so you only see actual API data calls.*

```text
[Top Toolbar Options]
[x] Preserve log    [ ] Disable cache    [ Filter: Fetch/XHR ]    [ Clear 🚫 ]
```

---

## 4. Inspecting an API Call Step-by-Step

When an action occurs (e.g., clicking "+ Ajouter un Agent" and saving), a new row appears in the Network list.

```text
Name         Status    Type         Initiator                  Size      Time
employees    201       fetch        employee.service.ts:145    1.2 kB    24 ms
```

Click directly on the name of the request (**`employees`**). A detailed inspection panel opens on the right side with 4 critical tabs:

### Tab 1: Headers (The Envelope)
Shows basic routing and communication details:
- **Request URL:** `http://localhost:8080/api/employees` (The exact backend address).
- **Request Method:** `POST` (The action performed: create record).
- **Status Code:** `201 Created` (The three-digit result code).
- **Request Headers:** Shows metadata, including `Authorization: Bearer <token>` when security is enabled.

### Tab 2: Payload (What the Browser Sent)
Shows the exact data the user submitted before it left the browser:
```json
{
  "matricule": "FIN-2026-008",
  "firstName": "Sofiane",
  "lastName": "CHIKHI",
  "email": "s.chikhi@finances.gov",
  "salary": 120000,
  "status": "ACTIVE",
  "departmentId": 4
}
```

### Tab 3: Response (What the Server Returned)
Shows the raw answer sent back by Spring Boot:
```json
{
  "id": 7,
  "matricule": "FIN-2026-008",
  "firstName": "Sofiane",
  "lastName": "CHIKHI",
  "departmentName": "Direction des Ressources Humaines"
}
```

### Tab 4: Timing (How Fast the System Responded)
Shows how many milliseconds the network request took:
- **Waiting for server response (TTFB):** If this value is 5,000 ms (5 seconds), the server or database is slow!
- **Content Download:** If this is large, the server sent too much data.

---

## 5. Identifying and Diagnosing Failed Requests

When an HTTP request fails, Chrome highlights the row in **Bright Red**:

```text
Name         Status    Type         Initiator                  Size      Time
employees    409       fetch        employee.service.ts:145    842 B     18 ms
```

### How to Investigate a Red Request:
1. Click the red request row.
2. Check the **Status Code**:
   - **`400 Bad Request`:** The user entered data that violated a validation rule (e.g., negative salary, malformed email). Click the **Response** tab to see the field-level error messages!
   - **`401 Unauthorized`:** The user is not logged in or their session token expired.
   - **`403 Forbidden`:** The user is logged in, but their role (e.g., `ROLE_AGENT`) does not have permission to execute this operation (e.g., delete a director).
   - **`404 Not Found`:** The requested ID does not exist in the database, or the URL has a typo.
   - **`409 Conflict`:** Business rule collision. For example, trying to register an employee whose matricule or email is already registered in PostgreSQL.
   - **`500 Internal Server Error`:** The server crashed! Spring Boot encountered an unhandled Java exception.
   - **`(failed)` or Status `0`:** The server is completely offline, unreachable, or port 8080 was blocked by a firewall or CORS policy.

---

## 6. A Status Code is Evidence, NOT a Complete Diagnosis

> [!IMPORTANT]
> **The Golden Diagnostic Principle:**  
> A status code like `500` tells you *where* the problem manifested (on the server), but it does not tell you *why* it crashed.  
> Always click the **Response** tab to see if Spring Boot sent a descriptive JSON error object:
> ```json
> {
>   "timestamp": "2026-11-16T14:22:10",
>   "status": 409,
>   "error": "Conflict",
>   "message": "Un agent avec le matricule 'FIN-2026-001' existe déjà."
> }
> ```
> Providing this JSON message in your bug report allows a developer to identify the exact line of code in 30 seconds.

---

## 7. Critical Security Warning: Handling Sensitive Data

> [!CAUTION]
> **Data Privacy and Redaction Mandate:**  
> When capturing screenshots or copying JSON payloads for bug reports or tickets:
> 1. **NEVER** share employee passwords or raw login credentials.
> 2. **NEVER** paste unredacted JWT Access Tokens (`Bearer eyJhbGciOi...`) in public chat channels or tickets.
> 3. **MASK** sensitive citizen information (national ID numbers, private bank account numbers, medical notes).
> 
> *Best Practice:* In Chrome DevTools, double-click a sensitive value to replace it with `[REDACTED]` before taking a screenshot!
