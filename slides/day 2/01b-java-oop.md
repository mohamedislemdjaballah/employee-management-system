# Day 2 — Period 2: OOP in Java (Object-Oriented Programming)

> **Target Audience:** Very beginners / non-developers  
> **Goal:** Understand **Object-Oriented Programming (OOP)** in plain English—Classes, Objects, Constructors, Methods, Visibility (`public`/`private`), and the 4 Pillars of OOP.

---

### Slide 1: OOP in Java — Thinking in Real-World Objects
- **Slide Points:**
  - **Day 2 — Period 2:** Object-Oriented Programming (OOP)
  - **What You'll Learn:**
    1. **Why OOP?** Organizing code around real-world things
    2. **Classes & Objects:** Blueprints vs. real items (`new`)
    3. **Constructors & Methods:** Building objects and giving them actions
    4. **The 4 Pillars of OOP** in simple, everyday English
- **Talking Points:**
  - *(0:00–0:05)* So far we wrote variables and loops. Now we learn **OOP**—the way Java organizes big apps so they stay clean and easy to understand.

---

### Slide 2: Why Do We Need OOP?
- **Slide Points:**
  - **Without OOP (Messy separate variables):**
    ```java
    String emp1Name = "Alice";
    String emp1Role = "Developer";

    String emp2Name = "Bob";
    String emp2Role = "Designer";
    ```
  - **With OOP (One clean box per employee):**
    ```java
    Employee emp1 = new Employee(1, "Alice", "Developer");
    Employee emp2 = new Employee(2, "Bob", "Designer");
    ```
- **Talking Points:**
  - *(0:05–0:10)* Imagine managing 1,000 employees with separate variables (`emp1Name`, `emp2Name`...)—impossible! OOP lets us bundle each employee's data and actions into one neat box.

---

### Slide 3: Class vs. Object (The #1 Concept)
- **Slide Points:**
  - **Class = The Blueprint (Recipe)**
    - Describes what every `Employee` has (`id`, `name`, `role`) and can do.
    - Written once. Does **not** hold real data by itself.
  - **Object = The Real Item Built From the Blueprint**
    - Created using the **`new`** keyword:
      ```java
      Employee emp1 = new Employee(1, "Alice", "Developer");
      ```
    - You can create **many objects** from **one class** (`emp1`, `emp2`, `emp3`...).
- **Talking Points:**
  - *(0:10–0:16)* Analogy: A **house blueprint** is a **Class**. The **3 actual houses** built from that blueprint are **Objects**.

---

### Slide 4: Inside a Class — Fields (What an Object *Has*)
- **Slide Points:**
  - **Fields** (also called attributes) are variables inside a class:
    ```java
    public class Employee {
        public int id;
        public String name;
        public String role;
    }
    ```
  - Reading & changing a field with a dot (`.`):
    ```java
    emp1.name = "Alice";
    System.out.println(emp1.name); // prints: Alice
    ```
- **Talking Points:**
  - *(0:16–0:21)* Think of fields as the **properties** of the object—for a Car it's `color` and `speed`; for an Employee it's `id`, `name`, and `role`.

---

### Slide 5: Constructors — Building an Object With `new`
- **Slide Points:**
  - A **Constructor** is a special block that runs automatically when you write `new`:
    ```java
    public class Employee {
        public int id;
        public String name;

        // 1. Empty constructor
        public Employee() {}

        // 2. Full constructor
        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
    ```
  - **`this.name`** means *"this object's own `name` field"*.
- **Talking Points:**
  - *(0:21–0:28)* Notice the constructor has the **exact same name as the class** and **no return type**. `this.` avoids confusion when the parameter and the field have the same name!

---

### Slide 6: Methods — What an Object *Does*
- **Slide Points:**
  - **Methods** are actions (functions) that belong to the class:
    ```java
    public class Employee {
        public String name;
        public String role;

        // Action that prints something (void = returns nothing)
        public void introduce() {
            System.out.println("Hi, I'm " + name + " (" + role + ")");
        }
    }
    ```
  - Calling the method on an object:
    ```java
    emp1.introduce(); // Hi, I'm Alice (Developer)
    ```
- **Talking Points:**
  - *(0:28–0:34)* Fields = **nouns** (what it *has*). Methods = **verbs** (what it *does*).

---

### Slide 7: Pillar 1 — Encapsulation (`public` vs. `private`)
- **Slide Points:**
  - **Encapsulation** = Protecting data inside the class so nothing breaks it by accident.
  - **`public`:** Anyone anywhere can read or change it.
  - **`private`:** Hidden inside the class—only methods inside that class can touch it:
    ```java
    public class BankAccount {
        private double balance = 0; // locked!

        public void deposit(double amount) {
            if (amount > 0) {
                balance = balance + amount;
            }
        }
        public double getBalance() {
            return balance;
        }
    }
    ```
- **Talking Points:**
  - *(0:34–0:41)* If `balance` were `public`, anyone could write `account.balance = -9999`! Making it `private` forces everyone to go through `deposit()` where we can check rules.

---

### Slide 8: Pillar 2 — Inheritance (`extends`)
- **Slide Points:**
  - **Inheritance** = Reusing fields and methods from a **Parent** class in a **Child** class:
    ```java
    public class Person {
        public String name;
    }

    public class Employee extends Person {
        public String role; // automatically gets 'name' from Person!
    }
    ```
  - Use **`extends`** when one class *"is a"* type of another class (`Employee` **is a** `Person`).
- **Talking Points:**
  - *(0:41–0:47)* Instead of copy-pasting `name` and `age` into `Employee`, `Manager`, and `Customer`, put the shared parts in `Person` and let them `extends Person`!

---

### Slide 9: Pillar 3 — Polymorphism (`@Override`)
- **Slide Points:**
  - **Polymorphism** = *"Many forms"* — the **same method name** behaves differently depending on the object:
    ```java
    public class Animal {
        public void makeSound() { System.out.println("..."); }
    }

    public class Dog extends Animal {
        @Override
        public void makeSound() { System.out.println("Woof!"); }
    }

    public class Cat extends Animal {
        @Override
        public void makeSound() { System.out.println("Meow!"); }
    }
    ```
- **Talking Points:**
  - *(0:47–0:52)* You tell both a `Dog` and a `Cat` to `makeSound()`. Same command, different result!

---

### Slide 10: Pillar 4 — Abstraction (`interface`)
- **Slide Points:**
  - **Abstraction** = Hiding complex details and showing only **what** an object can do (like a TV remote).
  - An **`interface`** is a **contract** of method names without the code inside:
    ```java
    public interface Printable {
        void print(); // rule: any Printable class MUST have print()
    }

    public class Invoice implements Printable {
        public void print() {
            System.out.println("Printing invoice...");
        }
    }
    ```
- **Talking Points:**
  - *(0:52–0:56)* When you drive a car, you use the steering wheel and pedals—you don't need to know how the engine pistons work. That's Abstraction!

---

### Slide 11: The 4 Pillars of OOP — Quick Recap
- **Slide Points:**
  | Pillar | Plain English | Keyword / Tool |
  | :--- | :--- | :--- |
  | **1. Encapsulation** | Protect data inside the class | `private` + getters/setters |
  | **2. Inheritance** | Share code from parent to child | `extends` |
  | **3. Polymorphism** | Same method name, different behavior | `@Override` |
  | **4. Abstraction** | Hide complexity behind a simple contract | `interface` / `implements` |
- **Talking Points:**
  - *(0:56–0:58)* You don't need to memorize fancy definitions—just remember what problem each pillar solves!

---

### Slide 12: Quick Mini-Practice (5 Minutes)
- **Slide Points:**
  - Create a **`Car`** class with:
    1. Fields: `brand` (`String`), `speed` (`int`)
    2. A constructor `Car(String brand, int speed)`
    3. A method `drive()` that prints `"Toyota is driving at 80 km/h"`
    4. In `main()`, create **2 different `Car` objects** and call `drive()` on both!
- **Talking Points:**
  - *(0:58–1:00)* Open the **OOP Cheat Sheet HTML** in your browser for copy-pasteable examples and the solution!
