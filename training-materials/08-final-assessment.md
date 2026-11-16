# Deliverable 08: Final Practical Assessment & Evaluation Rubric
## End-of-Workshop Capstone Assessment: Operational Diagnostics & Technical Communication

This assessment measures whether participants can inspect an existing application, diagnose problems using Chrome Developer Tools, and write clear, structured bug reports that enable developers to fix issues quickly.

---

## 1. Assessment Instructions for Participants

- **Duration:** 25 Minutes (14:35 – 15:00)
- **Rules:** Open application, open workbook, open DevTools. You are encouraged to use Chrome DevTools (`F12`).
- **Goal:** Review the 3 real-world scenarios below. Choose **ONE primary scenario** (or complete all three if time permits) and write a complete, professional Bug Report using the standard template.

---

## 2. The 3 Diagnostic Scenarios

### Scenario A: The Blocked Form (Validation Problem)
> **Context:** An administrative clerk is attempting to register a new employee. After filling out the form and clicking "Créer l'agent", nothing happens. The form does not submit, the page does not redirect, and no network request appears in the DevTools Network tab.
> 
> **Your Task:**
> 1. What was the clerk trying to do?
> 2. Where is the problem located (Frontend vs Backend)?
> 3. What steps would you take to identify the missing or invalid field?
> 4. Write a concise bug report or guidance note.

### Scenario B: The Denied Deletion (Authorization Problem)
> **Context:** A regional HR assistant logs into the portal. They navigate to the Employee Directory and click "Supprimer" on an employee record. An error dialog appears saying *"Opération impossible"*. In the DevTools Network tab, the request `DELETE http://localhost:8080/api/employees/4` shows HTTP Status Code `403 Forbidden`.
> 
> **Your Task:**
> 1. What is the difference between this error and an invalid password?
> 2. What technical evidence should be captured?
> 3. Is this a backend crash (bug) or a security configuration issue?
> 4. Write a clear technical report.

### Scenario C: The Server Crash on Submission (Server 500 Problem)
> **Context:** A user submits an employee creation form. After clicking "Créer l'agent", a red banner appears at the top: *"Erreur Serveur: Une erreur interne est survenue"*. In DevTools Network tab, the request `POST http://localhost:8080/api/employees` is highlighted in red with Status Code `500 Internal Server Error`.
> 
> **Your Task:**
> 1. List the reproduction steps.
> 2. Extract the technical evidence (URL, Method, Status, Payload).
> 3. State clearly why this must be escalated to backend developers immediately.
> 4. Complete the Golden Bug Report.

---

## 3. Instructor Scoring Rubric (100 Points Total)

| Evaluation Dimension | Description | Points |
| :--- | :--- | :--- |
| **1. Diagnostic Accuracy** | Correctly identifies where the problem originates (Client Validation, Authorization policy, or Server Exception). | **25 pts** |
| **2. Reproduction Steps** | Steps are clear, numbered, sequential, and specific enough for anyone to reproduce the issue in under 60 seconds. | **25 pts** |
| **3. Technical Evidence** | Correctly identifies and records: HTTP Method, Endpoint URL, Status Code, and Payload/Response. | **30 pts** |
| **4. Communication & Safety** | Professional language, clear separation of expected vs actual behavior, and **zero unredacted sensitive data**. | **20 pts** |

---

## 4. Expected Model Answers (For the Instructor)

### Model Answer: Scenario A (Validation Problem)
- **Origin:** Frontend Reactive Form Validation.
- **Evidence:** No network traffic generated (0 requests). The form control (e.g. Email missing `@` or Salary empty) is marked invalid in DOM.
- **Diagnosis:** Angular caught the invalid field locally and prevented form submission to protect the network.
- **Solution:** Scroll up, locate the red-bordered field with helper text, correct the value, and submit.

### Model Answer: Scenario B (Authorization 403 Problem)
- **Origin:** Backend Security / Authorization Engine (Spring Security).
- **Evidence:**
  - Method: `DELETE`
  - URL: `http://localhost:8080/api/employees/4`
  - Status: `403 Forbidden`
- **Diagnosis:** The user was successfully authenticated (identity verified), but their assigned Role lacks the permission `EMPLOYEE_DELETE`. This is not a crash; it is an authorization block.
- **Action:** Check the user's role in the database or admin console and assign deletion privileges if authorized.

### Model Answer: Scenario C (Server 500 Problem — Completed Bug Report)

```text
TITLE: HTTP 500 Internal Server Error when submitting employee creation form

ENVIRONMENT:
- Application: Employee Management System (EMS)
- URL: http://localhost:4200/employees/new
- Browser: Google Chrome Version 128 (Windows 11)
- User Role: ROLE_ADMIN
- Date & Time: 2026-11-16 14:45

STEPS TO REPRODUCE:
1. Navigate to http://localhost:4200/employees/new
2. Enter Matricule: "FIN-2026-099"
3. Enter Name: "Karim BENALI", Salary: "145000", Direction: "SIT"
4. Click the blue button "Créer l'agent"

EXPECTED RESULT:
The employee is saved to PostgreSQL, returns HTTP 201 Created, and redirects to /employees.

ACTUAL RESULT:
A red error banner appears on screen: "Erreur Serveur: Une erreur interne est survenue". The user remains on the creation page.

TECHNICAL EVIDENCE:
- HTTP Method: POST
- Endpoint URL: http://localhost:8080/api/employees
- HTTP Status Code: 500 Internal Server Error
- Response Body: 
  {
    "timestamp": "2026-11-16T14:45:12",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Cannot invoke 'com.finances.ems.entity.Department.getId()' because 'department' is null"
  }

SCREENSHOT:
[Attached: screenshot_create_employee_500.png showing DevTools Network tab with red POST request and response payload]

ADDITIONAL NOTES:
This is an unhandled NullPointerException in the backend service layer when resolving department foreign key. Escalated to Backend Development Team.
```

---

## 5. Instructor Evaluation Sheet

| Participant Name | Scenario Chosen | Diagnostic (25) | Repro Steps (25) | Tech Evidence (30) | Safety/Clarity (20) | Total (/100) | Feedback Notes |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Participant 1** | | | | | | | |
| **Participant 2** | | | | | | | |
| **Participant 3** | | | | | | | |
| **Participant 4** | | | | | | | |
| **Participant 5** | | | | | | | |
| **Participant 6** | | | | | | | |
| **Participant 7** | | | | | | | |
