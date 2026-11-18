# Day 5: Angular Under the Hood — Compiled to Plain JavaScript

**Title:** *Demystifying Angular: How Angular Compiles to Plain JavaScript (+ Hands-On JS Exercises)*  
**Target Audience:** Senior Engineers (Basic HTML, CSS & JavaScript knowledge; no TypeScript required)

> **Instructor Note (Speaker Notes Only):**
> - **Goal:** Strip away the "magic" of Angular. Students already know basic HTML/CSS/JS. By showing what Angular actually compiles into—plain, everyday JavaScript (`addEventListener`, `textContent`, `forEach`, `if/else`, `fetch`)—Angular becomes 100% transparent and understandable.
> - **Rule for Today:** All code examples and exercises use **plain JavaScript (ES6)**—no TypeScript types (`: string`), no interfaces, no decorators (`@Component`), and no RxJS operators.
> - **Hands-On Lab:** Direct students to open `slides/day 5/angular-js-exercises/index.html` in Google Chrome and work through Exercises 1–4.

---

### Slide 1: Day 5 — Angular Under the Hood (Compiled to Plain JavaScript)
- **Subtitle:** Seeing Through the Framework: What Chrome Actually Executes
- **Today's Agenda:**
  1. Why Chrome never runs TypeScript or Angular templates directly.
  2. Side-by-side: Angular concepts compiled into **plain JavaScript**.
  3. How `{{ }}`, `(click)`, `@if`, `@for`, and `HttpClient` work in real JS.
  4. **4 Hands-On Plain JavaScript Exercises** (running live in your browser!).
- **Talking Points (Speaker Notes):**
  - Good morning! Over the last few days we looked at Angular from the outside using Chrome DevTools.
  - Today we answer the final mystery: "What is actually inside `main.js` when Angular runs?"
  - Every single Angular feature compiles down to standard JavaScript that you already know.

---

### Slide 2: Chrome Only Understands 3 Languages
- **Inside Google Chrome:**
  - **HTML** $\rightarrow$ Structure of the page.
  - **CSS** $\rightarrow$ Colors, fonts, and layout.
  - **JavaScript** $\rightarrow$ Logic, clicks, loops, and network calls.
- **What Happens During `npm start` / `ng build`?**
  ```text
  [ Angular Source (.ts + .html) ]  --->  [ Angular Compiler ]  --->  [ Plain JavaScript (main.js) ]
     (Only for developers)                                              (Executed by Chrome!)
  ```
- **Key Takeaway:** Chrome **cannot** read TypeScript, `@Component`, `@for`, or `{{ name }}`. The build step converts everything into plain JavaScript!
- **Talking Points (Speaker Notes):**
  - Remind students of the `ng build` output they saw earlier: Chrome receives `index.html`, `styles.css`, and `main.js`.
  - There is no TypeScript inside the browser. Everything becomes plain JavaScript functions and DOM calls.

---

### Slide 3: Step 1 — Stripping TypeScript Into Plain JavaScript
- **What Developers Write (TypeScript):**
  - Adds safety labels (`: string`, `: number`) checked *before* running.
- **What Compiles Into Chrome (Plain JavaScript):**
  ```javascript
  // Compiled Plain JavaScript executed by Chrome:
  const employee = {
    id: 1,
    matricule: "FIN-2026-001",
    firstName: "Karim",
    lastName: "BENALI",
    salary: 145000,
    status: "ACTIVE"
  };

  function calculateBonus(salary) {
    return salary * 0.10;
  }
  ```
- **Notice:** Every `: string`, `: number`, and `interface` completely disappears! Only clean JavaScript objects and functions remain.
- **Talking Points (Speaker Notes):**
  - Reassure students: if you remove the `: string` and `: number` labels from TypeScript, what is left is literally standard JavaScript!

---

### Slide 4: What Is an Angular Component in Plain JavaScript?
- **In Angular:** A Component holds screen data (state) and button actions (methods).
- **Compiled to Plain JavaScript:**
  ```javascript
  // Plain JavaScript class representing an Angular Component:
  class EmployeeCard {
    constructor() {
      this.fullName = "Karim BENALI";
      this.salary = 145000;
      this.status = "ACTIVE";
    }

    giveRaise(amount) {
      this.salary = this.salary + amount;
      this.render(); // Redraws the HTML on screen
    }
  }
  ```
- **Plain English:** A Component is simply a JavaScript object that remembers its data and knows how to update its piece of HTML!
- **Talking Points (Speaker Notes):**
  - You don't need to worry about `@Component` decorators. Under the hood, it's just a plain JS class with variables (`this.salary`) and functions (`giveRaise()`).

---

### Slide 5: How `{{ interpolation }}` Compiles to JavaScript
- **In Angular HTML Template:**
  ```html
  <h2 id="emp-name">{{ employee.lastName }}</h2>
  <p id="emp-salary">{{ employee.salary }} DA</p>
  ```
- **Compiled Plain JavaScript Running in Chrome:**
  ```javascript
  // Angular generates DOM update instructions like this:
  const nameElement = document.getElementById("emp-name");
  const salaryElement = document.getElementById("emp-salary");

  function renderEmployee(employee) {
    nameElement.textContent = employee.lastName;
    salaryElement.textContent = employee.salary + " DA";
  }
  ```
- **Key Takeaway:** `{{ ... }}` is simply shorthand for setting `.textContent` on an HTML element!
- **Talking Points (Speaker Notes):**
  - Every student with basic JS knows `document.getElementById(...).textContent = ...`. That is literally all `{{ }}` compiles into!

---

### Slide 6: How `(click)` Events Compile to JavaScript
- **In Angular HTML Template:**
  ```html
  <button (click)="refreshList()">Actualiser</button>
  ```
- **Compiled Plain JavaScript Running in Chrome:**
  ```javascript
  const refreshBtn = document.getElementById("refresh-btn");

  refreshBtn.addEventListener("click", function() {
    refreshList();
  });

  function refreshList() {
    console.log("Fetching latest employees from server...");
  }
  ```
- **Key Takeaway:** `(click)="..."` compiles directly into standard browser `addEventListener("click", ...)`!
- **Talking Points (Speaker Notes):**
  - Ask the room: "Who has seen `addEventListener('click', ...)` in basic JavaScript?"
  - Angular simply writes the `addEventListener` wiring for you automatically so you don't have to write `getElementById` 50 times.

---

### Slide 7: How `@if` Conditionals Compile to JavaScript
- **In Angular HTML Template:**
  ```html
  @if (emp.status === 'ACTIVE') {
    <span class="badge-green">En Activité</span>
  } @else {
    <span class="badge-yellow">En Congé</span>
  }
  ```
- **Compiled Plain JavaScript Running in Chrome:**
  ```javascript
  function renderStatusBadge(status) {
    const badge = document.createElement("span");

    if (status === "ACTIVE") {
      badge.className = "badge-green";
      badge.textContent = "En Activité";
    } else {
      badge.className = "badge-yellow";
      badge.textContent = "En Congé";
    }
    return badge;
  }
  ```
- **Key Takeaway:** Angular `@if` / `@else` compiles directly into standard JavaScript `if (...) { } else { }` statements!
- **Talking Points (Speaker Notes):**
  - Nothing mysterious here—it is literally an `if / else` block choosing which CSS class and text to create.

---

### Slide 8: How `@for` Loops Compile to JavaScript
- **In Angular HTML Template:**
  ```html
  @for (emp of employees; track emp.id) {
    <tr><td>{{ emp.firstName }}</td><td>{{ emp.salary }}</td></tr>
  }
  ```
- **Compiled Plain JavaScript Running in Chrome:**
  ```javascript
  function renderTable(employees) {
    const tbody = document.getElementById("table-body");
    tbody.innerHTML = ""; // Clear old rows

    employees.forEach(function(emp) {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${emp.firstName} ${emp.lastName}</td>
        <td>${emp.salary} DA</td>
      `;
      tbody.appendChild(row);
    });
  }
  ```
- **Key Takeaway:** `@for` is simply a `.forEach()` loop creating `<tr>` elements and attaching them to `<tbody>`!
- **Talking Points (Speaker Notes):**
  - Show how `tbody.innerHTML = ""` clears the old table and `.forEach()` builds the rows one by one.

---

### Slide 9: What Is an Angular Service in Plain JavaScript?
- **In Angular:** Services (`@Injectable`) fetch data and share it across screens.
- **Compiled Plain JavaScript:**
  ```javascript
  // Just ONE shared JavaScript object (Singleton):
  const employeeService = {
    apiUrl: "http://localhost:8080/api/employees",

    filterByDepartment: function(employees, deptCode) {
      if (!deptCode) return employees;
      return employees.filter(function(emp) {
        return emp.departmentCode === deptCode;
      });
    }
  };
  ```
- **Key Takeaway:** An Angular Service is just a plain JavaScript object created **once** so multiple screens can reuse the same helper functions!
- **Talking Points (Speaker Notes):**
  - Demystify "Dependency Injection": instead of creating new helper objects on every page, the framework keeps one shared object (`employeeService`) in memory.

---

### Slide 10: How `HttpClient` Calls Spring Boot in Plain JavaScript (`fetch`)
- **In Angular:** `this.http.get(apiUrl).subscribe(...)`
- **Compiled Plain JavaScript (`fetch` API built into Chrome):**
  ```javascript
  function loadEmployeesFromServer() {
    fetch("http://localhost:8080/api/employees")
      .then(function(response) {
        return response.json(); // Parse JSON text into JS Array
      })
      .then(function(employees) {
        renderTable(employees); // Update HTML table when data arrives!
      })
      .catch(function(error) {
        console.error("Backend unreachable:", error);
      });
  }
  ```
- **Why `.then()`?** Because Chrome must **not freeze** while waiting for Spring Boot and PostgreSQL!
- **Talking Points (Speaker Notes):**
  - Point out the direct connection to Day 3: `fetch()` sends the exact HTTP request you saw in the DevTools Network tab!
  - `.then()` is the plain JavaScript callback that runs when the JSON comes back.

---

### Slide 11: How Forms & `POST` Validation Compile to JavaScript
- **Compiled Plain JavaScript for Form Submission:**
  ```javascript
  form.addEventListener("submit", function(event) {
    event.preventDefault(); // Stop browser from reloading the page!

    const email = document.getElementById("email").value;
    if (!email.includes("@")) {
      alert("Invalid email format!");
      return; // Client validation stops here
    }

    const newEmployee = { firstName: "Karim", email: email, salary: 120000 };

    fetch("http://localhost:8080/api/employees", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newEmployee)
    }).then(res => console.log("Created status:", res.status));
  });
  ```
- **Talking Points (Speaker Notes):**
  - Notice `event.preventDefault()`: in standard HTML, submitting a form reloads the page. Single-Page Applications stop the reload and send JSON with `fetch()` instead!

---

### Slide 12: Why Do Developers Use Angular Instead of Writing Raw JS?
- **Writing Raw JavaScript Manually:**
  - Lots of `document.getElementById(...)` and `createElement("tr")`.
  - Easy to forget `tbody.innerHTML = ""` or `event.preventDefault()`.
  - Gets messy when an app has 50 screens!
- **Using Angular:**
  - Developers write clean templates (`@for`, `@if`, `{{ emp.name }}`).
  - Angular's compiler **automatically generates** all the tedious JavaScript DOM code!
- **Your Superpower Now:** When you open DevTools (`F12`), you know **both** the high-level Angular concept and the plain JavaScript running underneath!
- **Talking Points (Speaker Notes):**
  - Summarize why frameworks exist: not to do magic, but to automate repetitive `getElementById` and `createElement` boilerplate safely.

---

### Slide 13: Hands-On Practice — 4 Plain JavaScript Exercises
- **Open in Browser:** `slides/day 5/angular-js-exercises/index.html`
- **Your 4 Progressive Exercises (100% Plain JavaScript — No TypeScript!):**
  1. **Exercise 1 (`01-component-and-interpolation.html`):** Wire up `textContent` and `addEventListener` (how `{{ }}` and `(click)` work).
  2. **Exercise 2 (`02-loops-and-conditionals.html`):** Render an Employee Table using `.forEach()`, `if/else` status badges, and `.filter()`.
  3. **Exercise 3 (`03-forms-and-validation.html`):** Build client-side form validation (`preventDefault()`, email check, red error messages).
  4. **Exercise 4 (`04-calling-spring-boot-api.html`):** Use `fetch()` to call the live Spring Boot API (`GET` & `POST`) and display real database records!
- **Talking Points (Speaker Notes):**
  - Walk students to `slides/day 5/angular-js-exercises/index.html`.
  - Remind them there is a `js-reference.html` cheat sheet and full working solutions in `solutions/` if they get stuck.

---

### Slide 14: Day 5 Wrap-Up — You Now See the Full Picture
- **From Browser Pixels to PostgreSQL Disk:**
  1. **UI & Components** $\rightarrow$ Plain JS updating HTML DOM (`textContent`, `forEach`, `if/else`).
  2. **Events & Forms** $\rightarrow$ Plain JS listeners (`addEventListener`, `preventDefault()`).
  3. **Network Wire** $\rightarrow$ Asynchronous `fetch()` sending JSON over HTTP (`GET`, `POST`, `PUT`, `DELETE`).
  4. **Backend API** $\rightarrow$ Spring Boot validating & translating via ORM to **PostgreSQL**.
- **Congratulations on completing the 5-Day Modernization Workshop!**
- **Talking Points (Speaker Notes):**
  - Wrap up the week! Students now understand the entire stack without mystery.
