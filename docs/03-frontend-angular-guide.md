# Guide 03 : Volet Frontend — Angular 18 Teaching Script & Walkthrough (Days 3, 4 & 5)
*(Written specifically for You — bridging your **Laravel** background to **Angular 18** so you can teach the 7 engineers with complete confidence)*

---

## 1. How to Open Day 3 Morning (Your First 30 Minutes)

When you take the stage on Day 3 Morning, your coworker will have just finished building and testing the Spring Boot REST API on Days 1 & 2.

### Your Opening Pitch to the 7 Engineers:
1. Open `http://localhost:8080/api/employees` (or Swagger UI) in the browser and show the raw JSON.
2. Say (in French/Arabic):
   > *"Durant les Jours 1 et 2, nous avons construit un moteur sécurisé avec Spring Boot et PostgreSQL. Mais un gestionnaire RH du Ministère ne peut pas travailler en lisant du texte JSON brut ! Aujourd'hui et demain, nous allons construire l'application cliente avec **Angular** qui tourne dans le navigateur (port 4200), consomme ce JSON, et offre une interface dynamique sans jamais recharger la page."*
3. Explain **Single Page Application (SPA)**:
   - Point to [`frontend/src/index.html`](../frontend/src/index.html). Show that the entire HTML file is only 14 lines long and contains a single custom tag: `<app-root></app-root>`.
   - **Laravel Analogy for You:** In Laravel Blade, every route returns a new HTML document from the server. In Angular, the browser downloads `index.html` **once**, and Angular swaps components inside `<router-outlet>` in memory!

---

## 2. Step-by-Step Code Walkthrough (In the Exact Order You Should Teach It)

### Module 1: TypeScript Interfaces (`src/app/models/`) — Day 3 Morning (45 mins)
Open [`department.model.ts`](../frontend/src/app/models/department.model.ts) and [`employee.model.ts`](../frontend/src/app/models/employee.model.ts).

- **Why Legacy Engineers Love TypeScript:**
  - Plain JavaScript lets you make typos like `emp.fisrtName` without warning you until runtime.
  - **TypeScript** adds strict types (just like Java on the backend!).
  - Show how `export interface Employee` mirrors the exact fields of the Java `EmployeeResponseDto` record. If a developer types `emp.salaire` instead of `emp.salary`, VS Code underlines it in red immediately!

---

### Module 2: Anatomy of an Angular Component (`navbar` & `app.component`) — Day 3 Morning (60 mins)
Open [`app.component.ts`](../frontend/src/app/app.component.ts) and [`navbar.component.ts`](../frontend/src/app/components/navbar/navbar.component.ts).

Explain the `@Component({...})` decorator using 4 simple properties:
1. **`selector: 'app-navbar'`**: The custom HTML tag name you can use in other templates (`<app-navbar></app-navbar>`). *(Laravel equivalent: `<x-navbar />` Blade component)*.
2. **`standalone: true`**: Modern Angular 18 standard. Each component is self-contained (no old `app.module.ts` file needed!).
3. **`imports: [RouterLink, RouterLinkActive]`**: Lists the other components or directives used inside this component's HTML.
4. **`template` / `templateUrl` & `styles` / `styleUrl`**: The HTML view and scoped CSS (CSS written in a component **only** affects that component — it never leaks to other screens!).

---

### Module 3: Services, Dependency Injection & `HttpClient` — Day 3 Afternoon (90 mins)
Open [`department.service.ts`](../frontend/src/app/services/department.service.ts) and [`employee.service.ts`](../frontend/src/app/services/employee.service.ts).

- **Laravel Mental Model for You:**
  - In Laravel, you don't put raw database or `Http::get()` calls inside a Blade view; you inject a Service into your Controller.
  - In Angular, Components only handle **screen state**, while `@Injectable({ providedIn: 'root' })` Services handle **REST API calls (`HttpClient`)**.
- **How to Explain `Observable<Employee[]>` to Older Engineers (Keep it Simple!):**
  - Show `getEmployees()` in [`employee.service.ts`](../frontend/src/app/services/employee.service.ts):
    ```typescript
    return this.http.get<Employee[]>(this.apiUrl, { params });
    ```
  - Tell the students:
    > *"En Java ou en procédural, un appel réseau bloque l'exécution jusqu'à la réponse. Dans un navigateur web, si on bloquait l'exécution en attendant PostgreSQL, la souris et l'écran se figeraient ! C'est pourquoi `http.get()` renvoie un objet `Observable` : une promesse que les données JSON vont arriver. Dans le composant, il suffit d'appeler `.subscribe()` pour dire : quand le JSON arrive, mets à jour le tableau."*

---

### Module 4: Displaying Data, Loops & Filters (`EmployeeListComponent`) — Day 3 Afternoon / Day 4 Morning
Open [`employee-list.component.ts`](../frontend/src/app/components/employee-list/employee-list.component.ts) and [`employee-list.component.html`](../frontend/src/app/components/employee-list/employee-list.component.html).

Walk the students through the **4 core template syntaxes** right inside `employee-list.component.html`:
1. **Interpolation `{{ ... }}`**:
   - `{{ emp.lastName }} {{ emp.firstName }}` (just like Blade `{{ $emp->lastName }}`).
   - Show the **Pipe `|`** for formatting numbers: `{{ emp.salary | number:'1.2-2' }} DA`.
2. **Modern Control Flow `@if` and `@for`**:
   - Show lines 2–10 (`@if (employeeService.isOfflineFallbackMode) { ... }`).
   - Show the table loop:
     ```html
     @for (emp of employees; track emp.id) {
       <tr>...</tr>
     }
     ```
   - Explain `track emp.id`: *"Cela indique à Angular quelle est la clé primaire (ID) de chaque ligne pour ne redessiner que la ligne modifiée au lieu de recréer tout le tableau HTML."*
3. **Property Binding `[property]="expression"`** (TypeScript $\rightarrow$ HTML):
   - `[class]="getStatusBadgeClass(emp.status)"`
   - `[routerLink]="['/employees', emp.id, 'edit']"`
4. **Event Binding `(event)="method()"`** (HTML $\rightarrow$ TypeScript):
   - `(click)="confirmDelete(emp)"`
5. **Two-Way Binding `[(ngModel)]="property"`** on the search/filter bar:
   - `[(ngModel)]="searchTerm"` keeps the HTML `<input>` and the TypeScript variable `this.searchTerm` synchronized in real time!

---

### Module 5: Routing & Reusable Reactive Forms (`EmployeeFormComponent`) — Day 4 Afternoon
Open [`app.routes.ts`](../frontend/src/app/app.routes.ts), [`employee-form.component.ts`](../frontend/src/app/components/employee-form/employee-form.component.ts), and [`employee-form.component.html`](../frontend/src/app/components/employee-form/employee-form.component.html).

- **Why Reactive Forms (`FormBuilder` / `FormGroup`) instead of Template-Driven Forms?**
  - For a simple search box, `[(ngModel)]` is great.
  - For a real administrative form with 10 fields and validation rules (required, email, positive salary), **Reactive Forms** define all rules cleanly in TypeScript — just like **Laravel's `FormRequest` `rules()` array**!
- **Highlight the Create vs. Edit Reuse Pattern:**
  - Point to `ngOnInit()` in [`employee-form.component.ts`](../frontend/src/app/components/employee-form/employee-form.component.ts):
    ```typescript
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEditMode = true;
      this.employeeId = Number(idParam);
      this.loadEmployeeForEdit(this.employeeId);
    }
    ```
  - If the URL is `/employees/new`, `idParam` is `null` $\rightarrow$ empty form, submits via `POST /api/employees`.
  - If the URL is `/employees/3/edit`, `idParam` is `"3"` $\rightarrow$ calls `GET /api/employees/3`, fills the form using `this.employeeForm.patchValue(...)`, and submits via `PUT /api/employees/3`!

---

### Module 6: Day 5 Security Workshop (`LoginDemoComponent` & `authInterceptor`)
Open [`login-demo.component.ts`](../frontend/src/app/components/login-demo/login-demo.component.ts), [`auth.service.ts`](../frontend/src/app/services/auth.service.ts), and [`auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts).

- **How to Demonstrate This Live on Day 5 Morning:**
  1. In the browser, click **Atelier Sécurité (Jour 5)** in the navbar.
  2. Open Chrome DevTools (**F12 $\rightarrow$ Network / Réseau**).
  3. Click **Obtenir un Jeton JWT** (with `admin` / `finances2026`).
  4. Show the token displayed on screen and stored in `sessionStorage`.
  5. Click **Tester un appel API avec ce jeton**, then click the `employees` request in the Network tab of Chrome DevTools.
  6. Show the students the **Request Headers**:
     `Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`
  7. Show [`auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts) (only 20 lines of code!) and explain:
     > *"Au lieu d'ajouter manuellement le jeton dans chacun de nos 50 appels `http.get` ou `http.post`, l'intercepteur Angular agit comme un guichet automatique qui attache l'en-tête `Authorization: Bearer` à toutes les requêtes sortantes."*
