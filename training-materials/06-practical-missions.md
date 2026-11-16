# Deliverable 06: Practical Workshop Missions
## Hands-On Operational & Diagnostic Missions for Participants
**Target System:** Employee Management System (EMS — Ministry of Finance / SIT)  
**Execution Environment:** Google Chrome (`http://localhost:4200`) + Chrome Developer Tools (`F12`)  

---

## Mission 1: Application Exploration & Architectural Audit

### 1. Mission Metadata
- **Time Allocation:** 45 Minutes (Morning Session: 09:15 – 10:00)
- **Objective:** Navigate the running application, audit the visual interface, test search and filter capabilities, and observe how data cards respond dynamically.
- **Prerequisites:** Google Chrome open to `http://localhost:4200`.

### 2. Participant Instructions
1. Open Google Chrome and verify that the page title reads: *"Portail RH - Ministère des Finances (SIT)"*.
2. Look at the top navigation bar. Identify the title and the links available.
3. Look at the four colored KPI Summary Cards:
   - What is the value shown for **Effectif Affiché**?
   - What is the value shown for **En Activité**?
   - What is the value shown for **En Congé / Détachement**?
   - What is the total displayed for **Masse Salariale Mensuelle**?
4. In the **Annuaire des Agents** table, count the number of employee rows.
5. In the **Direction / Structure** dropdown menu, select `SIT — Système d'Information du Trésor`.
   - Observe the table: which employees are displayed?
   - Observe the KPI cards: did the **Effectif Affiché** change?
6. In the **Recherche rapide** search input, type `Amina` and press Enter.
   - Who is displayed now?
7. Click the white button **"Réinitialiser"**.
   - Verify that all initial records return.

### 3. Expected Results
- Total initial employees: **6**.
- Initial Active: **5**, On Leave: **1** (Nadia KHELIFI).
- SIT employees: **3** (Karim BENALI, Amina MANSOURI, Mourad HADJADJ).
- Searching `Amina` isolates Amina MANSOURI.
- Resetting returns the view to 6 employees.

### 4. Instructor Solution & Evaluation Criteria
- **Evaluation Criteria:**
  - Participant successfully isolates records using both dropdown and text search.
  - Participant accurately records the initial KPI numbers in their workbook.
- **Instructor Solution:** If a student asks why the screen doesn't reload, explain that Angular executes filter logic instantly in memory without requesting a full HTML page.

### 5. Hints & Troubleshooting
- *Hint:* If the table is empty, check if you typed a space in the search box. Click **Réinitialiser** to clear all filters.

---

## Mission 2: Performing Business Operations (CRUD)

### 1. Mission Metadata
- **Time Allocation:** 50 Minutes (Morning Session: 10:40 – 11:30)
- **Objective:** Successfully execute standard business operations: create an employee, verify their appearance in the directory, edit an existing record, and observe administrative status changes.

### 2. Participant Instructions
1. From the Employee Directory, click the blue button **"+ Ajouter un Agent"**.
2. Notice the URL in your browser: it changed from `/employees` to `/employees/new`.
3. Fill out the form with the following official test record:
   - **Matricule Administratif:** `FIN-2026-008`
   - **Direction d'Affectation:** Select `DGT — Direction Générale du Trésor`
   - **Nom de Famille:** `CHIKHI`
   - **Prénom:** `Sofiane`
   - **Email:** `s.chikhi@finances.gov`
   - **Téléphone:** `0558 11 22 33`
   - **Poste / Grade:** `Inspecteur des Finances`
   - **Salaire Mensuel Brut:** `125000`
   - **Date de Recrutement:** Choose today's date.
   - **Statut Administratif:** `En Activité (ACTIVE)`
4. Click the blue button **"Créer l'agent"**.
   - Verify that you are redirected to `/employees`.
   - Verify that Sofiane CHIKHI appears in the table.
   - Verify that the total employee count increased from 6 to 7.
5. In the table row for Sofiane CHIKHI, click the white button **"Modifier"**.
   - Notice the URL: `/employees/<ID>/edit`.
   - Update his salary to `140000`.
   - Update his status to `En Congé / Détachement (ON_LEAVE)`.
   - Click **"Enregistrer les modifications"**.
6. Verify in the table that his status badge turned **Yellow** (`En Congé`) and the salary updated.

### 3. Expected Results
- New agent created with ID and proper DGT department badge.
- Edit operation successfully persists and updates the list view upon return.

### 4. Instructor Solution & Evaluation Criteria
- **Evaluation Criteria:**
  - Participant completes both creation and modification without skipping required fields.
  - Participant notes that the same form component handles both creation and editing.
- **Instructor Solution:** Walk to any participant who reports an error and check if their matricule or email collided with an existing record.

### 5. Hints & Troubleshooting
- *Hint:* If the "Créer l'agent" button does nothing, scroll up to check if any field has a red border (e.g. invalid date or missing required field).

---

## Mission 3: Wire Investigation with Chrome DevTools

### 1. Mission Metadata
- **Time Allocation:** 25 Minutes (Afternoon Session: 14:00 – 14:25)
- **Objective:** Use Chrome DevTools Network tab to observe, intercept, and record the exact HTTP request, method, status code, and payload of a real business transaction.

### 2. Participant Instructions
1. In Google Chrome, press **F12** to open Developer Tools.
2. Dock DevTools to the bottom of the window.
3. Click the **Network** tab.
4. Verify the two golden settings:
   - Check the box **"Preserve log"**.
   - Click the filter **"Fetch/XHR"**.
5. Click the red or grey clear button (🚫) to empty previous history.
6. In the application, click **"+ Ajouter un Agent"**.
7. Enter a test employee:
   - **Matricule:** `FIN-2026-009`
   - **Direction:** `DB — Direction du Budget`
   - **Nom:** `MEZIANI`
   - **Prénom:** `Lynda`
   - **Email:** `l.meziani@finances.gov`
   - **Poste:** `Analyste Financière`
   - **Salaire:** `115000`
   - **Date:** Today's date.
   - **Statut:** `ACTIVE`
8. In the DevTools Network tab, get ready to watch. Click **"Créer l'agent"**.
9. In the Network tab list, locate the request named **`employees`**.
10. Click on `employees` and record the following technical evidence:
    - **Request URL:** What is the full address?
    - **Request Method:** What HTTP verb was used?
    - **Status Code:** What 3-digit code was returned?
    - Click the **Payload** tab: What was sent?
    - Click the **Response** tab: What was returned?

### 3. Expected Results
- Request URL: `http://localhost:8080/api/employees`
- Request Method: `POST`
- Status Code: `201 Created`
- Payload: JSON object containing Lynda Meziani's fields.
- Response: JSON object containing generated `id: 8` and `departmentName: "Direction du Budget"`.

### 4. Instructor Solution & Evaluation Criteria
- **Evaluation Criteria:**
  - Participant locates the specific `POST` request among list requests.
  - Participant correctly identifies the difference between Payload (outgoing) and Response (incoming).
- **Instructor Solution:** Remind participants: `GET` requests usually have no payload; `POST` and `PUT` requests always have a JSON payload.

---

## Mission 4: Diagnostic Troubleshooting Scenarios

### 1. Mission Metadata
- **Time Allocation:** 20 Minutes (Afternoon Session: 14:25 – 14:45)
- **Objective:** Investigate and document 4 realistic application failure scenarios using DevTools evidence.

### 2. Scenario Instructions & Diagnostic Tasks

#### Scenario A: The Duplicate Matricule Conflict
1. Click **"+ Ajouter un Agent"**.
2. Enter the matricule `FIN-2026-001` (this belongs to Karim BENALI from Day 1!).
3. Fill out the other required fields with any test name and click **"Créer l'agent"**.
4. In DevTools Network tab, find the red request:
   - What is the HTTP Status Code? (Expected: `409 Conflict`).
   - Click the **Response** tab. What is the exact error message sent by Spring Boot?

#### Scenario B: The Invalid Email Format
1. Click **"+ Ajouter un Agent"**.
2. Enter a unique matricule (`FIN-2026-010`), but in the Email box, type: `invalid-email-address` (no `@` symbol).
3. Try to submit.
   - Observe: Did Angular let the request reach the network wire?
   - Why not? (Expected: Frontend validation stopped it immediately, turning the border red).

#### Scenario C: Inspecting the Security Token
1. In the top navigation bar, click **Atelier Sécurité (Jour 5)** (`/login-demo`).
2. Log in using the workshop demo credentials:
   - **Username:** `admin`
   - **Password:** `finances2026`
3. Click **Obtenir un Jeton JWT**.
4. Click **Tester un appel API avec ce jeton**.
5. In DevTools Network tab, click on the `employees` request:
   - Scroll down to **Request Headers**.
   - What is the name and prefix of the security header? (Expected: `Authorization: Bearer eyJhbGci...`).

#### Scenario D: The Deleted Record Check
1. In the Directory, click the red button **"Supprimer"** on any test employee you created.
2. Confirm the browser dialog.
3. In DevTools Network tab, locate the delete request:
   - What was the Request Method? (Expected: `DELETE`).
   - What was the Status Code? (Expected: `204 No Content`).

### 3. Expected Diagnostic Summary Table

| Scenario | HTTP Method | Endpoint | Status Code | Root Cause Diagnosis |
| :--- | :--- | :--- | :--- | :--- |
| **A: Duplicate** | `POST` | `/api/employees` | `409 Conflict` | Unique constraint violation in PostgreSQL; matricule already assigned. |
| **B: Bad Email** | None (blocked) | N/A | None (0) | Client-side reactive form validation prevented submission locally. |
| **C: Token Auth** | `GET` | `/api/employees` | `200 OK` | Angular HTTP Interceptor attached the `Authorization: Bearer` JWT header. |
| **D: Deletion** | `DELETE` | `/api/employees/:id` | `204 No Content` | Successful database row deletion; empty response body returned. |

### 4. Instructor Solution & Evaluation Criteria
- **Evaluation Criteria:**
  - Participant distinguishes between client-side block (Scenario B) and server-side rejection (Scenario A).
  - Participant identifies the `Authorization: Bearer` header in Scenario C.
