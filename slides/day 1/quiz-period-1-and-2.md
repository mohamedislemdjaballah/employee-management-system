# Day 1 — Periods 1 & 2 Quiz (Votense Ready)

> **Coverage:** Period 1 (Web History & Evolution) & Period 2 (Web Apps Architectures, REST APIs & OpenAPI)  
> **Votense Types Used:** `Multiple choice` · `Multi-select` · `True or false`

---

## 1. Multiple choice *(One right answer)*

**1. In the Client–Server model, what is the "Client"?**
- A) The database storing user passwords
- B) The cloud data center hosting the website
- C) **Your web browser (Chrome, Firefox, Safari) or mobile app that sends requests** ✅
- D) The programming language running on the backend

**2. When you type a URL into your browser and press Enter, what happens first?**
- A) **The browser sends an HTTP Request to the server, and the server replies with an HTTP Response** ✅
- B) The server opens a browser window on your computer
- C) The database directly connects to your monitor
- D) JavaScript compiles HTML into C code

**3. Why were early languages like C and Perl used on web servers in the 1990s?**
- A) To style buttons and fonts in the browser
- B) **To generate dynamic HTML pages on the server instead of serving only static files** ✅
- C) To replace the HTTP protocol
- D) To run mobile apps offline

**4. Why did TypeScript become so popular for modern frontend web apps?**
- A) It replaces HTML completely
- B) It runs only on SQLite databases
- C) **It adds strict type-checking on top of JavaScript to catch bugs before code runs** ✅
- D) It is the only language Chrome understands

**5. What was the biggest shift in the "Web 2.0 / Modern SPA" era?**
- A) Websites stopped using servers completely
- B) **Pages could update smoothly without reloading the whole page (via JavaScript & APIs)** ✅
- C) Browsers stopped supporting images
- D) Every website had to be written in C

**6. Which HTTP Status Code range means "Success" (for example, `200 OK` or `201 Created`)?**
- A) **2xx** ✅
- B) 3xx
- C) 4xx
- D) 5xx

**7. Which statement best describes a "Monolithic" web architecture?**
- A) Every feature runs in a separate cloud company
- B) **The UI and Backend live together in one single application/codebase** ✅
- C) There is no database at all
- D) It only works for mobile apps

**8. When does a company typically choose "Microservices" (like Netflix or Uber)?**
- A) For a simple 1-person blog
- B) **When the system is massive and many independent teams need to scale and deploy features separately** ✅
- C) Whenever they want the simplest possible setup
- D) When they don't want to use HTTP

**9. In "Representational State Transfer" (REST), what is the "Representation"?**
- A) The physical server rack in a data center
- B) **The readable snapshot of data (usually JSON) sent between client and server** ✅
- C) The company logo on the homepage
- D) The SQLite file stored on disk

**10. Which HTTP method is used in a REST API to CREATE a new resource (like publishing a new post)?**
- A) `GET`
- B) **`POST`** ✅
- C) `DELETE`
- D) `HEAD`

---

## 2. Multi-select *(Pick every right answer)*

**11. Which three core technologies make up the foundation of almost every web page in your browser?**
- A) **HTML (structure)** ✅
- B) **CSS (styling)** ✅
- C) **JavaScript (interactivity)** ✅
- D) SQLite (database)

**12. What are the 3 logical layers of almost every business web application?**
- A) **Presentation Layer (UI)** ✅
- B) **Business Logic Layer (Backend)** ✅
- C) **Data Layer (Database)** ✅
- D) Wi-Fi Router Layer

**13. Which of the following are benefits of a Decoupled architecture (Frontend SPA + Backend REST API)?**
- A) **Web and mobile apps can share the exact same backend API** ✅
- B) **Frontend and backend teams can work independently** ✅
- C) You never need a database anymore
- D) **You can call or test the backend API directly without a browser (e.g., Postman or `curl`)** ✅

**14. What can you do with an OpenAPI Specification file?**
- A) **Power interactive API documentation (`/docs` Swagger UI)** ✅
- B) **Import it into Postman to auto-generate requests** ✅
- C) **Generate client SDK code (e.g., Angular/TypeScript, Python) and starter server stubs** ✅
- D) Replace CSS styling in the browser

---

## 3. True or false *(Two answers)*

**15. In a REST API, "State" means the physical server hardware where the code runs.**
- **False** ✅ *(State means the current data/values of a resource in the database right now.)*

**16. With a Decoupled architecture, both a web browser app and a mobile app can talk to the exact same Backend REST API.**
- **True** ✅

**17. An OpenAPI specification is written in a standard JSON or YAML format.**
- **True** ✅

**18. In a REST API, `GET` requests are used to delete records from the database.**
- **False** ✅ *(`GET` only reads data; `DELETE` removes records.)*

---

## 4. Multiple choice *(One right answer — Core REST Concepts)*

**19. What does "REST" stand for?**
- A) Rapid Execution Server Technology
- B) Remote Endpoint Storage Tool
- C) **Representational State Transfer** ✅
- D) Reliable External System Transport

**20. In a REST API, what lightweight text format (`{ "key": "value" }`) is most commonly used to exchange data between client and server?**
- A) CSS
- B) **JSON (JavaScript Object Notation)** ✅
- C) SQL
- D) PNG

**21. If `GET /posts` returns all posts, what URL path should you request to get only Post #42?**
- A) `/posts?delete=42`
- B) `/get-post-42`
- C) **`/posts/42`** ✅
- D) `/42/posts/all`

**22. Which HTTP method (verb) is used to update an existing resource (like editing a post)?**
- A) `GET`
- B) `DELETE`
- C) **`PUT`** ✅
- D) `HEAD`
