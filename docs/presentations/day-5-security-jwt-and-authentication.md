# Day 5 Presentation: Enterprise Security & Stateless Authentication (JWT)
**Target Audience:** Senior Enterprise Engineers  
**Duration:** 1 Day (Morning: Authentication Paradigms & JWT / Afternoon: Interceptors, CORS & Capstone Lab)

---

## Slide 1: Welcome to Day 5 — Enterprise Security & Authentication
- **The Culmination:** We understand decoupled architectures, REST APIs, ORM persistence, and frontend SPAs.
- **The Core Question:** How do we secure an API when the client runs in a browser on port 4200 and the server runs on port 8080?
- **Today's Agenda:**
  1. Stateful Session Authentication (Cookies / Sessions) vs Stateless Token Authentication (JWT).
  2. The Anatomy of a JSON Web Token (Header, Claims Payload, Cryptographic Signature).
  3. HTTP Interceptors: Centralizing Bearer tokens on the wire.
  4. CORS (Cross-Origin Resource Sharing): Why the browser blocks cross-port requests and how to configure it.
  5. Capstone Exercise: Demonstrating end-to-end token flow with Chrome DevTools.

---

## Slide 2: Stateful Sessions vs. Stateless Tokens

```mermaid
flowchart TD
    subgraph Stateful["1. Stateful Session (Monolithic JSP/PHP)"]
        direction TB
        B1["Browser"] -->|"Login credentials"| S1["Server"]
        S1 -->|"Creates Session in RAM & sends Cookie"| B1
        B1 -->|"Sends Cookie: JSESSIONID=abc123"| S1
        Note1["Problem: Server memory stores every active session.<br/>Hard to scale across multiple server instances."]
    end

    subgraph Stateless["2. Stateless Token / JWT (Decoupled & Modern Web)"]
        direction TB
        B2["Browser SPA"] -->|"POST /api/auth/login"| S2["Auth Server"]
        S2 -->|"Signs & returns Token (JWT)"| B2
        B2 -->|"Sends Header: Authorization: Bearer <token>"| S2
        Note2["Advantage: Server stores NOTHING in RAM.<br/>Server validates signature mathematically on every call!"]
    end
```

### Why Decoupled Systems Avoid Server Sessions:
- In decoupled enterprise systems (and load-balanced server clusters), server 1 should not need to share RAM session state with server 2.
- A **Stateless Token** is self-contained: it carries all necessary identity data inside itself.

---

## Slide 3: Anatomy of a JSON Web Token (JWT)

A JWT looks like a long string of text with two dots separating three parts:
`xxxxx.yyyyy.zzzzz`

```mermaid
flowchart LR
    Token["eyJhbGciOiJIUzI1Ni... . eyJzdWIiOiJhZG1pbi... . 4e1f7a8b..."]
    Header["1. Header (Red)<br/>Algorithm: HS256<br/>Type: JWT"]
    Payload["2. Payload (Purple)<br/>User Claims:<br/>sub: 'admin'<br/>role: 'ROLE_ADMIN'<br/>exp: 1731189600"]
    Signature["3. Signature (Blue)<br/>HMACSHA256(<br/>Header + Payload, secretKey)"]

    Token --> Header
    Token --> Payload
    Token --> Signature
```

### Part 1: Header
Contains metadata about the token (e.g. `{"alg": "HS256", "typ": "JWT"}`).

### Part 2: Payload (Claims)
Contains user data (e.g. `sub: "admin"`, `role: "ROLE_ADMIN"`, expiration timestamp).
> **Security Note:** The payload is Base64 encoded, **not encrypted**! Anyone can read it. Never put passwords or confidential secrets inside a JWT payload.

### Part 3: Signature
The server computes: `HMACSHA256(Base64(Header) + "." + Base64(Payload), SecretKey)`.
If a hacker tampers with their username or role in the payload, the signature becomes invalid, and Spring Boot immediately rejects the request with `401 Unauthorized`!

---

## Slide 4: Where Does the Frontend Store the Token?

In modern web applications, the token is stored in browser memory:

1. **`sessionStorage` (Recommended for sensitive enterprise tabs):**
   - Cleared automatically when the user closes the browser tab.
   - Isolated to the current tab.
2. **`localStorage`:**
   - Persists even after closing and reopening the browser.

Open [`frontend/src/app/services/auth.service.ts`](../../frontend/src/app/services/auth.service.ts) in VS Code:
```typescript
login(username, password) {
  return this.http.post<AuthSession>(`${environment.apiUrl}/auth/login`, { username, password })
    .pipe(
      tap((session) => {
        sessionStorage.setItem('sit_ems_auth_session', JSON.stringify(session));
      })
    );
}
```

---

## Slide 5: The Magic of HTTP Interceptors

> **The Dilemma:** If we have 40 different REST API calls in our application, do we have to manually add the token header to all 40 calls?
> **Answer: NO! We use an HTTP Interceptor.**

Open [`frontend/src/app/interceptors/auth.interceptor.ts`](../../frontend/src/app/interceptors/auth.interceptor.ts):

```typescript
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  // If a JWT token exists in sessionStorage, attach it automatically:
  if (token) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(clonedRequest);
  }

  return next(req);
};
```

### How it Works:
- Every outgoing HTTP request passes through this pipeline.
- The interceptor automatically clones the request, injects the header `Authorization: Bearer <token>`, and forwards it to Spring Boot!

---

## Slide 6: Understanding CORS (Cross-Origin Resource Sharing)

> **The #1 Error Every Beginner Encounters:**
> *"Access to XMLHttpRequest at 'http://localhost:8080/api/employees' from origin 'http://localhost:4200' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource."*

### Why Does This Happen?
- The browser enforces the **Same-Origin Policy** for security.
- Origin = `Protocol` (`http`) + `Host` (`localhost`) + `Port` (`4200` vs `8080`).
- Because port 4200 $\neq$ port 8080, the browser blocks JavaScript from reading the response unless Spring Boot explicitly permits it!

### The Solution in Spring Boot:
Open [`backend/src/main/java/com/finances/ems/config/CorsConfig.java`](../../backend/src/main/java/com/finances/ems/config/CorsConfig.java):
```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
```

---

## Slide 7: Live Demonstration — Watching the Token on the Wire

1. Open `http://localhost:4200`.
2. Click **Atelier Sécurité (Jour 5)** in the navbar.
3. Open Chrome DevTools (`F12`) $\rightarrow$ **Network** tab.
4. Click **Obtenir un Jeton JWT** (`admin` / `finances2026`).
   - Observe the `POST /api/auth/login` request.
   - Inspect the response JSON containing the `token`.
5. Click **Tester un appel API avec ce jeton**:
   - Inspect the new `GET /api/employees` request.
   - Scroll to **Request Headers**:
   - Observe: `Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`
   - Notice that `auth.interceptor.ts` did this automatically!

---

## Slide 8: Course Wrap-Up & Key Takeaways

1. **Decoupled Architecture:** Clean boundary between the browser UI (Angular) and the server logic (Spring Boot).
2. **REST API Contract:** Resources, HTTP verbs, status codes, and living OpenAPI / Swagger documentation.
3. **ORM (Hibernate/JPA):** Eliminates repetitive SQL strings and maps relations directly into Java object models.
4. **Execution Models (Sync vs Async):** Java uses multi-threaded blocking I/O; JavaScript uses a single-threaded event loop and non-blocking Observables.
5. **Stateless Security:** JWT tokens carry identity claims across the network without burdening server RAM.
