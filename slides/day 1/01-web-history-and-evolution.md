# Day 1: Modern Web & APIs

## Period 1: Morning Session 1

**Title:** *Web History & Evolution*

> **Instructor Note (Speaker Notes Only — Not Shown on Slides):**
> - **08:00 – 08:25 (25 mins):** Workshop Introduction & 5-Day Roadmap (`roadmap.md`)
> - **08:25 – 09:00 (35 mins):** Part 1 — How the Web & HTTP Work Under the Hood (Slides 1–8)
> - **09:00 – 09:25 (25 mins):** Part 2 — Inside the Web Browser, Its 3 Core Languages & TypeScript (Slides 9–14)
> - **09:25 – 10:00 (35 mins):** Part 3 — History & Evolution: Web 1.0, Web 2.0 & Browser Evolution (Slides 15–22)

---

### Slide 1: Web History & Evolution
  - **Session Overview (3 Parts):**
    - **Part 1: How the Web & HTTP Work Under the Hood**
      - Internet vs. the Web, the Client–Server model, URLs/DNS, and the anatomy of HTTP Requests & Responses.
    - **Part 2: Inside the Web Browser, Its 3 Core Languages & TypeScript**
      - How browsers render pages, the roles of HTML, CSS, and JavaScript, and why modern apps use TypeScript.
    - **Part 3: History & Evolution of the Web & Browsers (Web 1.0 to Web 2.0)**
      - From 1991 static pages and full-page reloads to AJAX, JSON, and the evolution of web browsers into modern application platforms.
- **Talking Points (Speaker Notes):**
  - *[Target Pacing: 08:25 – 09:00 for Part 1 (Slides 1–7)]*
  - Before we talk about Angular, Spring Boot, or PostgreSQL later this week, we need to understand the environment they all live in: the Web, HTTP, and the Browser.

---

## Part 1: How the Web & HTTP Work Under the Hood

---

### Slide 2: The Internet vs. The World Wide Web
- **Slide Points:**
  - **They Are Not the Same Thing:**
    - **The Internet (1960s–1980s):** The global physical network of fiber-optic cables, undersea lines, routers, and data centers connecting millions of computers worldwide.
    - **The World Wide Web (1991):** A software service that runs *on top of* the Internet—specifically, the system of interlinked pages and applications accessed through a web browser.
  - **Other Services That Use the Internet (Besides the Web):**
    - Email (SMTP/IMAP), File Transfer (FTP), SSH (Remote Server Access), VoIP/Video Calls.
- **Talking Points:**
  - People use "Internet" and "Web" interchangeably, but technically the Internet is the physical infrastructure (cables and routers), while the Web (`http://` and `https://`) is one specific application layer running over that network.

---

### Slide 3: The Client–Server Model
- **Slide Points:**
  - **Every Web Interaction Involves Two Sides:**
    - **The Client (Frontend Environment):** The user's device and web browser (Chrome, Edge, Firefox, Safari) or mobile app. It initiates requests and renders the user interface.
    - **The Server (Backend Environment):** A powerful computer running 24/7 in a data center or cloud (with no screen or keyboard attached) that listens for requests, runs business rules, queries the database, and sends back responses.
  - **Key Principle:**
    - Your browser does not hold the company's database or confidential business logic; it requests what it needs from the server over the network.
- **Talking Points:**
  - Whenever you hear developers say "Client-side" vs. "Server-side" (or "Frontend" vs. "Backend"), this is all they mean: code running inside the user's browser vs. code running on the central server machine.

---

### Slide 4: Client–Server Across the Internet (Not Just the Web!)
- **Slide Points:**
  - **Almost All Internet Services Follow the Client–Server Model:**
    - The difference is simply *which* client program and *which* communication protocol they use:

    | Service | Protocol | The Client | The Server |
    | :--- | :--- | :--- | :--- |
    | **The Web** | `HTTP / HTTPS` | Web Browser (Chrome, Edge) or Mobile App | Web Server (Nginx, Spring Boot) |
    | **Email** | `SMTP / IMAP` | Email App (Outlook, Apple Mail, Thunderbird) | Mail Server (Exchange, Gmail SMTP) |
    | **File Transfer** | `FTP / SFTP` | FTP Client (FileZilla) | FTP File Server |
    | **Remote Terminal** | `SSH` | Terminal on your laptop (`ssh user@host`) | Remote Linux Server (`sshd`) |
    | **Database** | `PostgreSQL Protocol` | `pgAdmin` or **Spring Boot Backend** | **PostgreSQL Database Server** |

  - **Key Insight for Our Stack:**
    - Being a "Client" or a "Server" is a **role**: to the **Browser (Angular)**, **Spring Boot** is the **Server**—but when **Spring Boot** queries **PostgreSQL**, **Spring Boot** acts as the **Client** and **PostgreSQL** is the **Database Server**!
- **Talking Points:**
  - The only major alternative to Client–Server on the Internet is **Peer-to-Peer (P2P)** (like BitTorrent), where every computer acts as both a client and a server simultaneously.
  - Point out the last row (Database): this helps students understand how Angular, Spring Boot, and PostgreSQL chain together!

---

### Slide 5: URLs, Domain Names (DNS) & IP Addresses
- **Slide Points:**
  - **How Your Browser Finds the Right Server:**
    - **IP Address (e.g., `192.0.2.44`):** Every device and server on the Internet has a numerical address so routers know where to deliver packets.
    - **Domain Name & DNS (Domain Name System):** Humans remember names (`company-portal.com`), not numbers. DNS acts as the Internet's directory, translating a domain name into an IP address in milliseconds.
  - **Anatomy of a URL (Uniform Resource Locator):**
    - `https://` (**Protocol:** Secure HTTP communication)
    - `app.company.com` (**Domain/Host:** Which server to contact)
    - `/employees/42` (**Path/Resource:** Which specific page or record you are asking for)
- **Talking Points:**
  - We will see URLs constantly when we look at our application and REST APIs this afternoon. Breaking a URL into *Protocol*, *Host*, and *Path* helps you immediately read what any web link is doing.

---

### Slide 6: HTTP & HTTPS — The Request/Response Interaction Cycle
- **Slide Points:**
  - **What is HTTP / HTTPS?**
    - **HTTP (HyperText Transfer Protocol):** The strict request-and-response messaging rules that browsers and servers use to communicate.
    - **HTTPS (HTTP Secure):** Wraps HTTP messages in encryption (TLS/SSL) so nobody on the network can eavesdrop on passwords or business data in transit.
  - **End-to-End Interaction Diagram:**
    ```text
    [ Web Browser (Client) ]
       │  1. DNS Lookup ("What is the IP of app.company.com?")
       ▼
    [ DNS Server ] ──► Returns IP (e.g., 192.0.2.44)
       │
       │  2. HTTP Request  (GET /employees/42)
       ▼
    [ Web / Application Server (e.g., Nginx / Spring Boot) ]
       │  3. Queries / Updates Records
       ▼
    [ Database (e.g., PostgreSQL) ] ──► Returns Data Rows
       │
       │  4. HTTP Response (200 OK + HTML or JSON Data)
       ▼
    [ Web Browser (Client) ] ──► 5. Renders UI on Screen
    ```
- **Talking Points:**
  - Notice that HTTP is **stateless and client-initiated**: the server sits quietly waiting until the browser sends an HTTP Request, and every HTTP Request receives exactly one HTTP Response.

---

### Slide 7: Anatomy of an HTTP Request (What the Browser Sends)
- **Slide Points:**
  - **Every HTTP Request Has 4 Main Parts:**
    1. **HTTP Method (Verb):** Tells the server what action the client wants to perform:
       - `GET` (Read/Fetch data), `POST` (Create/Submit new data), `PUT` (Update existing data), `DELETE` (Remove data).
    2. **Target URL / Path:** The specific resource being requested (e.g., `/api/employees`).
    3. **Request Headers (Metadata):** Key-value pairs describing the request (browser type, accepted data format, security token).
    4. **Request Body (Optional Payload):** The actual data being sent to the server (used in `POST`/`PUT`, e.g., a new employee form submission).
  - **Example HTTP Request:**
    ```http
    POST /api/employees HTTP/1.1
    Host: app.company.com
    Content-Type: application/json
    Authorization: Bearer eyJhbGci...

    {
      "firstName": "Sarah",
      "department": "Engineering"
    }
    ```
- **Talking Points:**
  - Think of an HTTP Request like a business letter in an envelope: the **Method & Path** are written on the outside of the envelope, the **Headers** are the routing stamps and return address, and the **Body** is the letter inside.

---

### Slide 8: Anatomy of an HTTP Response (What the Server Sends Back)
- **Slide Points:**
  - **Every HTTP Response Has 3 Main Parts:**
    1. **HTTP Status Code:** A 3-digit code telling the browser whether the request succeeded or failed:
       - **`2xx` Success:** `200 OK` (Success), `201 Created` (New record saved)
       - **`3xx` Redirect:** `301 Moved Permanently` (Go to a new URL)
       - **`4xx` Client Error:** `400 Bad Request` (Invalid input), `401 Unauthorized` (Not logged in), `403 Forbidden` (No permission), `404 Not Found` (URL doesn't exist)
       - **`5xx` Server Error:** `500 Internal Server Error` (Backend code or database crashed)
    2. **Response Headers (Metadata):** Information about the server's reply (e.g., `Content-Type: application/json` or `text/html`).
    3. **Response Body (Payload):** The actual content requested—either an HTML web page, an image, or structured JSON data.
  - **Example HTTP Response:**
    ```http
    HTTP/1.1 200 OK
    Content-Type: application/json

    {
      "id": 105,
      "firstName": "Sarah",
      "department": "Engineering",
      "status": "Active"
    }
    ```
- **Talking Points:**
  - Recognizing status codes (`200`, `401`, `403`, `404`, `500`) is hugely helpful for non-developers! When an app shows an error, knowing whether it's a `403` (permission issue) vs. a `500` (server/backend bug) tells you immediately who to talk to.

---

## Part 2: Inside the Web Browser & Its 3 Core Languages

### Slide 9: What Is a Web Browser & What Happens Inside It?
- **Slide Points:**
  - **More Than Just a Window:**
    - A web browser (Google Chrome, Microsoft Edge, Mozilla Firefox, Apple Safari) is a complex software application that translates raw network responses into an interactive visual experience.
  - **Key Components Inside Every Modern Browser:**
    1. **Networking Layer:** Sends HTTP requests and receives HTTP responses.
    2. **Rendering Engine (e.g., Blink, WebKit, Gecko):** Reads **HTML** and **CSS** to calculate layouts and paint pixels on your monitor.
    3. **JavaScript Engine (e.g., Chrome's V8):** Executes **JavaScript** code at high speed to handle clicks, animations, and background logic.
    4. **Browser Storage & DevTools:** Stores login cookies/session tokens and provides built-in inspection tools (Right-click → **Inspect**).
- **Talking Points (Speaker Notes):**
  - *[Target Pacing: 09:00 – 09:25 for Part 2 (Slides 8–12)]*
  - When a server sends back a response, it's just plain text over a wire. The browser is the engine that turns that raw text into buttons, tables, colors, and interactive screens.

---

### Slide 10: HTML — Structure & Content
- **Slide Points:**
  - **HTML (HyperText Markup Language):**
    - The standard markup language used to define the **structure and raw content** of a web page.
    - Uses "tags" (like `<h1>` for headings, `<p>` for paragraphs, `<table>` for tables, `<button>` for buttons) to tell the browser's rendering engine *what* elements exist on the page.
  - **What HTML Alone Looks Like:**
    - Plain black text on a white background, stacked top-to-bottom, with blue underlined links.
- **Talking Points:**
  - HTML is not a programming language with decision-making logic—it is a document structure language that defines the skeleton of a page.

---

### Slide 11: CSS — Presentation, Layout & Design
- **Slide Points:**
  - **CSS (Cascading Style Sheets — Introduced in 1996):**
    - Created to separate **document structure (HTML)** from **visual presentation (CSS)**.
  - **What CSS Controls:**
    - Colors, typography, spacing, grids, sidebars, button styles, and visual themes.
    - **Responsive Design:** Adapting the same page layout automatically for a large desktop monitor, a laptop, or a smartphone screen.
- **Talking Points:**
  - Without CSS, every modern web app would still look like a 1991 academic paper. CSS is what gives an application its brand colors, clean sidebars, and modern layout.

---

### Slide 12: JavaScript — In-Browser Logic & Interactivity
- **Slide Points:**
  - **JavaScript (Introduced in 1995):**
    - The programming language executed by the **Browser's JavaScript Engine** directly on the user's computer.
  - **What JavaScript Does on a Page:**
    - Responds instantly to user actions (switching tabs, opening modal dialogs, sorting a table column, validating form fields before submission).
    - Sends background HTTP requests (**AJAX / Fetch**) to the server without reloading the page.
    - Updates the HTML and CSS on the screen dynamically in real time.
- **Talking Points:**
  - Important distinction for non-developers: **JavaScript** and **Java** are two completely different programming languages. JavaScript runs inside the browser (Frontend); Java runs on the server (Backend).

---

### Slide 13: Why Plain JavaScript Can Be a Pain — And Why TypeScript Exists
- **Slide Points:**
  - **Why Plain JavaScript (`.js`) Becomes Difficult in Large Applications:**
    - **Created in Just 10 Days (1995):** Originally designed for tiny scripts (like button pop-ups), not 100,000-line enterprise applications.
    - **No Strict Data Types ("Dynamic Typing"):** A variable holding a salary number (`5000`) can accidentally be overwritten with text (`"5000"`) or `undefined` without warning—leading to weird bugs like `10 + "5"` becoming `"105"` instead of `15`.
    - **Errors Only Appear at Runtime:** If a developer misspells a field name (e.g., `employee.departmnt`), plain JavaScript won't complain until a real user clicks a button in the browser and the screen breaks.
  - **The Solution — TypeScript (`.ts`, Created by Microsoft in 2012):**
    - **"JavaScript with Guardrails (Static Types)":** A stricter layer built on top of JavaScript that requires developers to define the exact structure of data (e.g., an `Employee` must have an `id: number` and `department: string`).
    - **Catches Bugs Before the App Even Runs:** The code editor flags typos and mismatched data types immediately while developers are typing.
    - **Used by Angular:** Angular applications are written in **TypeScript** for safety and maintainability, then automatically translated (compiled) into standard **JavaScript** for the browser.
- **Talking Points:**
  - Think of plain JavaScript like writing a legal contract on a blank notepad with zero spellcheck, whereas TypeScript is like a smart form that refuses to let you put text inside a date field or misspell a required clause.

---

### Slide 14: Crucial Takeaway — What the Browser Actually Understands
- **Slide Points:**
  - **Web Browsers Only Speak Three Languages:**
    1. **HTML** (Structure)
    2. **CSS** (Style)
    3. **JavaScript** (Interactivity & Logic)
  - **Where Do TypeScript, Angular & Spring Boot Fit In?**
    - Browsers do **not** directly understand **TypeScript**, **Java/Spring Boot**, or **PostgreSQL**.
    - Developers write frontend code in **Angular & TypeScript**, which is compiled into standard **HTML, CSS, and JavaScript** bundles before being sent to the browser.
    - **Spring Boot (Java)** runs on the server and sends raw data (**JSON**) over HTTP to the JavaScript running in the browser.
- **Talking Points:**
  - This single slide connects everything you'll see over the next 5 days: developers use TypeScript and Angular to write safer, cleaner frontend code, but the final output running inside Chrome or Edge is always HTML, CSS, and JavaScript.

---

## Part 3: History & Evolution of the Web & Browsers (Web 1.0 to Modern Web 2.0)

### Slide 15: 1991 & Web 1.0 — The "Read-Only" Static Web
- **Slide Points:**
  - **Historical Context (1991 – Late 1990s):**
    - Invented by **Tim Berners-Lee** at **CERN** in 1989–1991 to allow scientists to share research documents across servers using HTTP and HTML.
  - **How Web 1.0 Worked:**
    - Webmasters wrote static `.html` files and saved them in folders on a web server.
    - When a user visited a URL, the server simply sent that fixed `.html` file in the HTTP response.
  - **Characteristics:**
    - **Read-Only:** Users could read text and click hyperlinks, but could not log in, comment, upload content, or customize their view.
    - **Static Content:** Every visitor saw the exact same page until a webmaster manually edited the file on the server.
  - **Examples:** CERN's first website (`info.cern.ch`), early Yahoo! directories, GeoCities, static corporate brochure sites.
- **Talking Points (Speaker Notes):**
  - *[Target Pacing: 09:25 – 10:00 for Part 3 (Slides 15–22)]*
  - Think of Web 1.0 like a digital encyclopedia: great for publishing static documents, impossible for running interactive business workflows.

---

### Slide 16: Early Server-Side Programming — C & Perl (Mid-1990s)
- **Slide Points:**
  - **Why Static HTML Was No Longer Enough (1993–1995):**
    - Websites needed to process search boxes, contact forms, guestbooks, and visitor counters—which static `.html` files could not do.
    - Web servers started running backend programs on the server to process user inputs and generate HTML pages dynamically on the fly.
  - **C (The Original Systems Language):**
    - *Logo:* `[C Language Logo]`
    - **Why it was used:** Early Unix web servers were written in C, and compiled C programs ran extremely fast for early search engines and high-traffic scripts.
    - **The drawback:** Building and manipulating HTML text strings in C was tedious and error-prone (manual memory management meant a tiny string bug could crash the program).
  - **Perl ("The Duct Tape of the Early Internet"):**
    - *Logo:* `[Perl Language Logo]`
    - **Why it became the #1 early web language:** Created by Larry Wall specifically for **text processing and pattern matching (Regex)**. Since HTTP requests and HTML pages are just text, Perl made it much easier to read form inputs and print out dynamic HTML pages.
  - **Why Early C & Perl Scripts Were Eventually Replaced:**
    - Running standalone C or Perl scripts started a brand-new OS process on the server for *every single click*—which overloaded servers as traffic grew and led to dedicated web runtimes like **PHP**, **ASP**, and **Java (Servlets/JSP → Spring Boot)**.
- **Talking Points:**
  - If you ever saw `.pl` in an old 1990s URL, you were looking directly at a Perl script generating HTML on the server!

---

### Slide 17: The Late 1990s Transition — Server-Rendered Dynamic Pages
- **Slide Points:**
  - **The Need for Dynamic Data:**
    - Businesses needed e-commerce, user logins, search engines, and live database records—not fixed text files.
  - **How Early Dynamic Websites Worked (Multi-Page Applications):**
    - Server languages (PHP, early Java/JSP, Classic ASP) connected the web server to a **Database**.
    - Every time a user clicked a link or submitted a form, the browser sent an HTTP request, and the server queried the database, built a **brand-new HTML page from scratch**, and sent the entire page back.
  - **The Major Pain Point:**
    - **Full-Page Reloads:** Every single click caused the browser screen to flash white, lose your scroll position, and re-download the header, sidebar, logos, and styles all over again.
- **Talking Points:**
  - If you remember filling out an online form in the early 2000s where clicking a button or making a validation mistake forced the entire page to reload—that was server-rendered Multi-Page architecture.

---

### Slide 18: Mid-2000s & Web 2.0 — The "Read-Write & Participatory" Web
- **Slide Points:**
  - **Core Concept (~2004 – Present):**
    - The web transformed from a one-way document viewer into a **two-way interactive platform** where users create content, collaborate, and run full software applications inside the browser.
  - **Key Characteristics:**
    - **User Participation & Real-Time Updates:** Interactive dashboards, cloud office suites, social platforms, and collaborative business tools.
    - **Desktop-Class User Experience:** Web applications started behaving like fast, installed desktop software rather than static text documents.
  - **Representative Examples:**
    - Gmail (2004), Google Maps (2005), Wikipedia, YouTube, Google Docs, and modern enterprise web systems (like our Employee Management System).
- **Talking Points:**
  - When Gmail and Google Maps launched in 2004–2005, people saw for the first time that a website could drag maps smoothly and open emails instantly without refreshing the page.

---

### Slide 19: The Technical Breakthrough Behind Web 2.0 — AJAX & JSON
- **Slide Points:**
  - **What is AJAX? (Asynchronous JavaScript and XML):**
    - A technique allowing JavaScript inside the browser to send HTTP requests to the server **quietly in the background** while the user keeps working on the screen.
  - **How It Changed Web Communication:**
    - **Before AJAX (Web 1.0 / Early Dynamic):** Browser clicks button → Server sends back an **entire new HTML page** → Browser wipes screen and reloads everything.
    - **With AJAX & Modern APIs (Web 2.0):** Browser clicks button → JavaScript sends a background HTTP request → Server sends back lightweight raw data (**JSON**) → Browser updates **only that specific table row or section** on the screen.
  - **The Rise of Single-Page Applications (SPAs):**
    - This breakthrough directly gave birth to modern frontend frameworks like **Angular**, React, and Vue.
- **Talking Points:**
  - This is the most important technical turning point in web history for understanding our stack: separating the *user interface* (loaded once in the browser) from the *data* (fetched quietly in the background via HTTP).

---

### Slide 20: How Web Browsers Evolved from Web 1.0 to Web 2.0
- **Slide Points:**
  - **Web 1.0 Browsers (1990s — Simple Document Viewers):**
    - **WorldWideWeb / Nexus (1990 — The Very First Browser):**
      - *Image:* `https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHU0wCE1NSQhwsObiaLosE12g6UHeBmQrGZARY3L3wHA&s=10`
      - Created by **Tim Berners-Lee** at CERN on a NeXT computer; later renamed **Nexus** to avoid confusion with the World Wide Web itself.
      - Served as both a text-based browser and a basic HTML editor, proving that documents could be linked and read over the Internet.
    - **NCSA Mosaic (1993):**
      - *Image:* `https://www.ariadne.ac.uk/images/issue2-brian/fig1.gif`
      - The first popular graphical browser to display images inline with text on the same page instead of in a separate window.
      - Popularized the World Wide Web outside of universities and research labs.
    - **Netscape Navigator (1994):**
      - *Image:* `https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJd0b0SZBSKnY7YiqiVnX7jQfvuVPeFfA_IvYdbG_hGbpUQ70WxgzCGyRu&s=10`
      - Dominated the mid-1990s web and invented **JavaScript** (1995), **Cookies**, and **SSL (Secure Sockets Layer)** encryption for HTTPS e-commerce.
      - Later open-sourced its browser code, which became the foundation for Mozilla Firefox.
      - Netscape's **SSL** later served as the foundation for **TLS (Transport Layer Security)**, which was developed and standardized by the **IETF (Internet Engineering Task Force)** in 1999 (led by Christopher Allen and Tim Dierks).
    - **Internet Explorer (1995):**
      - *Image:* `https://www.versionmuseum.com/images/applications/internet-explorer/webp/internet-explorer%5E1995%5Eie-2.0-windows-home.webp`
      - Bundled free with Windows, winning the "First Browser War" (>90% market share by 2002).
      - Introduced `XMLHttpRequest` (the seed of **AJAX**), but suffered from non-standard HTML/CSS rules and slow updates for years.
  - **Web 2.0 Browsers (Mid-2000s to Today — Full Application Platforms):**
    - **Mozilla Firefox (2004):**
      - *Image:* `https://cdnjs.cloudflare.com/ajax/libs/browser-logos/74.1.0/firefox/firefox_256x256.png`
      - Broke Internet Explorer's monopoly with tabbed browsing, pop-up blockers, strong W3C web standards, and the **Firebug** developer tools.
    - **Apple Safari (2003):**
      - *Image:* `https://cdnjs.cloudflare.com/ajax/libs/browser-logos/74.1.0/safari/safari_256x256.png`
      - Pioneered the lightweight open-source **WebKit** rendering engine and brought full desktop-grade web browsing to smartphones (iPhone, 2007).
    - **Google Chrome (2008):**
      - *Image:* `https://cdnjs.cloudflare.com/ajax/libs/browser-logos/74.1.0/chrome/chrome_256x256.png`
      - Built specifically for the **Web 2.0 application era**: introduced the ultra-fast **V8 JavaScript Engine** (compiling JS to machine code) and **multi-process sandboxing** (one crashed tab doesn't crash the whole browser).
      - Created the open-source **Chromium** project and built-in **Chrome DevTools**.
    - **Microsoft Edge (2015 / Chromium 2020):**
      - *Image:* `https://cdnjs.cloudflare.com/ajax/libs/browser-logos/74.1.0/edge/edge_256x256.png`
      - Replaced Internet Explorer and adopted Google's **Chromium/Blink** engine in 2020, ensuring modern enterprise web apps (like Angular) run identically on Edge and Chrome.
- **Talking Points:**
  - Without this evolution in web browsers, frameworks like **Angular** could not exist. In the 1990s, a browser was basically a simple document reader; today, Chrome or Edge is practically an operating system running inside your computer.

---

### Slide 21: Summary Comparison — How the Web & Browsers Evolved

| Dimension | Web 1.0 (1991 – Late 1990s) | Early Dynamic Web (Late 1990s – ~2004) | Modern Web 2.0 & SPAs (~2004 – Present) |
| :--- | :--- | :--- | :--- |
| **Primary Mode** | **Read-Only** (Consume static info) | **Interactive via Forms** (Logins, early e-commerce) | **Read-Write & Live** (Create, edit & collaborate in real time) |
| **How Pages Update** | Opening a new static `.html` file | **Full-page reload** on every click | **Background HTTP requests (AJAX / REST)** — no full reload |
| **HTTP Response Payload** | Pre-written static HTML files | A brand-new server-built HTML page every time | Lightweight data (**JSON**) sent to dynamic frontends (Angular) |
| **Role of the Browser** | Simple document viewer (Mosaic, early Netscape/IE) | Form submitter & HTML page renderer | High-speed **application runtime** (Chrome/Edge V8 engine, HTML5) |
| **User Experience** | Digital brochure / online textbook | Functional, but screen flashes white on every action | Smooth, fast, desktop-class application |
| **Core Technologies** | HTML, HTTP, basic CSS | C, Perl → PHP / JSP / ASP + Databases | JavaScript, AJAX, REST APIs, **Angular**, **Spring Boot**, **PostgreSQL** |

---

### Slide 22: Quick Recap & The "Bridge" to Next Session
- **Slide Points:**
  - **Quick Recap:**
    1. **Client–Server & HTTP:** Every web interaction is an **HTTP Request** (Method, URL, Headers, Body) sent by the client and an **HTTP Response** (Status Code, Headers, Payload) returned by the server.
    2. **The 3 Browser Languages & TypeScript:** Browsers only understand **HTML** (structure), **CSS** (style), and **JavaScript** (logic)—while **TypeScript** adds strict type guardrails for developers before compiling down to JavaScript.
    3. **Web 1.0 to Web 2.0 (AJAX & JSON):** Moving from static files and disruptive full-page reloads to quiet background requests (**AJAX**) allowed web pages to update smoothly using lightweight **JSON** data.
    4. **Browser Evolution:** Web browsers evolved from simple 1990s document viewers into high-speed application platforms (like Chrome's V8 engine and modern Web Standards) capable of running desktop-class apps like **Angular**.
  - **The "Bridge" to Next Session:**
    - *Now that modern browsers can run full user interfaces and fetch data quietly in the background, how should we structure our Frontend, Backend, and Database—and how do they communicate using REST APIs?*
    - **Next Session:** *Web Apps Architectures & REST API*
- **Talking Points:**
  - Pause here for any questions on HTTP, browsers, HTML/CSS/JS/TypeScript, or Web 1.0 vs. Web 2.0 before taking our break and diving into Web Application Architectures.

