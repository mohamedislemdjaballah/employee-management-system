# Instructor Preparation Playbook (5 Days, 8h/Day — Split Between Laravel & Django Instructors)

## 1. Current Machine Diagnostic & What You Need Installed First

When we inspected your machine today (Nov 7), here is what was found:
- ✅ **Node.js `v22.18.0` & NPM `10.9.3`**: Already installed! You can run and build the **Angular 18** frontend right now.
- ✅ **Docker `28.1.1`**: Already installed! You can spin up PostgreSQL 16 + pgAdmin in one command (`docker compose up -d`).
- ⚠️ **Java Version**: Your machine currently has **Java 1.8 (`1.8.0_451`)** on the PATH, and Maven (`mvn`) is not on the PATH.
  - **Spring Boot 3 requires JDK 17 or JDK 21.**
  - Since you are focusing on **Angular**, you don't even need Java running during your first 3 prep days because we built an automatic **Offline Demo Fallback** into `EmployeeService`!
  - However, before Day 4 (Full-Stack Integration), install **Eclipse Temurin JDK 21** and **Maven** (or IntelliJ IDEA Community Edition, which bundles both JDK downloaders and Maven out-of-the-box):
    ```powershell
    winget install EclipseAdoptium.Temurin.21.JDK
    winget install Apache.Maven
    ```

---

## 2. How to Prepare the 7 Classroom PCs (Pre-Setup Strategy)

You mentioned you have internet access in the classroom and will pre-setup the PCs while leaving a couple of steps optional if students want to install them themselves. **Here is the best-practice classroom setup strategy so you don't lose Day 1 to installation bugs:**

### Pre-Install on ALL 7 PCs (Heavy Downloads)
Do not let 7 PCs download JDK, Node, PostgreSQL, and 400 MB of Maven/NPM dependencies at the exact same moment on Day 1 Morning — classroom networks often throttle or fail.
1. **JDK 21 LTS** (Eclipse Temurin 21 MSI installer — check the option *"Set JAVA_HOME variable"* during installation).
2. **Node.js LTS (v20 or v22)** (MSI installer).
3. **PostgreSQL 16** (Windows installer + pgAdmin 4) — set the password tosomething simple and uniform on all 7 PCs (e.g., `postgres` or `ems_password`) and write it on the whiteboard!
4. **IDEs**:
   - **IntelliJ IDEA Community Edition** (for Spring Boot)
   - **VS Code** + the **Angular Language Service** extension (for Angular)
5. **Pre-Warm the Dependency Caches (Huge Time Saver!)**:
   - Copy this `demo-employee-management` folder onto each PC (e.g., `C:\Formation\demo-employee-management`).
   - Run `cd frontend && npm install` once on each PC before Nov 15. This populates the local npm cache so `npm install` takes 3 seconds during class.
   - Run `cd backend && mvn dependency:go-offline` once on each PC. This downloads all Spring Boot JARs into `C:\Users\<User>\.m2\repository` so Maven works instantly even if internet drops!

### What to Leave for Students to Do Hands-On
Instead of having them run Windows `.msi` installers, let them do the **developer setup steps**:
- Creating the `ems_db` database in pgAdmin 4 and running `database/init.sql`.
- Opening `backend/` in IntelliJ, editing `application.yml` with their database credentials, and running `EmsApplication.java`.
- Installing the Angular CLI globally (`npm install -g @angular/cli`) or running `npm start` in `frontend/`.

---

## 3. The 5-Day Co-Instructor Preparation Plan (8 Hours/Day Max)

Since the entire working codebase and French lab manuals are now generated in this repository, you and your coworker will likely finish your preparation in **5 to 6 hours per day** instead of 8!

### Prep Day 1: Architecture Alignment & Tooling (You + Coworker)
- **Morning (2h Together):**
  - Open `docs/01-architecture-for-legacy-engineers.md` and agree on how you will co-present the first 90 minutes of Day 1.
  - Review the JSON contract between Spring Boot and Angular:
    - Look at [`EmployeeResponseDto.java`](../backend/src/main/java/com/finances/ems/dto/EmployeeResponseDto.java) and compare it line-by-line with [`employee.model.ts`](../frontend/src/app/models/employee.model.ts). Notice the field names are 100% identical!
- **Afternoon (5h Split):**
  - **You (Angular):** Run `cd frontend && npm start`. Open `http://localhost:4200` and click through every screen (List, Filters, Create Agent, Edit Agent, Delete, Day 5 Security). Read `docs/03-frontend-angular-guide.md`.
  - **Coworker (Spring Boot):** Run PostgreSQL and start `backend/`. Open `http://localhost:8080/swagger-ui.html` and test all endpoints. Read `docs/02-backend-spring-boot-guide.md`.

### Prep Day 2: Mastering the Read Flow (List + Services + Dependency Injection)
- **You (Angular — 6h):**
  - Study these 4 files in order:
    1. [`main.ts`](../frontend/src/main.ts) & [`app.config.ts`](../frontend/src/app/app.config.ts) (How Angular boots and registers `HttpClient` and `Router`).
    2. [`employee.model.ts`](../frontend/src/app/models/employee.model.ts) (TypeScript interfaces).
    3. [`employee.service.ts`](../frontend/src/app/services/employee.service.ts) (How `@Injectable` and `this.http.get<Employee[]>()` work).
    4. [`employee-list.component.ts`](../frontend/src/app/components/employee-list/employee-list.component.ts) & [`.html`](../frontend/src/app/components/employee-list/employee-list.component.html) (How `ngOnInit()` calls the service and `@for` renders the table).
  - **Practice Exercise:** Add a temporary column or a new KPI card in `employee-list.component.html` so you feel comfortable editing Angular templates live in front of students.
- **Coworker (Spring Boot — 6h):**
  - Study `Department.java`, `Employee.java`, `DepartmentRepository.java`, and `EmployeeRepository.java`.
  - Practice building the `@Entity` and `JpaRepository` from scratch in a blank project so they can live-code it smoothly on Day 1 Afternoon.

### Prep Day 3: Mastering the Write Flow (Reactive Forms, Validation & Routing)
- **You (Angular — 6h):**
  - Study [`app.routes.ts`](../frontend/src/app/app.routes.ts) and [`employee-form.component.ts`](../frontend/src/app/components/employee-form/employee-form.component.ts) + [`.html`](../frontend/src/app/components/employee-form/employee-form.component.html).
  - Understand how **one component** handles both **Create** (`/employees/new`) and **Edit** (`/employees/:id/edit`) by checking `this.route.snapshot.paramMap.get('id')`.
  - Practice explaining Reactive Forms (`FormBuilder`, `Validators.required`, `formControlName="firstName"`) using your Laravel `FormRequest` mental model.
- **Coworker (Spring Boot — 6h):**
  - Study `EmployeeService.java`, `EmployeeController.java`, `EmployeeRequestDto.java`, and `GlobalExceptionHandler.java`.
  - Practice demonstrating how `@Valid` catches empty fields or bad emails and returns a clean `400 Bad Request` JSON.

### Prep Day 4: Full-Stack Integration & Day 5 Security Module
- **Morning (4h Together):**
  - Run PostgreSQL + Spring Boot (`:8080`) + Angular (`:4200`) together on the same machine.
  - Notice how the Angular badge automatically switches to green: **"Connecté à Spring Boot + PostgreSQL (:8080)"**.
  - Open Chrome DevTools (**F12 $\rightarrow$ Network / Réseau tab**). Perform a Create, an Edit, a Filter, and a Delete. Practice showing the HTTP Request and JSON Response in Chrome DevTools — **legacy engineers love seeing the wire protocol!**
  - Temporarily comment out `@Configuration` in [`CorsConfig.java`](../backend/src/main/java/com/finances/ems/config/CorsConfig.java) to see the browser CORS error in red, then re-enable it. That 5-minute demo will save your students hours of confusion in their careers.
- **Afternoon (3h Together):**
  - Walk through the Day 5 Security screen (`http://localhost:4200/login-demo`), [`AuthDemoController.java`](../backend/src/main/java/com/finances/ems/controller/AuthDemoController.java), [`auth.service.ts`](../frontend/src/app/services/auth.service.ts), and [`auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts).

### Prep Day 5: Rehearsal & Classroom PC Setup
- Set up the 7 classroom PCs using the checklist in Section 2.
- Do a quick 90-minute rehearsal with your coworker where you each present your opening 20 minutes of Day 1 and Day 3.

---

## 4. Deep Cheat-Sheet for You: Laravel $\rightarrow$ Angular 18

Since your background is **Laravel**, use this translation table whenever a student asks how Angular works:

### A. Project Structure Comparison
- **Laravel `routes/web.php`** $\rightarrow$ **Angular [`src/app/app.routes.ts`](../frontend/src/app/app.routes.ts)**
- **Laravel `app/Http/Controllers/EmployeeController.php` + `resources/views/employees/index.blade.php`** $\rightarrow$ **Angular [`employee-list.component.ts`](../frontend/src/app/components/employee-list/employee-list.component.ts) + [`employee-list.component.html`](../frontend/src/app/components/employee-list/employee-list.component.html)**
  - In Laravel, the Controller fetches data and passes it to a Blade view on the server.
  - In Angular, the `.component.ts` class runs **in the browser**, calls `EmployeeService` to fetch JSON from Spring Boot, stores the array in `this.employees`, and the `.component.html` template automatically updates the DOM!
- **Laravel `app/Services/EmployeeService.php`** $\rightarrow$ **Angular [`src/app/services/employee.service.ts`](../frontend/src/app/services/employee.service.ts)**
- **Laravel `app/Http/Middleware/Authenticate.php`** $\rightarrow$ **Angular [`src/app/interceptors/auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts)**

### B. Template Syntax Comparison (Blade vs. Angular 18)

| Feature | Laravel Blade | Angular 18 Template |
| :--- | :--- | :--- |
| **Print variable** | `{{ $employee->firstName }}` | `{{ emp.firstName }}` |
| **Format number** | `{{ number_format($emp->salary, 2) }}` | `{{ emp.salary \| number:'1.2-2' }}` |
| **If / Else** | `@if($isLoading) ... @else ... @endif` | `@if (isLoading) { ... } @else { ... }` |
| **Loop** | `@foreach($employees as $emp) ... @endforeach` | `@for (emp of employees; track emp.id) { ... }` |
| **Pass attribute** | `<a href="{{ route('emp.edit', $emp->id) }}">` | `<a [routerLink]="['/employees', emp.id, 'edit']">` |
| **Click event** | `<button onclick="...">` (or Livewire `wire:click`) | `<button (click)="confirmDelete(emp)">` |
| **Two-way input** | Livewire `wire:model="searchTerm"` | `[(ngModel)]="searchTerm"` |

> **Remember the Angular Bracket Mnemonic (Teach this on Day 3!):**
> - **`[property]="value"`** (Square brackets) = Data flows **from TypeScript $\rightarrow$ HTML** (Input / Property Binding).
> - **`(event)="handler()"`** (Parentheses) = Action flows **from HTML $\rightarrow$ TypeScript** (Output / Event Binding).
> - **`[(ngModel)]="field"`** ("Banana in a box" `[()]`) = **Two-way binding** (both directions at once!).

---

## 5. Deep Cheat-Sheet for Your Coworker: Django $\rightarrow$ Spring Boot 3

Share this table with your coworker who knows **Django**:

| Django / DRF Concept | Spring Boot 3 Equivalent in `backend/` |
| :--- | :--- |
| `manage.py runserver` | `mvn spring-boot:run` (or Run `EmsApplication.java` in IDE) |
| `settings.py` (`DATABASES`) | [`src/main/resources/application.yml`](../backend/src/main/resources/application.yml) |
| `models.Model` | `@Entity @Table(name = "employees")` ([`Employee.java`](../backend/src/main/java/com/finances/ems/entity/Employee.java)) |
| `models.ForeignKey(Department)` | `@ManyToOne @JoinColumn(name = "department_id")` |
| `Employee.objects.all()` / `.filter()` | `JpaRepository<Employee, Long>` ([`EmployeeRepository.java`](../backend/src/main/java/com/finances/ems/repository/EmployeeRepository.java)) |
| `serializers.ModelSerializer` | `record EmployeeRequestDto` + `record EmployeeResponseDto` |
| `@api_view(['GET', 'POST'])` / `ViewSet` | `@RestController @RequestMapping("/api/employees")` ([`EmployeeController.java`](../backend/src/main/java/com/finances/ems/controller/EmployeeController.java)) |
| `django-cors-headers` | `WebMvcConfigurer.addCorsMappings()` ([`CorsConfig.java`](../backend/src/main/java/com/finances/ems/config/CorsConfig.java)) |
| `drf-spectacular` / `drf-yasg` | `springdoc-openapi-starter-webmvc-ui` (`/swagger-ui.html`) |
| `transaction.atomic()` | `@Transactional` on [`EmployeeService.java`](../backend/src/main/java/com/finances/ems/service/EmployeeService.java) |

### Why We Avoided Lombok in `backend/` (Crucial Classroom Tip!)
In many online Spring Boot tutorials, authors use `@Data` or `@Getter/@Setter` from **Lombok**. In a classroom of 7 PCs, Lombok frequently breaks if a student's IDE doesn't have the Lombok plugin or "Enable Annotation Processing" checked.
In our `backend/` code:
- DTOs use native **Java 17+ `record`** (zero boilerplate, built into Java itself!).
- Entities (`Department` and `Employee`) use standard, explicit Java getters/setters.
This guarantees **zero IDE compilation issues** across all 7 classroom machines.
