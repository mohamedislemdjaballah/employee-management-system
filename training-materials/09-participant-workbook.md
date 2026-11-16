# Deliverable 09: Participant Practical Workbook
## Working with Modern Web Applications: Angular Frontend & Spring Boot Backend
**Name of Participant:** _______________________________________  
**Department / Direction:** ___________________________________  
**Date:** Day 3 Practical Workshop  

---

## 1. Welcome & Workshop Objectives

Welcome to Day 3 of the Web Modernization Training. Today's session is focused entirely on understanding how modern decoupled applications operate in production, how to navigate and test them, and how to use technical tools to diagnose and report problems.

### What You Will Learn Today:
1. **Decoupled Architecture:** Understand why the frontend (Angular) and backend (Spring Boot) run as separate programs.
2. **Business Operations:** Execute full CRUD workflows (Create, Read, Update, Delete) on an enterprise employee management system.
3. **The Network Wire:** Inspect how data travels in JSON format using HTTP verbs (`GET`, `POST`, `PUT`, `DELETE`).
4. **Developer Tools:** Use Google Chrome DevTools (`F12`) to inspect network traffic and spot console errors.
5. **Technical Communication:** Write professional, evidence-backed bug reports that developers can fix immediately.

---

## 2. Daily Timetable

| Time Slot | Session | Format |
| :--- | :--- | :--- |
| **08:00 – 08:20** | Welcome, Objectives & Orientation | Setup & Briefing |
| **08:20 – 08:45** | Understanding Decoupled Web Architecture | Concept Exploration |
| **08:45 – 09:15** | Angular Interface Tour: The Component Model | Live System Tour |
| **09:15 – 10:00** | **Mission 1: Exploring the Application** | **Hands-On Lab** |
| **10:00 – 10:15** | *Morning Break (15 min)* | Refreshment |
| **10:15 – 10:40** | Navigation, Client-Side Routing & Forms | Concept & Demo |
| **10:40 – 11:30** | **Mission 2: Performing Business Operations** | **Hands-On Lab** |
| **11:30 – 12:00** | Validation & Application Error Behaviors | Theory & Quiz 1 |
| **12:00 – 13:00** | *Lunch Break (60 min)* | Lunch |
| **13:00 – 13:25** | The Wire: REST API Communication & Spring Boot | Concept & Demo |
| **13:25 – 14:00** | Masterclass: Chrome Developer Tools (`F12`) | Guided Practice |
| **14:00 – 14:35** | **Missions 3 & 4: Wire Inspection & Troubleshooting** | **Hands-On Lab** |
| **14:35 – 15:00** | Bug Reporting, Final Assessment & Wrap-Up | Evaluation & Recap |

---

## 3. Architecture & Protocol Reference Sheets

### A. The 3 Tiers of Our System
```text
[ CLIENT TIER (Browser) ]       ---- REST API / HTTP (JSON) ---->       [ APPLICATION TIER (Server) ]
  Angular Application                                                     Spring Boot API Engine
  Runs in Chrome (Port 4200)                                              Runs on Server (Port 8080)
                                                                                     |
                                                                           SQL Queries (Hibernate)
                                                                                     v
                                                                        [ DATABASE TIER (Storage) ]
                                                                          PostgreSQL (Port 5432)
```

### B. Core HTTP Verbs
- **`GET`**: Retrieve or read records (Completely safe, never alters data).
- **`POST`**: Create a brand-new record in the database.
- **`PUT`**: Update or replace an existing record.
- **`DELETE`**: Permanently remove a record.

### C. Standard HTTP Status Codes
- **`200 OK`**: General success.
- **`201 Created`**: Resource successfully saved in PostgreSQL.
- **`204 No Content`**: Deletion successful (empty body returned).
- **`400 Bad Request`**: Data failed validation rules (e.g., negative salary, bad email).
- **`401 Unauthorized`**: Identity unknown (login required or session expired).
- **`403 Forbidden`**: Identity known, but permission denied for this action.
- **`404 Not Found`**: The requested record ID or URL does not exist.
- **`409 Conflict`**: Unique data collision (e.g. matricule or email already taken).
- **`500 Server Error`**: Program crash or unhandled bug on the server.

---

## 4. Practical Hands-On Mission Worksheets

---

### MISSION 1: Application Exploration & Audit Worksheet

**Instructions:** Open `http://localhost:4200` in Google Chrome and complete this audit sheet:

1. **Initial Dashboard Audit:**
   - What is the organization title in the top banner? ________________________
   - Initial **Effectif Affiché** count: __________
   - Initial **En Activité** count: __________
   - Initial **En Congé / Détachement** count: __________
   - Total **Masse Salariale Mensuelle**: ____________________ DA

2. **Testing Filter Controls:**
   - Filter by Direction `SIT — Système d'Information du Trésor`.
   - How many agents are displayed? __________
   - List their names:
     1. ________________________________________
     2. ________________________________________
     3. ________________________________________
   - What happened to the **Effectif Affiché** card at the top? ________________________

3. **Testing Search:**
   - In the search box, type `Karim`. Does the table isolate Karim BENALI? [ ] Yes  [ ] No
   - Click the white button **"Réinitialiser"**. Does the table return to 6 records? [ ] Yes  [ ] No

---

### MISSION 2: Business Operations Worksheet

**Instructions:** Create and update a real administrative record:

1. Click **"+ Ajouter un Agent"**. Enter the following test employee:
   - **Matricule:** `FIN-2026-008`
   - **Direction:** `DGT — Direction Générale du Trésor`
   - **Nom de Famille:** `CHIKHI`
   - **Prénom:** `Sofiane`
   - **Email:** `s.chikhi@finances.gov`
   - **Poste:** `Inspecteur des Finances`
   - **Salaire Brut:** `125000`
   - **Date:** Today's date
   - **Statut:** `ACTIVE`
2. Click **"Créer l'agent"**.
   - Was the record added to the table upon return? [ ] Yes  [ ] No
   - What is the new total employee count? __________
3. Click **"Modifier"** on Sofiane CHIKHI:
   - Update his salary to `140000`.
   - Update his status to `ON_LEAVE` (En Congé).
   - Click **"Enregistrer les modifications"**.
   - Did the status badge in the table turn Yellow? [ ] Yes  [ ] No

---

### MISSION 3: Chrome DevTools Wire Investigation Worksheet

**Instructions:** Press `F12`, click the **Network** tab, check **"Preserve log"**, and select filter **"Fetch/XHR"**. Create test employee Lynda MEZIANI (`FIN-2026-009`) and record the technical wire data:

1. Locate the request row named `employees` in the DevTools Network list.
2. Click on it and record the exact technical values:
   - **Full Request URL:** __________________________________________________
   - **HTTP Request Method:** [ ] GET   [ ] POST   [ ] PUT   [ ] DELETE
   - **HTTP Status Code Returned:** ____________________
   - Click the **Payload** tab. What is the matricule value sent? ____________________
   - Click the **Response** tab. What is the database `id` returned? ____________________

---

### MISSION 4: Troubleshooting Scenarios Worksheet

**Instructions:** Perform these 4 failure tests and record the diagnostic evidence:

| Scenario | What You Did | HTTP Status Code | Response Error Message Observed |
| :--- | :--- | :--- | :--- |
| **A. Duplicate Matricule** | Tried to create employee using matricule `FIN-2026-001` | | |
| **B. Invalid Email** | Entered email without `@` (e.g. `bademail`) | | |
| **C. Token Auth** | Logged into Day 5 Security tab (`admin` / `finances2026`) | | |
| **D. Record Deletion** | Clicked "Supprimer" on a test record | | |

---

## 5. Knowledge Check Quizzes (Without Answers)

### Quiz 1: Architecture & Roles
1. **Does the Angular frontend communicate directly with PostgreSQL?**
   - [ ] A) Yes, Angular sends SQL queries directly to port 5432.
   - [ ] B) No, Angular communicates only with Spring Boot using REST APIs (HTTP/JSON).
   - [ ] C) Only when modifying records.
2. **What is a Single Page Application (SPA)?**
   - [ ] A) An application that can only hold one employee.
   - [ ] B) An application where the web page loads once, and navigation updates dynamically in memory without full-page reloads.
   - [ ] C) A website that does not use a database.
3. **If the user's browser freezes, does the Spring Boot server crash?**
   - [ ] True
   - [ ] False

---

### Quiz 2: REST APIs & Status Codes
1. **Which HTTP method is used to read data without modifying the database?**
   - [ ] A) `POST`
   - [ ] B) `GET`
   - [ ] C) `DELETE`
2. **What HTTP status code indicates a brand-new record was successfully saved?**
   - [ ] A) `200 OK`
   - [ ] B) `201 Created`
   - [ ] C) `404 Not Found`
3. **What does an HTTP `409 Conflict` status code mean?**
   - [ ] A) The server lost power.
   - [ ] B) A unique constraint was violated (e.g. matricule or email already exists).
   - [ ] C) The user entered the wrong password.

---

### Quiz 3: DevTools & Troubleshooting
1. **If a button is clicked and nothing happens, which tab in DevTools shows red JavaScript crashes?**
   - [ ] A) Network
   - [ ] B) Console
   - [ ] C) Elements
2. **What is the difference between Authentication (AuthN) and Authorization (AuthZ)?**
   - [ ] A) AuthN = Identity ("Who are you?"); AuthZ = Permissions ("What can you do?").
   - [ ] B) They are identical terms.
   - [ ] C) AuthN is for databases; AuthZ is for web browsers.
3. **What information should NEVER be shared unredacted in a bug report?**
   - [ ] A) The HTTP status code.
   - [ ] B) The request URL.
   - [ ] C) Passwords, private access tokens, or sensitive citizen data.

---

## 6. Blank Bug Report Form (For Final Assessment)

```text
TITLE:
________________________________________________________________________________

METADATA:
- Application: Employee Management System (EMS)
- URL / Page: _________________________________________________________________
- User Role: __________________________   Date & Time: ________________________

STEPS TO REPRODUCE:
1. ____________________________________________________________________________
2. ____________________________________________________________________________
3. ____________________________________________________________________________

EXPECTED RESULT:
________________________________________________________________________________

ACTUAL RESULT:
________________________________________________________________________________

TECHNICAL EVIDENCE:
- HTTP Method: ____________________   HTTP Status Code: ______________________
- Endpoint URL: _______________________________________________________________
- Error Message / JSON Response:
  _____________________________________________________________________________
  _____________________________________________________________________________

SENSITIVE DATA SANITIZED? [ ] Yes (All passwords & tokens masked)
```
