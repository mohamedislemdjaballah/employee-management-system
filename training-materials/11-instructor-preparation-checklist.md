# Deliverable 11: Instructor Pre-Flight Checklist & Emergency 30-Minute Plan
## The Field Readiness Guide for Day 3 Training

Use this checklist before walking into the classroom on Day 3 morning to guarantee that your presentation laptop and all 7 student workstations run smoothly.

---

## 1. Full Pre-Flight Checklist (1 Day Before Training)

### A. Laptop & Application Stack Verification
- [ ] **Docker / Database Running:**
  - Verify PostgreSQL container is healthy on port `5432` (`docker compose ps` shows `ems-postgres` Up).
  - Verify user `ems_user` exists and password connects in TablePlus (`localhost:5432`, `ems_db`).
- [ ] **Backend Spring Boot Running:**
  - Start backend via `.\mvnw.cmd spring-boot:run` in `backend/`.
  - Open `http://localhost:8080/swagger-ui.html` in Chrome $\rightarrow$ Execute `GET /api/employees` $\rightarrow$ Returns 200 OK with 6 initial employees.
- [ ] **Frontend Angular Running:**
  - Start frontend via `npm start` in `frontend/`.
  - Open `http://localhost:4200` $\rightarrow$ Confirm that the top banner does **NOT** show the blue "Simulation Mode" alert (which means it successfully connected to port 8080).
- [ ] **Port Check (Crucial for Laravel Developers!):**
  - Verify that Apache service (`PEMHTTPD-x64`) is stopped (`Stop-Service PEMHTTPD-x64`) so port 8080 remains open for Spring Boot.

### B. Classroom & Workstation Setup (The 7 Student PCs)
- [ ] **Google Chrome Installed:** Verify Chrome is installed on all 7 machines.
- [ ] **DevTools Access:** Press `F12` on each student PC to verify that Developer Tools are not blocked by corporate group policies.
- [ ] **App Accessible:** Open `http://localhost:4200` on each machine.
- [ ] **Dependency Pre-Warming:** Run `npm start` once on each machine so dependencies are pre-compiled and start instantly on Day 3.

### C. Presentation & Instructional Materials
- [ ] **Projector Resolution Check:** Connect instructor laptop to projector; verify text in Chrome and terminal is readable from the back row (use `Ctrl + +` to zoom 125% in Chrome).
- [ ] **Slide Deck Loaded:** Open `03-presentation-slides.md` in your presentation tool or Markdown reader.
- [ ] **Participant Workbooks:** Print 7 copies or distribute digital copies of `09-participant-workbook.md`.
- [ ] **Backup Screenshots Folder:** Have a local folder with screenshots of Swagger UI and DevTools in case an unexpected projector disconnect occurs.

---

## 2. Safe Test Scenarios Pre-Calculated for Demonstration

Keep these specific test values written on a sticky note next to your keyboard:

| Scenario | Values to Use | Expected Outcome |
| :--- | :--- | :--- |
| **New Agent Creation** | `FIN-2026-008`, `Sofiane CHIKHI`, `125000 DA`, Direction `DRH` | Success: `201 Created`, redirected to table. |
| **Duplicate Conflict** | Matricule `FIN-2026-001` (Karim Benali) | Rejection: `409 Conflict` error banner. |
| **Client Validation** | Email: `test-no-at-sign` | Form button blocked; email box turns red. |
| **JWT Login Demo** | Username: `admin`, Password: `finances2026` | Token issued; attached as `Bearer` header. |

---

## 3. The 30-Minute Emergency Preparation Plan (Zero-Time Scenario)

If you arrive at the venue with only 30 minutes before the 08:00 start time, execute **ONLY** these 5 high-priority steps:

### Minute 00 – 10: Launch the Application Suite
1. Double-click **`start-all.bat`** in the repository root.
2. Check that three windows open:
   - Window 1: Docker/PostgreSQL starting.
   - Window 2: Spring Boot starting on `:8080`.
   - Window 3: Angular compiling on `:4200`.
3. Open Chrome to `http://localhost:4200`. Click "+ Ajouter un Agent" and back to verify health.

### Minute 10 – 18: Projector & DevTools Arrangement
1. Plug into the projector (`Windows Key + P` $\rightarrow$ Select **Duplicate**).
2. Open Chrome with two tabs:
   - Tab 1: `http://localhost:4200`
   - Tab 2: `http://localhost:8080/swagger-ui.html`
3. Press `F12` on Tab 1, dock DevTools to the bottom, check **"Preserve log"**, and select **"Fetch/XHR"**.
4. Press `Ctrl + +` twice to enlarge the text for the audience.

### Minute 18 – 25: Verify the 7 Classroom PCs
1. Walk down the classroom rows.
2. On each PC, open Chrome to `http://localhost:4200`.
3. If any PC fails to reach PostgreSQL, remember the instant fallback:
   `mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=h2`
   *(Spring Boot will start in-memory with zero database setup required!)*

### Minute 25 – 30: Mental Warmup & Opening Script
1. Open `01-instructor-guide.md` on your phone or printed paper.
2. Review the opening sentence:
   > *"Good morning everyone. Today is about operational understanding and investigation. You will not write code today. We are going to look under the hood of a decoupled web application."*
3. Smile, welcome the first arrivals, and take your seat!
