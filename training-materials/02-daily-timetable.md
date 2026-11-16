# Deliverable 02: Detailed Daily Timetable
## Workshop: Working with Modern Web Applications — Angular Frontend & Spring Boot Backend
**Day:** Day 3 (Hands-on Exploration, DevTools Investigation & Bug Reporting)  
**Total Training Time:** 6 Hours (08:00–12:00 & 13:00–15:00)  
**Target Audience:** Senior Enterprise Engineers (Non-Programmers)  
**Teaching Method:** 20% Theory, 20% Instructor Demonstration, 60% Participant Hands-on  

---

## Master Schedule Overview

| Time Slot | Session Title | Session Type | Key Objective | Deliverables / Output |
| :--- | :--- | :--- | :--- | :--- |
| **08:00 – 08:20** | Welcome, Objectives & Orientation | Theory & Setup | Understand the role of today's workshop and set classroom ground rules. | Student environments verified; expectations aligned. |
| **08:20 – 08:45** | Understanding Decoupled Web Architecture | Theory (Analogy-Driven) | Demystify how the Angular SPA and Spring Boot backend interact over HTTP. | Clear mental model of the 3 tiers (Browser $\leftrightarrow$ REST $\leftrightarrow$ Database). |
| **08:45 – 09:15** | Angular Interface Tour: The Component Model | Demo (Instructor-Led) | Show how the screen is assembled from Lego-like components without page reloads. | Visual recognition of Navbar, Table, Form, and Status Badges. |
| **09:15 – 10:00** | **Practical Mission 1:** Exploring the Application | **Hands-On (45 min)** | Navigate the live Employee Management System (EMS), audit fields, test filters. | Completed Mission 1 Audit Worksheet in Workbook. |
| **10:00 – 10:15** | *Morning Refreshment Break* | *Break (15 min)* | Rest and informal questions. | |
| **10:15 – 10:40** | Navigation, Client-Side Routing & Forms | Theory & Demo | Understand client-side routing (`/employees` vs `/employees/new`) vs server page loads. | Understanding of SPA memory state and URL synchronization. |
| **10:40 – 11:30** | **Practical Mission 2:** Performing Business Operations | **Hands-On (50 min)** | Execute full CRUD operations: add employees, update departments, observe lists. | Successful creation, modification, and deletion of test records. |
| **11:30 – 12:00** | Validation & Application Error Behavior | Theory & Demo | Differentiate between instant frontend validation and authoritative backend rules. | Knowledge Check Quiz 1; recognition of client vs server errors. |
| **12:00 – 13:00** | *Lunch Break* | *Break (60 min)* | Lunch & rest. | |
| **13:00 – 13:25** | The Wire: REST API Communication & Spring Boot | Theory & Demo | Inspect what travels across the network wire (HTTP Verbs, Headers, JSON payloads). | Understanding JSON as the language-agnostic contract. |
| **13:25 – 14:00** | Masterclass: Chrome Developer Tools | Demo & Guided Practice | Master the Network tab (Preserve log, Fetch/XHR) and the Console tab. | Practical ability to capture request URLs, status codes, and payloads. |
| **14:00 – 14:35** | **Practical Mission 3 & 4:** Wire Inspection & Troubleshooting | **Hands-On (35 min)** | Investigate 4 realistic failure scenarios (offline 0, invalid 400, conflict 409, auth 403). | Technical evidence gathered for 4 failure scenarios. |
| **14:35 – 15:00** | Structured Bug Reporting, Final Assessment & Wrap-Up | Assessment & Synthesis | Write professional, reproducible bug reports; complete final 3-scenario assessment. | Submitted Final Assessment Bug Reports; course certificates/wrap-up. |

---

## Detailed Session Breakdown

### Morning Block: Exploration & Business Operations (08:00 – 12:00)

#### 08:00 – 08:20 (20 min) — Welcome & Orientation
- **Activity:** Instructor welcome, participant check-in, orientation of workshop goals.
- **Emphasis:** Reassure participants: *"You do not need to write code. You are here to learn how to operate, inspect, and diagnose this application as technical leaders."*
- **Check:** Ensure all 7 student PCs have `http://localhost:4200` open in Google Chrome.

#### 08:20 – 08:45 (25 min) — Decoupled Architecture Concepts
- **Theory (15 min):** The Restaurant Analogy (Angular = Dining Room, Spring Boot = Kitchen, PostgreSQL = Pantry, HTTP/JSON = Waitstaff).
- **Comparison for Instructor:** Compare with traditional server-rendered apps (Blade/JSP) where the kitchen had to build the entire dining plate and room on every request.
- **Demo (10 min):** Show two separate terminal windows or ports running (`:4200` and `:8080`) to visually prove separation.

#### 08:45 – 09:15 (30 min) — Angular Interface Tour
- **Demo (20 min):** Instructor walks through the UI: Header badge (`MF / SIT`), Navigation links, KPI summary cards, filter toolbar, and table rows.
- **Q&A (10 min):** Prompting students on how UI widgets relate to underlying business data.

#### 09:15 – 10:00 (45 min) — Practical Mission 1: Application Exploration
- **Format:** Individual or paired hands-on work at student workstations.
- **Task:** Students open the EMS app, filter by department (`SIT`, `DGT`, `DB`, `DRH`), search by name, observe KPI card recalculations, and document findings in their Workbook.
- **Instructor Role:** Walk the room, verify that everyone is clicking and filtering successfully.

#### 10:00 – 10:15 (15 min) — Morning Break

#### 10:15 – 10:40 (25 min) — Navigation & Client Routing
- **Theory & Demo (15 min):** Watch the browser address bar change from `/employees` to `/employees/new`. Notice that the browser reload icon never spins.
- **Explanation:** Explain Single Page Applications (SPA): the browser page is loaded once; Angular simply swaps screen templates in memory.
- **Transition (10 min):** Setting up Mission 2 (Data entry and modification).

#### 10:40 – 11:30 (50 min) — Practical Mission 2: Performing Business Operations
- **Format:** Hands-on business operations.
- **Task:** Students create a new employee (`FIN-2026-008`), modify an existing salary, change an employee's administrative status (`ACTIVE` $\rightarrow$ `ON_LEAVE`), and test form cancellation.
- **Instructor Role:** Guide students who mistype dates or leave required fields blank.

#### 11:30 – 12:00 (30 min) — Validation & Error Behaviors
- **Theory & Demo (15 min):** Why does the "Create Agent" button disable or show red text when an email is missing? (Frontend validation for UX). What happens when an identical matricule is submitted? (Backend validation HTTP 409 Conflict).
- **Quiz 1 (15 min):** Deliver Quiz 1 (5 questions on architecture and validation).

---

### Afternoon Block: Wire Investigation & Bug Reporting (13:00 – 15:00)

#### 13:00 – 13:25 (25 min) — The REST Wire & JSON
- **Theory & Demo (15 min):** The 4 HTTP verbs (`GET` = Read, `POST` = Create, `PUT` = Update, `DELETE` = Remove). JSON as the clean, human-readable data format.
- **Demonstration (10 min):** Open Swagger UI (`http://localhost:8080/swagger-ui.html`) to show the raw REST contract independent of Angular.

#### 13:25 – 14:00 (35 min) — Chrome DevTools Masterclass
- **Demonstration & Follow-Along:**
  1. Pressing `F12` to open Developer Tools.
  2. Docking DevTools to the bottom or right side.
  3. Configuring the **Network** tab: enabling **Preserve log**, selecting the **Fetch/XHR** filter.
  4. Inspecting the 5 key dimensions of a request: URL, Method, Status Code, Request Payload, Response Body.
  5. The **Console** tab: spotting red JavaScript errors vs yellow warnings.

#### 14:00 – 14:35 (35 min) — Practical Mission 3 & 4: Wire Inspection & Troubleshooting
- **Format:** Investigative hands-on lab.
- **Task:** Students open DevTools while performing operations:
  - Inspecting the `POST /api/employees` payload on creation.
  - Provoking and capturing a `400 Bad Request` (invalid email format).
  - Provoking and capturing a `409 Conflict` (duplicate matricule).
  - Testing the Day 5 Security tab to capture the `Authorization: Bearer <token>` header.
- **Deliverable:** Students copy/paste exact technical evidence into their diagnostic worksheets.

#### 14:35 – 15:00 (25 min) — Bug Reporting, Final Assessment & Wrap-Up
- **Assessment (15 min):** Students complete the 3-Scenario Final Assessment (identifying reproduction steps, expected vs actual result, and technical evidence).
- **Synthesis (10 min):** Review of the Golden Bug Report template, awarding completion acknowledgments, and closing Q&A.
