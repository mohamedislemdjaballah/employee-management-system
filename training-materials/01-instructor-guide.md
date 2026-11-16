# Deliverable 01: Complete Instructor Guide
## Workshop: Working with Modern Web Applications — Angular Frontend & Spring Boot Backend
**Instructor Profile:** Backend Developer (Laravel / PHP background) teaching modern decoupled web application architecture to non-programmer senior engineers.  
**Language:** English  
**Course Philosophy:** 20% Theory, 20% Demo, 60% Hands-on. No programming in Java, TypeScript, or RxJS.

---

# Morning Block: Architecture, Exploration & Business Operations (08:00 – 12:00)

---

## Session 1: Welcome, Workshop Objectives & Orientation (08:00 – 08:20)

### A. Session Information
- **Duration:** 20 Minutes (08:00 – 08:20)
- **Learning Objectives:**
  - Establish a comfortable, non-intimidating learning environment.
  - Clarify the role of the participants: technical operators and investigative auditors, NOT software developers.
  - Confirm all 7 participant workstations have Google Chrome running with the Employee Management System open.
- **Required Materials:**
  - Projector connected to Instructor Laptop.
  - Google Chrome open to `http://localhost:4200`.
  - Printed or digital Participant Workbook (`09-participant-workbook.md`).
- **Expected Outcome:** Participants understand what they will achieve today and feel confident that no coding knowledge is required.

### B. Instructor Speaking Script
- **Opening Sentence:**
  > *"Good morning everyone, and welcome to Day 3 of our web modernization workshop. Today is dedicated entirely to understanding, operating, and investigating modern decoupled web applications."*
- **Simple Explanation & Ground Rules:**
  > *"Before we begin, I want to make one promise: you will not write a single line of Java or TypeScript code today. You are not here to become frontend developers. In your daily work at the Ministry of Finance, your responsibility is to understand how these systems work, operate them efficiently, diagnose problems when they occur, and communicate clearly with development teams."*
- **The Analogy:**
  > *"Think of this like learning to drive and inspect an automobile. You do not need to know how to weld the engine block to be a skilled driver, and you do not need to be a mechanical engineer to check the oil dipstick or explain to the mechanic that the front-left brake is making a squealing sound. Today, you are learning how to inspect the dashboard and look under the hood of a web application."*
- **Transition to Next Topic:**
  > *"To inspect any system properly, we must first understand how its major parts fit together. Let's look at the architecture of our application."*
- **Questions to Ask Participants:**
  - *"When you use an internal portal at work today, what is the most frustrating error message you typically see?"*
  - *(Listen for answers like: 'Screen went blank', 'Server error 500', 'Button didn't do anything').*

### C. Demonstration Instructions
- **What to Open:** Instructor laptop screen on projector showing the Google Chrome browser at `http://localhost:4200`.
- **What to Click:** Click on the **Annuaire des Agents** navigation link.
- **What to Show:** Point out the title: *"Portail de Gestion des Agents — Ministère des Finances / SIT"*.
- **What Participants Should Observe:** The interface is clean, loaded instantly, and shows summary KPI cards at the top.
- **Questions to Ask:**
  - *"Does this look like a typical enterprise intranet application?"*

---

## Session 2: Understanding Decoupled Web Architecture (08:20 – 08:45)

### A. Session Information
- **Duration:** 25 Minutes (08:20 – 08:45)
- **Learning Objectives:**
  - Understand the concept of "Decoupled Architecture" (Frontend vs Backend vs Database).
  - Understand why modern web systems run on two separate ports (`:4200` and `:8080`).
  - Learn the "Restaurant Analogy" to explain separation of concerns.
- **Required Materials:** Slide Deck (`03-presentation-slides.md`), Slide 3 ("Client-Server Architecture").
- **Expected Outcome:** Participants can explain why Angular never touches PostgreSQL directly.

### B. Instructor Speaking Script
- **Opening Sentence:**
  > *"In older systems—like Oracle Forms or traditional web portals from 15 years ago—everything lived in one giant box. Today, modern applications are 'decoupled'."*
- **Simple Explanation:**
  > *"Decoupled means we split the application into two independent programs running in different places. On port 4200, we have Angular, which runs inside your Chrome browser. On port 8080, we have Spring Boot, which runs on the server. And on port 5432, we have PostgreSQL, which stores the database records."*
- **The Analogy (The Restaurant Model):**
  > *"Imagine a restaurant:
  > 1. The **Database (PostgreSQL)** is the locked pantry where ingredients are stored safely.
  > 2. The **Backend (Spring Boot)** is the kitchen. The chef has the keys to the pantry, knows the recipes, checks food safety, and cooks the meals.
  > 3. The **Frontend (Angular)** is the dining room and the waiter. The waiter presents the menu to the customer, takes the customer's order, brings it to the kitchen hatch, and brings the cooked food back to the table.
  > 
  > Notice the crucial rule: **The customer in the dining room is never allowed to walk into the kitchen or the pantry.** They only communicate through the waiter using clean order slips. In our application, those order slips are called **REST API requests**."*
- **Laravel Note for You (Instructor Context):**
  > *In traditional Laravel, Blade views are rendered by PHP on the server and sent as full HTML strings. In this decoupled stack, Spring Boot acts like an `api.php` route that only returns JSON, while Angular is an independent client application running in the user's browser.*
- **Transition:**
  > *"Now that we know the dining room is separate from the kitchen, let's explore how the dining room—the Angular interface—is built."*
- **Questions to Ask Participants:**
  - *"If a user's computer loses internet connection for 5 seconds, which part of the system is affected: the dining room in their browser, or the kitchen on the server?"*

### C. Demonstration Instructions
- **What to Open:** Show two terminal windows side-by-side:
  - Terminal 1: Shows Spring Boot running on port `8080`.
  - Terminal 2: Shows Angular running on port `4200`.
- **What to Show:** Point out that shutting down Terminal 1 (the backend) does not make the web page disappear from Chrome; the buttons still exist, but they can no longer reach the database!
- **What Participants Should Observe:** The visual UI exists in the browser independently of the server.

---

## Session 3: Angular Interface Tour — The Component Model (08:45 – 09:15)

### A. Session Information
- **Duration:** 30 Minutes (08:45 – 09:15)
- **Learning Objectives:**
  - Identify the primary visual blocks of an Angular interface: Header/Navbar, Summary Cards, Data Tables, and Action Buttons.
  - Understand the concept of a "Component": reusable visual Lego blocks.
- **Required Materials:** Browser open to `http://localhost:4200`.
- **Expected Outcome:** Participants can visually decompose a web page into its distinct components.

### B. Instructor Speaking Script
- **Opening Sentence:**
  > *"When you look at this screen, your eye sees one big page. But Angular sees a collection of interlocking Lego blocks called **Components**."*
- **Simple Explanation:**
  > *"Look at the top dark-blue bar: that is the **Navbar Component**. It stays at the top of your screen no matter where you navigate.
  > Below it, look at the four colored boxes: those are **KPI Summary Cards**. One shows total employees, one shows active staff, one shows staff on leave, and one calculates the monthly payroll.
  > Below that, look at the white box with search fields: that is the **Filter Component**.
  > And finally, the large table listing our staff: that is the **Employee List Component**."*
- **Practical Example:**
  > *"Why do developers build screens this way? Because if the Minister asks for the payroll summary card to appear on five different department pages, developers do not have to rebuild it five times. They simply reuse the exact same Lego block."*
- **Transition:**
  > *"Enough listening to me talk. It is time for you to sit in the driver's seat. Let's begin Practical Mission 1."*
- **Questions to Ask:**
  - *"If the Ministry changes its official logo in the top bar, how many files should the development team need to update?"* *(Answer: Just one, the Navbar Component!)*

---

## Session 4: Practical Mission 1 — Exploring the Application (09:15 – 10:00)

### A. Mission Information
- **Duration:** 45 Minutes (09:15 – 10:00)
- **Format:** Hands-on individual or paired activity.
- **Workbook Reference:** `training-materials/06-practical-missions.md` $\rightarrow$ Mission 1.
- **Objective:** Navigate the live EMS application, verify data display, test multi-criteria filters, and observe dynamic KPI calculations.

### B. Instructions for Participants
1. Ensure your browser is displaying `http://localhost:4200`.
2. Look at the top blue banner. What is the text badge on the left? (Expected: `MF / SIT`).
3. Count how many employee records are displayed in the initial table. (Expected: 6 employees).
4. Locate the **Direction / Structure** dropdown. Select `SIT — Système d'Information du Trésor`.
   - How many employees are displayed now? (Expected: 3 employees: Karim BENALI, Amina MANSOURI, Mourad HADJADJ).
   - What happened to the **Effectif Affiché** summary card at the top? (Expected: It dynamically changed from 6 to 3).
5. In the **Recherche rapide** field, type `Karim` and press Enter.
   - Who remains visible in the table?
6. Click the **Réinitialiser** button. Verify that all 6 employees return.

### C. Instructor Facilitation & Observations
- **What to Observe:** Walk around the room. Ensure participants are interacting with the actual dropdowns and inputs rather than just reading the screen.
- **Common Mistake:** Some participants might type in the search box but forget to clear it before selecting a different department dropdown, resulting in an empty table.
- **Coaching Hint:**
  > *"If your table ever shows 'Aucun agent ne correspond', simply click the white 'Réinitialiser' button to clear all search criteria."*

### D. Mission Recap & Debrief (09:55 – 10:00)
- **Key Takeaways:**
  1. The interface reacts instantly without the screen flickering or doing a white-page reload.
  2. The KPI cards are dynamically tied to the data displayed below them.
- **Knowledge-Check Question:**
  - *"When you filtered by department, did your browser download a brand-new web page from the server?"*
  - **Answer for Instructor:** No! The browser simply asked for filtered data, and Angular updated the table rows in memory.

---

### *Morning Break: 10:00 – 10:15 (15 Minutes)*

---

## Session 5: Navigation, Client-Side Routing & Forms (10:15 – 10:40)

### A. Session Information
- **Duration:** 25 Minutes (10:15 – 10:40)
- **Learning Objectives:**
  - Understand **Client-Side Routing**: how URLs change (`/employees` vs `/employees/new`) without contacting a web server for new HTML.
  - Understand the anatomy of a Web Form: labels, input controls, dropdowns, and submission buttons.
- **Required Materials:** Chrome browser displaying EMS.
- **Expected Outcome:** Participants understand that navigation in modern web applications happens in browser memory.

### B. Instructor Speaking Script
- **Opening Sentence:**
  > *"Welcome back. In a traditional website, whenever you click a link like 'Add Employee', the browser screen goes blank for a second while it downloads a new file called `add_employee.html` from a server. Watch what happens in our modern application."*
- **Demonstration Walkthrough:**
  > *"Watch my mouse. I am currently on the URL `/employees`. Now I click the blue button **'+ Ajouter un Agent'**.
  > Look at the address bar: the URL changed instantly to `/employees/new`.
  > But look at your browser's reload icon (the circular arrow in the top left): **it never spun!**
  > This is called **Client-Side Routing**. The Angular router caught my click, kept the Navbar in place, and swapped the table for a form in less than 5 milliseconds."*
- **Form Anatomy Explanation:**
  > *"Look at this form:
  > - Each field has a **Label** explaining what data is expected.
  > - Required fields have a red asterisk (`*`).
  > - The Direction field is a **Dropdown Menu** populated with official Ministry departments.
  > - The submit button at the bottom says **'Créer l'agent'**."*
- **Transition:**
  > *"Now it is your turn to create real records and modify existing data. Let's start Practical Mission 2."*

---

## Session 6: Practical Mission 2 — Performing Business Operations (10:40 – 11:30)

### A. Mission Information
- **Duration:** 50 Minutes (10:40 – 11:30)
- **Format:** Hands-on individual business data entry.
- **Workbook Reference:** `training-materials/06-practical-missions.md` $\rightarrow$ Mission 2.
- **Objective:** Successfully perform standard business operations: Create an employee, verify their appearance in the list, edit an existing record, and observe administrative status changes.

### B. Instructions for Participants
1. From the Employee Directory, click the blue button **'+ Ajouter un Agent'**.
2. Enter the following test employee:
   - **Matricule:** `FIN-2026-008`
   - **Direction:** `DRH — Direction des Ressources Humaines`
   - **Nom de Famille:** `CHIKHI`
   - **Prénom:** `Sofiane`
   - **Email:** `s.chikhi@finances.gov`
   - **Téléphone:** `0558 11 22 33`
   - **Poste / Grade:** `Auditeur Financier`
   - **Salaire Mensuel:** `120000`
   - **Date de Recrutement:** Select today's date.
   - **Statut:** `ACTIVE`
3. Click the blue button **"Créer l'agent"**.
4. Observe: You are automatically redirected back to the Directory.
5. In the quick search box, type `CHIKHI`. Verify that Sofiane CHIKHI is now visible in the list with ID and department badge!
6. Click the white button **"Modifier"** next to Sofiane CHIKHI.
   - Change his salary from `120000` to `135000`.
   - Change his status from `ACTIVE` to `ON_LEAVE` (En Congé).
   - Click **"Enregistrer les modifications"**.
7. Verify that his badge in the table changed to yellow (`En Congé`) and the top KPI card updated!

### C. Instructor Facilitation & Observations
- **What to Observe:** Verify that participants see the newly created employee in the table.
- **Common Trap:** If a student tries to create another employee with the exact same matricule (`FIN-2026-008`), the system will display a red error message. Tell them: *"Keep that in mind! That leads directly into our next topic on validation!"*

---

## Session 7: Validation & Application Error Behavior (11:30 – 12:00)

### A. Session Information
- **Duration:** 30 Minutes (11:30 – 12:00)
- **Learning Objectives:**
  - Clearly distinguish between **Frontend Validation** (instant user guidance) and **Backend Validation** (authoritative database security).
  - Understand HTTP Status `400 Bad Request` and `409 Conflict`.
- **Required Materials:** Slide Deck (`03-presentation-slides.md`), Slide 11 ("Frontend vs Backend Validation").
- **Expected Outcome:** Participants know why client-side validation alone is never trusted for security.

### B. Instructor Speaking Script
- **Opening Sentence:**
  > *"Have you ever submitted a web form that immediately highlighted an empty box in red before you even clicked submit? That is called **Frontend Validation**."*
- **Simple Explanation:**
  > *"When you type into the Email field, Angular checks the format as you type. If you leave it blank or forget the `@` symbol, Angular turns the border red and displays: 'Veuillez saisir une adresse email valide'.
  > Why? To save you time! It prevents you from waiting for a server message when you made an obvious typo."*
- **The Critical Security Rule (Frontend vs Backend):**
  > *"Now, here is a crucial question: **Can we rely on frontend validation for security?**
  > The answer is: **ABSOLUTELY NOT.**
  > A malicious user or an automated script can bypass the browser completely and send raw data directly to the server.
  > Therefore, the backend (Spring Boot) has its own strict validation rules:
  > - If you send bad data $\rightarrow$ Spring Boot returns **HTTP 400 Bad Request**.
  > - If you try to create an employee with a matricule that already exists $\rightarrow$ Spring Boot returns **HTTP 409 Conflict**."*
- **Laravel Note for You:**
  > *This is identical to client-side HTML5 validation vs a Laravel `FormRequest` class. The browser gives visual feedback, but the backend is the authoritative gatekeeper.*
- **Session Recap & Quiz 1 Administration:**
  - Distribute or display Quiz 1 (`training-materials/07-quizzes-and-answers.md`).
  - Give participants 5 minutes to answer, then review answers together before lunch.

---

### *Lunch Break: 12:00 – 13:00 (60 Minutes)*

---

# Afternoon Block: Wire Investigation & Bug Reporting (13:00 – 15:00)

---

## Session 8: The Wire — REST API Communication & Spring Boot (13:00 – 13:25)

### A. Session Information
- **Duration:** 25 Minutes (13:00 – 13:25)
- **Learning Objectives:**
  - Understand the concept of the "Network Wire" connecting Angular to Spring Boot.
  - Master the 4 core HTTP verbs: `GET` (Fetch), `POST` (Create), `PUT` (Update), `DELETE` (Remove).
  - Understand JSON as the structured message format traveling over the wire.
- **Required Materials:** Slide Deck, Slides 6, 7 & 8; Browser open to Swagger UI (`http://localhost:8080/swagger-ui.html`).

### B. Instructor Speaking Script
- **Opening Sentence:**
  > *"Welcome back. This morning, we interacted with the visual interface. This afternoon, we are going to look at the invisible wire that connects Angular to Spring Boot."*
- **Simple Explanation:**
  > *"Every time you click a button in Angular, a standardized envelope travels across your local network to port 8080. This envelope uses the **HTTP protocol**, the same protocol that powers the entire World Wide Web.
  > On the front of the envelope is an **Action Verb**:
  > - `GET` means: 'Please give me data' (Read-only, completely safe).
  > - `POST` means: 'Please create a brand-new record'.
  > - `PUT` means: 'Please update an existing record'.
  > - `DELETE` means: 'Please delete this record'.
  > 
  > And inside the envelope is a clean, text-based letter written in **JSON (JavaScript Object Notation)**."*
- **Demonstration:**
  > *"Look at this screen: this is **Swagger UI**. It shows the raw API contracts of our Spring Boot backend.
  > Notice that Swagger doesn't know about Angular. When I execute `GET /api/employees`, look at what comes back: pure JSON.
  > Angular takes this exact JSON list and draws the table you saw this morning."*
- **Transition:**
  > *"Now, how do we see these envelopes flying back and forth while using our application? We use the most powerful tool built into Google Chrome: **Developer Tools**."*

---

## Session 9: Masterclass — Chrome Developer Tools (13:25 – 14:00)

### A. Session Information
- **Duration:** 35 Minutes (13:25 – 14:00)
- **Learning Objectives:**
  - Open and dock Chrome Developer Tools (`F12` or `Ctrl + Shift + I`).
  - Master the **Network Tab**: enabling "Preserve log", filtering by "Fetch/XHR", and finding requests.
  - Inspect the 5 Essential Technical Clues: Request URL, HTTP Method, HTTP Status Code, Request Payload, and Response Body.
  - Master the **Console Tab**: recognizing red JavaScript errors.
- **Required Materials:** Reference Guide (`05-chrome-devtools-guide.md`).

### B. Instructor Speaking Script & Live Demonstration
- **Demonstration Step 1 (Opening DevTools):**
  > *"Everyone, please open Google Chrome to our application at `http://localhost:4200`.
  > Now press the **F12** key on your keyboard (or right-click anywhere on the page and select 'Inspect').
  > A panel will appear. If it is floating, click the three vertical dots in its top right and choose the dock icon to snap it to the bottom or right side of your window."*
- **Demonstration Step 2 (The Console Tab):**
  > *"Click on the **Console** tab.
  > The Console is the browser's black box flight recorder.
  > If a frontend programmer made a typo in their JavaScript, you will see bright red error text here with a description of what crashed.
  > As an auditor or technical lead, whenever an application screen freezes or a button stops responding, **your very first action must always be to look at the Console**."*
- **Demonstration Step 3 (The Network Tab):**
  > *"Now click on the **Network** tab.
  > This is where the magic happens. The Network tab shows every single envelope traveling between Angular and Spring Boot.
  > Please do two critical setup steps:
  > 1. Check the box labeled **'Preserve log'**. This prevents the log from clearing if the page navigates.
  > 2. Click the filter button labeled **'Fetch/XHR'**. This filters out images and fonts so you only see raw API data calls."*
- **Demonstration Step 4 (Watching an API Request Live):**
  > *"Watch my screen. With DevTools open, I click the white button **'↻ Actualiser'**.
  > Look at the table in DevTools: a new line appeared named `employees`.
  > Let's click on it. On the right, we have tabs:
  > - Under **Headers**: Request URL is `http://localhost:8080/api/employees`, Method is `GET`, Status is `200 OK`.
  > - Under **Response**: Look at the raw JSON list returned by Spring Boot!
  > You are now looking directly at the wire!"*
- **The Golden Warning:**
  > *"One essential rule of technical investigation: **Never include passwords, access tokens, or sensitive citizen data in unredacted screenshots!** Always verify that sensitive credentials are masked before attaching technical evidence to a bug ticket."*

---

## Session 10: Practical Missions 3 & 4 — Troubleshooting & Evidence Gathering (14:00 – 14:35)

### A. Mission Information
- **Duration:** 35 Minutes (14:00 – 14:35)
- **Format:** Investigative hands-on lab.
- **Workbook Reference:** `training-materials/06-practical-missions.md` $\rightarrow$ Missions 3 & 4.
- **Objective:** Collect concrete technical evidence for 4 distinct failure scenarios using Chrome DevTools.

### B. Instructions for Participants
Work through the 4 diagnostic scenarios in your workbook:
1. **Scenario 1 (Valid Creation):** Create a new employee while watching the Network tab. Capture the exact HTTP method, endpoint, and status code (Expected: `POST`, `/api/employees`, `201 Created`).
2. **Scenario 2 (Invalid Data):** Try to edit an employee and clear the salary to negative or enter an invalid email. In DevTools, find the red request and inspect the Response tab (Expected: `400 Bad Request` with `fieldErrors`).
3. **Scenario 3 (Duplicate Conflict):** Attempt to create an agent using an existing matricule (`FIN-2026-001`). What HTTP status code is returned? (Expected: `409 Conflict`).
4. **Scenario 4 (Security Header):** Navigate to **Atelier Sécurité (Jour 5)**, log in with `admin` / `finances2026`, and inspect the `GET /api/employees` request. What header was attached? (Expected: `Authorization: Bearer <token>`).

### C. Instructor Facilitation
- Walk the room and look over participants' shoulders. Ensure their DevTools are docked, "Fetch/XHR" is selected, and they are clicking on the specific request row to view the Headers and Response tabs.

---

## Session 11: Structured Bug Reporting, Final Assessment & Wrap-Up (14:35 – 15:00)

### A. Session Information
- **Duration:** 25 Minutes (14:35 – 15:00)
- **Learning Objectives:**
  - Translate raw technical evidence into a clean, reproducible, professional Bug Report.
  - Complete the Final Assessment (`08-final-assessment.md`).
  - Summarize key takeaways from Day 3.

### B. Instructor Speaking Script
- **Opening Sentence:**
  > *"Have you ever received an email from a user that said: 'The application doesn't work, please fix it'?
  > How helpful was that email? Completely useless! It tells the development team nothing about what broke, who was logged in, or what error occurred."*
- **The Anatomy of a Professional Bug Report:**
  > *"A professional bug report consists of four pillars:
  > 1. **Clear Title & Context:** Which page, which user, what time.
  > 2. **Step-by-Step Reproduction Steps:** Exactly what buttons to click so a developer can see the problem in 30 seconds.
  > 3. **Expected vs Actual Behavior:** What should have happened vs what actually happened on screen.
  > 4. **Concrete Technical Evidence:** The HTTP Method, the Endpoint URL, the HTTP Status Code, and the JSON error message from DevTools.
  > 
  > When you provide this evidence, developers do not argue with you. You have proven the issue with photographic technical accuracy."*
- **Final Assessment Administration (14:45 – 14:55):**
  - Direct students to Section 8 of their workbook: **Final Practical Assessment**.
  - Have them write a structured bug report for one of the three scenarios (Validation 400, Authorization 403, or Server 500).
- **Closing Synthesis & Congratulations (14:55 – 15:00):**
  - Review the 5 core status codes (`200 OK`, `201 Created`, `400 Bad Request`, `403 Forbidden`, `500 Server Error`).
  - Congratulate the participants on mastering the operational inspection of modern decoupled applications!
