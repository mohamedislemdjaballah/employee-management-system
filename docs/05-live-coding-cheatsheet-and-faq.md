# Guide 05 : Instructor Live-Coding Cheat-Sheet & Classroom Troubleshooting FAQ
*(Print this page or keep it open on your tablet/second screen while teaching Days 3, 4 & 5)*

---

## 1. Essential Angular CLI Commands (Your `php artisan` Equivalents)

When teaching the 7 engineers on Day 3, show them these CLI commands in the terminal:

| What You Want to Do | Laravel `artisan` Command | Angular `ng` CLI Command |
| :--- | :--- | :--- |
| **Check version** | `php artisan --version` | `npx ng version` |
| **Start dev server** | `php artisan serve` | `npm start` *(or `npx ng serve --open`)* |
| **Create a Component** | `php artisan make:component EmployeeList` | `npx ng generate component components/employee-list` *(shorthand: `npx ng g c ...`)* |
| **Create a Service** | *(manual class in `app/Services`)* | `npx ng generate service services/employee` *(shorthand: `npx ng g s ...`)* |
| **Create an Interceptor** | `php artisan make:middleware AuthMiddleware` | `npx ng generate interceptor interceptors/auth` |
| **Build for Production** | `npm run build` (Vite) | `npm run build` *(outputs static files to `dist/ems-frontend`)* |

> **Pro Tip for Classroom PCs:** Always prefix `ng` commands with `npx ng ...` (e.g., `npx ng g c components/stats`) if a student forgot to install `@angular/cli` globally with `-g`. `npx` automatically uses the local Angular CLI inside `frontend/node_modules`!

---

## 2. Top 6 Classroom Errors Students Will Make (And How to Fix Them in 10 Seconds)

### Error 1: `"Can't bind to 'ngModel' since it isn't a known property of 'input'"`
- **Cause:** A student used `[(ngModel)]="searchTerm"` in their `.html` template, but forgot to import `FormsModule` in the `.ts` component.
- **10-Second Fix:** Open the `.component.ts` file and add `FormsModule` to the `imports: [...]` array inside `@Component`:
  ```typescript
  imports: [CommonModule, FormsModule, RouterLink]
  ```

### Error 2: `"Can't bind to 'formGroup' since it isn't a known property of 'form'"`
- **Cause:** The student used `[formGroup]="employeeForm"` in `.html`, but forgot to import `ReactiveFormsModule`.
- **10-Second Fix:** Add `ReactiveFormsModule` to `imports: [...]` in `employee-form.component.ts`.

### Error 3: `NG0955: The track expression resulted in duplicated keys` (or missing `track` in `@for`)
- **Cause:** In Angular 18, every `@for` loop requires a `track` expression so Angular knows the primary key of each row.
- **10-Second Fix:** Always write:
  ```html
  @for (emp of employees; track emp.id) {
    ...
  }
  ```

### Error 4: Browser Console Shows `Access to XMLHttpRequest at 'http://localhost:8080/api/employees' from origin 'http://localhost:4200' has been blocked by CORS policy`
- **Cause:** The student created a new Controller in Spring Boot without CORS enabled, or their Spring Boot server restarted on a different port.
- **10-Second Fix:** Point them to [`CorsConfig.java`](../backend/src/main/java/com/finances/ems/config/CorsConfig.java) or add `@CrossOrigin(origins = "http://localhost:4200")` above their `@RestController`.

### Error 5: Nothing Happens When Calling `this.http.get(...)` or `this.employeeService.deleteEmployee(id)`!
- **Cause:** This is the #1 beginner mistake in Angular! In Laravel (`Http::get()`) or Fetch, calling the method immediately fires the HTTP request. In Angular, `http.get()` returns a **lazy `Observable`** — it does **not** send the network request until you call **`.subscribe()`**!
- **10-Second Fix:** Make sure they added `.subscribe({ next: ... })`:
  ```typescript
  this.employeeService.deleteEmployee(emp.id).subscribe({
    next: () => this.loadEmployees()
  });
  ```

### Error 6: Clicking a `<a href="/employees/new">` Reloads the Entire Browser Page!
- **Cause:** Using standard HTML `href="..."` forces a full browser page reload (destroying SPA state).
- **10-Second Fix:** Replace `href="/employees/new"` with `routerLink="/employees/new"` (and ensure `RouterLink` is in `imports: [...]`).

---

## 3. Tough Questions Senior Engineers Might Ask You (With Ready-Made Answers)

### Q1: *"Why don't we just put the SQL query or business calculation inside Angular TypeScript?"*
- **Your Answer:**
  > *"Tout ce qui s'exécute dans Angular s'exécute dans le navigateur de l'utilisateur final : n'importe qui peut ouvrir la console F12 et voir ou modifier le code JavaScript. C'est pourquoi la sécurité, les calculs critiques (ex: paie, budget) et l'accès à PostgreSQL restent strictement enfermés dans **Spring Boot**. Angular ne sert qu'à l'affichage et à l'ergonomie."*

### Q2: *"In production at the Ministry of Finance, will users have to run `npm start` on port 4200?"*
- **Your Answer:**
  > *"Non ! Le port 4200 (`ng serve`) est uniquement un serveur de développement pour nous les développeurs. En production, on lance `npm run build` qui compile tout le code Angular en 3 petits fichiers statiques (`index.html`, `main.js`, `styles.css` dans le dossier `dist/`). Ces fichiers sont ensuite servis par un serveur web léger (Nginx / Apache) ou directement embarqués dans Spring Boot."*

### Q3: *"Why do we have `Employee` in TypeScript (`employee.model.ts`) AND `EmployeeResponseDto` in Java? Isn't that duplication?"*
- **Your Answer:**
  > *"Ce sont deux programmes distincts écrits dans deux langages différents (Java côté serveur, TypeScript côté navigateur). L'interface TypeScript `Employee` est le miroir du contrat JSON exposé par Swagger (`http://localhost:8080/swagger-ui.html`). Cela permet au compilateur TypeScript de détecter immédiatement toute faute de frappe avant même de lancer le navigateur."*
