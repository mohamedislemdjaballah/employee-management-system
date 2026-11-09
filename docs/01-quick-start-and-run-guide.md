# Quick Start & Run Guide (Day 1 Classroom Guide)
## The Easiest Guide to Running and Inspecting the Application

This guide is designed for Day 1. It provides single-click launchers, terminal commands, and exact configuration for **Eclipse**, **VS Code**, and **TablePlus**.

---

## 1. Quickest Method: One-Click Launch (Windows)

Simply double-click the launcher script at the root of the project:

- **Option A (Double-Click):** Double-click `start-all.bat`
- **Option B (PowerShell):** Right-click `start-all.ps1` $\rightarrow$ *Run with PowerShell*

### What this launcher does automatically:
1. Starts the PostgreSQL 16 container (`ems-postgres` on port `5432`) if Docker is installed.
2. Opens a command window and boots **Spring Boot** on `http://localhost:8080`.
3. Opens a command window and boots **Angular** on `http://localhost:4200`.

---

## 2. Manual Terminal Commands (Step-by-Step)

If you or students prefer running step-by-step in separate terminal tabs:

### Step 1: Start PostgreSQL (Port 5432)
```powershell
# In the project root directory:
docker compose up -d
```
*(If Docker is not installed, ensure your local Windows PostgreSQL service is started, or see Section 5 below for the zero-install H2 mode).*

### Step 2: Start Spring Boot Backend (Port 8080)
```powershell
cd backend
.\mvnw.cmd spring-boot:run
```
- Verify in browser: Open **http://localhost:8080/swagger-ui.html** (Interactive API Documentation).

### Step 3: Start Angular Frontend (Port 4200)
```powershell
cd frontend
npm start
```
- Open in browser: **http://localhost:4200**

---

## 3. Tool Setup & Inspection

### A. Connecting TablePlus to PostgreSQL
**TablePlus** is the recommended visual database client for this course.

1. Open **TablePlus** $\rightarrow$ Click **Create a new connection...** $\rightarrow$ Select **PostgreSQL**.
2. Enter the exact connection details:
   - **Name:** `EMS Local DB`
   - **Host:** `localhost`
   - **Port:** `5432`
   - **User:** `ems_user`
   - **Password:** `ems_password`
   - **Database:** `ems_db`
3. Click **Test** (should turn green), then click **Connect**.
4. You will see two tables: `departments` and `employees`.
   - Double-click `employees` to view the initial seeded records.
   - You can see the foreign key `department_id` linking to `departments.id`.

---

### B. Opening the Backend in Eclipse (Eclipse IDE / STS)
1. Open **Eclipse IDE for Enterprise Java and Web Developers** (or Eclipse Spring Tools Suite - STS).
2. Go to **File** $\rightarrow$ **Import...**
3. Select **Maven** $\rightarrow$ **Existing Maven Projects** $\rightarrow$ Click **Next**.
4. Browse to the `backend` directory (e.g. `C:\Users\EK\Documents\GitHub\demo-employee-management\backend`).
5. Check `pom.xml` $\rightarrow$ Click **Finish**.
6. Once indexed:
   - Expand `src/main/java` $\rightarrow$ `com.finances.ems`.
   - Right-click `EmsApplication.java` $\rightarrow$ **Run As** $\rightarrow$ **Java Application** (or Spring Boot App).
   - The Eclipse Console will display the Spring Boot banner and Hibernate SQL logs.

---

### C. Opening the Frontend in VS Code
1. Open **Visual Studio Code**.
2. Go to **File** $\rightarrow$ **Open Folder...**
3. Select the `frontend` directory.
4. Recommended VS Code Extensions:
   - **Angular Language Service**
   - **Prettier - Code formatter**
5. Open an integrated terminal (`Ctrl + ~`) and run `npm start`.

---

## 4. System URLs & Ports at a Glance

| Component | Technology | URL / Port | Credentials / Purpose |
| :--- | :--- | :--- | :--- |
| **Frontend Application** | Angular 18 | `http://localhost:4200` | Main user interface (SPA) |
| **Swagger UI (OpenAPI)** | SpringDoc | `http://localhost:8080/swagger-ui.html` | Interactive API documentation |
| **REST API Base** | Spring Boot 3 | `http://localhost:8080/api/employees` | JSON endpoints |
| **Database Server** | PostgreSQL 16 | `localhost:5432` | User: `ems_user` / Pass: `ems_password` |
| **pgAdmin (Optional)** | Web GUI | `http://localhost:5050` | Email: `admin@finances.gov` / Pass: `admin` |

---

## 5. Classroom Troubleshooting & Fallbacks (Zero-Failure Guarantee)

### Fallback 1: PostgreSQL cannot be installed on a student machine?
Launch Spring Boot with the in-memory **H2 database profile**:
```powershell
cd backend
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=h2
```
Spring Boot will start instantly without PostgreSQL and provide an in-memory SQL database with the exact same data!

### Fallback 2: Spring Boot is not started yet, but you want to teach Angular?
The Angular application has an **Offline Simulation Fallback** built into `EmployeeService`.
When you run `npm start` in `frontend/`, Angular detects that port 8080 is unreachable, displays a friendly blue banner, and runs with mock in-memory data so students can click, filter, and inspect components immediately!
