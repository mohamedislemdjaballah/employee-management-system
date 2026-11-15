# Day 2 — Period 1 (Part A): Java Basics (~1 Hour)

> **Target Audience:** Non-developers / mixed team  
> **Goal:** Build a simple, beginner-friendly understanding of **Java**—what it is, why schools and enterprises love it, how it runs on the JVM, and how Java code is organized into packages and frameworks.

---

### Slide 1: Java Basics — The Language Behind Enterprise Backends
- **Slide Points:**
  - **Day 2 — Period 1 (Part A):** Understanding Java
  - **What You'll Learn:**
    1. What **Java** is (and why it's *not* JavaScript)
    2. How Java code runs anywhere using the **JVM**
    3. Simple building blocks: **Classes**, **Objects**, **Types**, and **Packages**
    4. Why web servers use **Frameworks** instead of plain Java
- **Talking Points:**
  - *(0:00–0:05)* Welcome to Day 2! Yesterday we called a REST API from the outside. Today we step **inside the Backend**—starting with the **Java** language in simple, plain English.

---

### Slide 2: History of Java & Its Key Milestones
- **Slide Points:**
  - **1. 1991–1995: Born as "Oak"**
    - Started in 1991 for smart TVs — named after an oak tree outside the creator's office.
    - Renamed **"Java"** (after coffee!) in 1995: write once, run on any computer.
  - **2. 1999–2004: Trusted by Big Companies**
    - Banks, airlines, and websites adopted Java because it was reliable and safe.
    - Updates made everyday code shorter and easier to write.
  - **3. 2010–2014: A New Home & Simpler Code**
    - Oracle bought Sun Microsystems (2010) and kept growing Java.
    - **Java 8 (2014)** made working with lists of data much simpler.
  - **4. 2017–Today: Regular Updates**
    - Gets a fresh update every 6 months (with long-support versions every few years).
    - Powers Android phones, websites, and major company servers today.
- **Talking Points:**
  - Java started in 1991 as a project called Oak for smart TVs, then launched in 1995 as Java (named after coffee!). Over 30 years it grew from powering banks and websites to Android phones and modern company servers, with regular updates every 6 months.

---

### Slide 2: Bridge from Day 1 — Where Does Java Live?
- **Slide Points:**
  - In a **Decoupled Web App**:
    - **Frontend (Browser):** Angular / React (HTML, CSS, TypeScript)
    - **Backend (Server):** **Java + Spring Boot** ← *We are here!*
    - **Database:** PostgreSQL
  - **Java's Role:** Runs on the server to process requests, check business rules, and talk to the database.
- **Talking Points:**
  - *(0:05–0:08)* Remind participants of the 3 logical layers from Day 1: Java lives in the **Business Logic Layer** on the backend server.

---

### Slide 3: What Is Java?
- **Slide Points:**
  - Created in **1995** with one famous motto: ***"Write Once, Run Anywhere"***
  - **3 Simple Facts About Java:**
    - **Java ≠ JavaScript:** Completely different languages! JavaScript runs in browsers; Java powers backend servers
    - **Strongly Typed:** Every variable has a fixed type that cannot change:
      - `int age = 25;` → `age = "hello";` ❌ **Error!** (Cannot put text into an `int` — unlike Python or JavaScript)
    - **Object-Oriented (OOP):** Organizes code around real-world things (`Employee`, `Post`, `Order`)
- **Talking Points:**
  - *(0:08–0:14)* Clarify the classic non-developer mix-up: Java and JavaScript are about as related as *Car* and *Carpet*!

---

### Slide 4: Why Do Enterprises & Universities Love Java?
- **Slide Points:**
  - **1. Built to Last:** Java code written 10 years ago still runs smoothly today
  - **2. Catches Mistakes Early:** Strict rules catch typos and wrong data types *before* the app goes live
  - **3. Proven at Huge Scale:** Used by banks, **Netflix**, **Amazon**, **LinkedIn**, and **Uber**
  - **4. Taught Everywhere:** Widely taught in schools and universities for **Object-Oriented Programming (OOP)**—almost any university tech student already knows Java, making hiring and onboarding easy
- **Talking Points:**
  - *(0:14–0:20)* Emphasize why big organizations trust Java: predictability, long-term support, and a massive pool of graduates and engineers who already speak it.

---

### Slide 5: How C Runs — Compiled Directly to Machine Code
- **Slide Points:**
  - **Step-by-Step Flow (Which File Is Shared?):**
    ```mermaid
    flowchart LR
        A["main.c (Source Code)"] --> B["Compiler (gcc)"]
        B -->|"Share separately per OS"| C["Windows (.exe)"]
        B -->|"Share separately per OS"| D["macOS (binary)"]
        B -->|"Share separately per OS"| E["Linux (binary)"]
    ```
  - **What You Share:** You compile **first**, then share a **different binary file** for Windows, macOS, and Linux!
  - **Pros:** Fastest possible speed (CPU runs the binary directly)
- **Talking Points:**
  - *(0:20–0:24)* In C, the compiler translates your code straight into raw 1s and 0s for one specific CPU and operating system—so you have to build and share 3 separate files if your users are on Windows, Mac, and Linux.

---

### Slide 6: How Python Runs — Interpreted Line by Line
- **Slide Points:**
  - **Step-by-Step Flow (Which File Is Shared?):**
    ```mermaid
    flowchart LR
        A["app.py (Source Code)<br/>Shared directly!"] -->|"Same file sent everywhere"| B["Python Interpreter"]
        B --> C["Reads & Runs Line by Line"]
        C --> D["Output on Windows / Mac / Linux"]
    ```
  - **What You Share:** You share the **source file (`app.py`) right from Step 1**—any machine with Python runs it immediately!
  - **Trade-off:** Slower than C/Java, and errors only show up when the program reaches that exact line
- **Talking Points:**
  - *(0:24–0:28)* Think of Python like a **live translator** reading a speech sentence by sentence—you share the raw `.py` script right from step 1, and anyone with Python installed can run it.

---

### Slide 7: How Java Runs — Compiled Once to Bytecode + JVM
- **Slide Points:**
  - **Step-by-Step Flow (Which File Is Shared?):**
    ```mermaid
    flowchart LR
        A["Employee.java"] -->|"javac"| B["Employee.class / app.jar<br/>(1 Shared Bytecode File!)"]
        B -->|"Same .jar shared"| C["JVM (Windows)"]
        B -->|"Same .jar shared"| D["JVM (macOS)"]
        B -->|"Same .jar shared"| E["JVM (Linux)"]
    ```
  - **What You Share:** You compile **once**, then share **one single `.jar` file** that runs on Windows, macOS, and Linux!
  - **Commands:**
    ```bash
    javac Employee.java   # 1. Compile (.java -> .class)
    java Employee         # 2. Run (no .class extension!)
    java -jar app.jar     # Or run a packaged .jar app
    ```
  - **`.class` vs. `.jar`:** `.class` is one compiled file; `.jar` bundles **all `.class` files + libraries** into one runnable file
- **Talking Points:**
  - *(0:28–0:34)* Analogy: Bytecode is like **sheet music**, and the **JVM** is the **musician** who can read it on Windows, Mac, or Linux. Real apps are shipped as one `.jar` file and started with `java -jar app.jar`.
---

### Slide 8: Building Blocks — Primitive Types
- **Slide Points:**
  - Java has **8 Primitive Types** (its simplest built-in building blocks):
    ```java
    byte age = 25;              // tiny whole number (-128 to 127)
    short year = 2026;          // small whole number (-32,768 to 32,767)
    int count = 42;             // standard whole number (-2,147,483,648 
                                // to 2,147,483,647)
    long population = 8000000L; // very large whole number (-9.22 quintillion 
                                // to +9.22 quintillion)

    float price = 19.99f;       // smaller decimal (~6-7 digits, ±3.4e38)
    double salary = 75000.50;   // standard decimal (~15 digits, ±1.7e308)

    boolean isActive = true;    // true or false only
    char grade = 'A';           // one single character (0 to 65,535 Unicode)
    ```
  - **Whole Numbers (`byte` < `short` < `int` < `long`):** All store whole numbers—they only differ in **box size (memory & max number)** (`1`, `2`, `4`, `8` bytes).
  - **Decimals (`float` vs. `double`):** `float` (4 bytes, ~7 decimal digits) vs. `double` (8 bytes, ~15 decimal digits — *double* the precision, Java's default!).
  - **Note (`String`):** `String name = "Sarah";` is *not* a primitive (it's an object), but it's used everywhere for text!
- **Talking Points:**
  - *(0:27–0:32)* Reassure beginners: you don't need to memorize all 8—**`int`**, **`double`**, and **`boolean`** (plus `String` for text) cover 95% of everyday code!

---

### Slide 9: Basic Java Syntax — Variables & Printing
- **Slide Points:**
  - **3 Quick Rules You'll See Everywhere:**
    - Every instruction ends with a semicolon **`;`**
    - Groups of code live inside curly braces **`{ ... }`**
    - Notes for humans start with **`//`** (comments)
  - **Creating Variables & Printing to the Screen:**
    ```java
    String name = "Sarah";
    int age = 29;
    boolean isActive = true;

    System.out.println("Hello, " + name);
    ```
- **Talking Points:**
  - *(0:32–0:36)* Point out that in Java you always write the **type** first (`String`, `int`, `boolean`), then the **name**, then `=`, then the **value**.

---

### Slide 10: Making Decisions — `if` / `else`
- **Slide Points:**
  - Use **`if` / `else`** when your program needs to choose between two paths:
    ```java
    int age = 20;

    if (age >= 18) {
        System.out.println("Adult");
    } else {
        System.out.println("Minor");
    }
    ```
  - **Useful Comparison Symbols:** `==` (equal), `!=` (not equal), `>`, `<`, `>=`, `<=`
- **Talking Points:**
  - *(0:36–0:40)* Emphasize that `if / else` reads just like plain English: *"If age is 18 or more, print Adult; otherwise, print Minor."* Note the double `==` for comparing two numbers!

---

### Slide 11: Repeating Tasks — `for`, `for-each` & `while` Loops
- **Slide Points:**
  - **`for` Loop** — Repeat something a specific number of times:
    ```java
    for (int i = 1; i <= 5; i++) {
        System.out.println("Step " + i);
    }
    ```
  - **`for-each` Loop** — Go through every item in a list or array (one by one):
    ```java
    for (String name : names) {
        System.out.println("Hello " + name);
    }
    ```
  - **`while` Loop** — Keep repeating *while* a condition is `true`:
    ```java
    int count = 1;
    while (count <= 3) {
        System.out.println("Try #" + count);
        count++;
    }
    ```
- **Talking Points:**
  - *(0:40–0:45)* Loops save us from copy-pasting the same line 100 times. Use a **`for` loop** when you know how many times to repeat, a **`for-each` loop** when you want to visit every item in a list without dealing with an index `i`, and a **`while` loop** when you want to keep going until a condition changes!

---

### Slide 12: Building Blocks — Classes & Objects
- **Slide Points:**
  - **Class = The Blueprint (Template):**
    ```java
    public class Employee {
        String firstName;
        int age;
        boolean isActive;

        Employee(String firstName, int age, boolean isActive) {
            this.firstName = firstName;
            this.age = age;
            this.isActive = isActive;
        }
    }
    ```
  - **Object = A Real Item Built With `new`:**
    ```java
    Employee emp1 = new Employee("Sarah", 29, true);
    Employee emp2 = new Employee("Karim", 34, true);
    ```
  - **One Class, Many Objects:** Just like one house blueprint can build 100 houses!
- **Talking Points:**
  - *(0:32–0:37)* Keep it visual: when developers say *"the Employee class"*, they mean the template; when they say *"an Employee object"*, they mean one specific person's record.

---

### Slide 13: Building Blocks — Methods (Actions)
- **Slide Points:**
  - **Methods** are the **actions** inside a class (`Inputs → Action → Output`):
    ```java
    public class Employee {
        // ... fields & constructor ...

        String getFullName() {
            return firstName + " " + lastName;
        }

        double calculateBonus(double percent) {
            return salary * percent;
        }

        void deactivate() { // "void" = returns nothing
            isActive = false;
        }
    }
    ```
  - **Calling Methods on an Object:**
    ```java
    String name = emp1.getFullName();      // "Sarah Connor"
    double bonus = emp1.calculateBonus(0.1);
    emp1.deactivate();
    ```
- **Talking Points:**
  - *(0:37–0:41)* Classes have **data** (fields like `firstName`) and **actions** (methods like `getFullName()`). In Spring Boot, every API endpoint simply calls a Java method!

---

### Slide 14: Building Blocks — Inheritance (`extends`)
- **Slide Points:**
  - **Inheritance** lets a child class reuse fields and methods from a parent class using **`extends`**:
    ```java
    // Parent class
    public class Person {
        String name;
    }

    // Child class gets "name" automatically!
    public class Employee extends Person {
        String role;
    }

    Employee emp = new Employee();
    emp.name = "Sarah";      // from Person!
    emp.role = "Developer";  // from Employee
    ```
- **Talking Points:**
  - *(0:41–0:44)* Together, **Classes**, **Objects**, and **Inheritance** are the 3 core OOP building blocks: a **Class** is the blueprint, an **Object** is the real item built with `new`, and **Inheritance** (`extends`) lets you share code from a parent class without copy-pasting!

---

### Slide 14: Building Blocks — Collections (`List`, `Set`, `Map`)
- **Slide Points:**
  - When you need to group **many items** together, Java gives you **Collections**:
    - **`List` — Ordered List:** Keeps items in order and allows duplicates:
      ```java
      List<String> names = List.of("Sarah", "Karim", "Sarah");
      ```
    - **`Set` — Unique Bag:** Automatically removes duplicates:
      ```java
      Set<String> roles = Set.of("ADMIN", "HR", "USER");
      ```
    - **`Map` — Key → Value Dictionary:** Instant lookup by a unique key:
      ```java
      Map<Integer, String> users = Map.of(1, "Sarah", 2, "Karim");
      users.get(1); // returns "Sarah"
      ```
- **Talking Points:**
  - *(0:41–0:46)* Point out how readable these are: `List` = ordered checklist (duplicates OK); `Set` = unique tags/roles (no duplicates); `Map` = a phonebook or dictionary (`id -> name`)!
---

### Slide 15: Java Packages vs. Frameworks
- **Slide Points:**
  - **What Is a Java Package?**
    - A **folder / namespace** that groups related Java classes so files stay tidy and names don't clash (e.g., `com.company.ems.service`, `java.util`, `java.time`)
    - Also used for helper libraries like **Jackson** (JSON) or **PDFBox** (PDFs)
  - **Package vs. Framework:**
    - **Package / Library (One Specific Tool):** A single helper you call for one job (e.g., formatting a date or creating a PDF)
    - **Framework (Designed for One Type of App):** A **set of related packages and tools** designed specifically for the kind of app you're building—giving you the full structure and wiring out of the box (e.g., **Spring Boot** for web APIs, **Angular** for web UIs, **Android SDK** for mobile)
- **Talking Points:**
  - *(0:38–0:44)* Analogy: A **package/library** is like buying a **microwave** (you press Start when you want). A **framework** is like renting a **fully equipped commercial kitchen**!

---

### Slide 16: Why Not Plain Java for Web Servers?
- **Slide Points:**
  - **Plain Java** only gives you the basic language. It does **not** include the ready-made tools every web app needs:
    - **Web Requests & URLs:** Receiving HTTP requests and matching URLs (`/posts`, `/users`)
    - **JSON & Checking Inputs:** Turning Java data into JSON and checking if required fields are missing
    - **Talking to Databases:** Connecting to the database safely for many users at once
    - **Login & Security:** Checking passwords, keeping users logged in, and blocking unauthorized access
    - **Fast Memory (Cache):** Saving frequent answers in memory so the app stays fast
    - **Logs & periodic Tasks:** Writing down errors and running scheduled jobs (like daily reports)
  - **A Framework** gives you all of these tools ready to use!
- **Talking Points:**
  - *(0:44–0:50)* Nobody builds a web server with plain Java alone—using a framework saves months of hard, repetitive work.

---

### Slide 17: Popular Java Frameworks & What They Are Used For
- **Slide Points:**
  - **Web Backends:**
    - **Spring Boot:** The **#1** framework for building modern **REST APIs**, web backends, and microservices
    - **Jakarta EE (formerly Java EE):** Traditional web framework used in large banking and government systems
  - **Desktop & Mobile:**
    - **JavaFX & Swing:** Build **cross-platform desktop apps** (e.g., IntelliJ IDEA uses Swing!)
    - **Android SDK:** Java (alongside Kotlin) powered the original **Android mobile apps** ecosystem
- **Talking Points:**
  - *(0:50–0:55)* Java isn't just for servers! **Android** and **desktop tools** use it too—and for web backends, **Spring Boot** is the clear market leader.

---

### Slide 18: Quick Recap & Up Next: Spring Boot
- **Slide Points:**
  - **Java Recap:**
    - Strongly typed, OOP language that runs anywhere via the **JVM** (`.class` / `.jar`)
    - Organized into **Packages**, powered by **Frameworks** for real-world apps
  - **Up Next (Part B):** **Spring Boot Basics** — how we turn Java into a live REST API in minutes!
- **Talking Points:**
  - *(0:55–1:00)* Take any quick questions on Java basics before switching to the **Spring Boot** deck.
