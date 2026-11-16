# Deliverable 10: Standard Professional Bug Report Template
## The Golden Standard for Communicating with Software Developers

This template provides the exact technical structure required by enterprise development and QA teams. A bug report written with this structure eliminates ambiguity, prevents back-and-forth email arguments, and provides immediate reproducible proof.

---

## 1. Blank Reusable Template

```text
================================================================================
                        PROFESSIONAL BUG REPORT
================================================================================

TITLE:
[Short, specific description including the action and the failure]

METADATA:
- Application Name: Employee Management System (EMS)
- Environment: Local / Staging / Production
- Page / URL: [e.g. http://localhost:4200/employees/new]
- User Role / Account: [e.g. ADMIN_DRH, AGENT_REGIONAL]
- Browser Version: [e.g. Google Chrome 128.0 (Windows 11)]
- Date & Timestamp: [e.g. 2026-11-16 14:30 UTC+1]
- Severity: Blocker / Major / Normal / Minor

STEPS TO REPRODUCE:
1. Navigate to ...
2. Click on ...
3. Enter the following values:
   - Field A: "..."
   - Field B: "..."
4. Click on [Button Name]

EXPECTED RESULT:
[What should have happened according to business rules or specifications]

ACTUAL RESULT:
[What actually happened on the screen, including error banners or lack of response]

TECHNICAL EVIDENCE (FROM CHROME DEVTOOLS F12):
- Console Tab Error: [Copy exact red text if any, or "None"]
- Network Request URL: [e.g. http://localhost:8080/api/employees]
- HTTP Method: [GET / POST / PUT / DELETE]
- HTTP Status Code: [e.g. 400 Bad Request, 409 Conflict, 500 Server Error]
- Outgoing Request Payload (JSON):
  [Paste sanitized request payload here]
- Incoming Response Body (JSON):
  [Paste server error JSON here]

SCREENSHOT / RECORDING:
[Attach cropped screenshot showing both the user interface and DevTools Network tab]

SENSITIVE DATA SANITIZATION CHECKLIST:
[ ] Passwords redacted or removed
[ ] Authorization JWT tokens redacted
[ ] Confidential citizen/financial data masked

ADDITIONAL NOTES:
[Any relevant observations, such as whether this is a regression from a previous release]
================================================================================
```

---

## 2. Completed Example 1: Duplicate Resource Conflict (HTTP 409)

```text
================================================================================
                        PROFESSIONAL BUG REPORT
================================================================================

TITLE:
Employee registration fails with HTTP 409 Conflict when using existing matricule

METADATA:
- Application Name: Employee Management System (EMS)
- Environment: Local Development (Port 4200 / 8080)
- Page / URL: http://localhost:4200/employees/new
- User Role / Account: ROLE_ADMIN (admin)
- Browser Version: Google Chrome 128 (Windows 11 64-bit)
- Date & Timestamp: 2026-11-16 11:15:20
- Severity: Normal (Expected business validation behavior)

STEPS TO REPRODUCE:
1. Navigate to http://localhost:4200/employees
2. Click on "+ Ajouter un Agent" (redirects to /employees/new)
3. Fill in the form fields:
   - Matricule: "FIN-2026-001" (Note: already assigned to Karim BENALI)
   - Direction: "SIT"
   - Nom: "DAHMANI"
   - Prénom: "Rachid"
   - Email: "r.dahmani@finances.gov"
   - Salaire: "110000"
   - Date: "2026-11-16"
   - Statut: "ACTIVE"
4. Click on "Créer l'agent"

EXPECTED RESULT:
If the matricule already exists, the form should highlight the Matricule input box with a field-specific warning before submission, or display an inline warning beneath the input.

ACTUAL RESULT:
The form submits over the network. A generic top error banner appears stating: "Une erreur est survenue lors de l'enregistrement. Vérifiez l'unicité du matricule et de l'email." The user is not told which specific field conflicted.

TECHNICAL EVIDENCE (FROM CHROME DEVTOOLS F12):
- Console Tab Error: None (No JavaScript crash)
- Network Request URL: http://localhost:8080/api/employees
- HTTP Method: POST
- HTTP Status Code: 409 Conflict
- Outgoing Request Payload (JSON):
  {
    "matricule": "FIN-2026-001",
    "firstName": "Rachid",
    "lastName": "DAHMANI",
    "email": "r.dahmani@finances.gov",
    "jobTitle": "Technicien Réseau",
    "salary": 110000,
    "hireDate": "2026-11-16",
    "status": "ACTIVE",
    "departmentId": 1
  }
- Incoming Response Body (JSON):
  {
    "timestamp": "2026-11-16T11:15:21",
    "status": 409,
    "error": "Conflict",
    "message": "Un agent avec le matricule 'FIN-2026-001' existe déjà."
  }

SCREENSHOT / RECORDING:
Attached: [bug_screenshot_409_conflict.png showing network tab highlighted in red]

SENSITIVE DATA SANITIZATION CHECKLIST:
[x] Passwords redacted or removed
[x] Authorization JWT tokens redacted
[x] Confidential citizen/financial data masked

ADDITIONAL NOTES:
Feature request for frontend team: Map the 409 response message directly to the 'matricule' form control invalid state rather than a generic banner.
================================================================================
```

---

## 3. Completed Example 2: Expired Session Token (HTTP 401 Unauthorized)

```text
================================================================================
                        PROFESSIONAL BUG REPORT
================================================================================

TITLE:
Directory list returns HTTP 401 Unauthorized after 8 hours of inactivity without redirecting to login

METADATA:
- Application Name: Employee Management System (EMS)
- Environment: Staging
- Page / URL: http://localhost:4200/employees
- User Role / Account: ROLE_AGENT
- Browser Version: Google Chrome 128
- Date & Timestamp: 2026-11-16 14:05:00
- Severity: Major (User left on blank screen without login prompt)

STEPS TO REPRODUCE:
1. Log into portal with valid credentials at 08:00
2. Leave the browser tab open and untouched for 6 hours (token expires)
3. Return to the computer at 14:00 and click "↻ Actualiser" on the Employee Directory

EXPECTED RESULT:
The application should detect that the JWT token is expired, clear sessionStorage, and smoothly redirect the user to `/login` with an informational message: "Your session has expired. Please log in again."

ACTUAL RESULT:
The table displays "Aucun agent ne correspond aux critères" and a red banner appears: "Impossible de récupérer la liste des agents." The user remains on the protected page without understanding that their session expired.

TECHNICAL EVIDENCE (FROM CHROME DEVTOOLS F12):
- Console Tab Error: HttpErrorResponse status: 401, statusText: "Unauthorized"
- Network Request URL: http://localhost:8080/api/employees
- HTTP Method: GET
- HTTP Status Code: 401 Unauthorized
- Outgoing Request Payload (JSON): None (GET request)
- Request Headers:
  Authorization: Bearer [REDACTED_EXPIRED_JWT_TOKEN]
- Incoming Response Body (JSON):
  {
    "status": 401,
    "error": "Unauthorized",
    "message": "JWT Token has expired at 2026-11-16T13:00:00Z"
  }

SCREENSHOT / RECORDING:
Attached: [bug_screenshot_401_expired_token.png]

SENSITIVE DATA SANITIZATION CHECKLIST:
[x] Passwords redacted or removed
[x] Authorization JWT tokens redacted
[x] Confidential citizen/financial data masked

ADDITIONAL NOTES:
Requires adding an HTTP Error Interceptor on the frontend that catches 401 errors globally and calls `authService.logout()` and `router.navigate(['/login'])`.
================================================================================
```
