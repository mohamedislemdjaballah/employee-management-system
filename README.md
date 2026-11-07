# Portail de Gestion des Agents (Spring Boot 3 + PostgreSQL + Angular 18)

Application de démonstration complète et support pédagogique pour la formation **Thème 2 : Développement WEB (Ministère des Finances / Système d'Information du Trésor - SIT)**.

---

## 📚 Documentation & Supports de Formation (`docs/`)

| Document | Public Cible | Langue | Contenu |
| :--- | :--- | :--- | :--- |
| **[00-instructor-prep-guide.md](docs/00-instructor-prep-guide.md)** | **Vous & Votre Collègue** | 🇬🇧 Anglais | Planning de préparation sur 5 jours (8h/j), antisèches **Laravel $\rightarrow$ Angular** et **Django $\rightarrow$ Spring Boot**, et checklist d'installation des 7 PC stagiaires. |
| **[01-architecture-for-legacy-engineers.md](docs/01-architecture-for-legacy-engineers.md)** | **Formateurs & Stagiaires** | 🇬🇧/🇫🇷 Bilingue | Comment expliquer l'architecture découplée (REST/JSON), l'Injection de Dépendances et Monolithe Modulaire vs Microservices à des ingénieurs expérimentés. |
| **[02-backend-spring-boot-guide.md](docs/02-backend-spring-boot-guide.md)** | **Collègue (Jours 1–2)** | 🇫🇷 Français | Guide pas-à-pas du Backend Spring Boot 3, JPA/Hibernate, PostgreSQL, Validation `@Valid`, CORS et Swagger UI. |
| **[03-frontend-angular-guide.md](docs/03-frontend-angular-guide.md)** | **Vous (Jours 3–5)** | 🇬🇧/🇫🇷 Bilingue | Votre script d'enseignement Angular ligne par ligne avec parallèles **Laravel** (Blade, Service Container, FormRequest, Middleware). |
| **[04-5day-student-lab-manual.md](docs/04-5day-student-lab-manual.md)** | **Les 7 Stagiaires** | 🇫🇷 Français | Support de Travaux Pratiques (TP) complet sur 5 jours avec exercices progressifs et atelier Sécurité JWT du Jour 5. |

---

## 🏗️ Architecture du Projet

```text
demo-employee-management/
├── docker-compose.yml          # PostgreSQL 16 (port 5432) + pgAdmin 4 (port 5050)
├── database/
│   └── init.sql                # Script SQL de référence (tables departments & employees)
├── backend/                    # API REST Spring Boot 3 (port 8080)
│   ├── pom.xml
│   └── src/main/java/com/finances/ems/
│       ├── entity/             # @Entity (Department, Employee, EmployeeStatus)
│       ├── repository/         # @Repository (Spring Data JPA)
│       ├── dto/                # Records Java (Validation @NotBlank, @Email)
│       ├── service/            # @Service (Logique métier & transactions)
│       ├── controller/         # @RestController (/api/employees, /api/departments, /api/auth)
│       ├── exception/          # @RestControllerAdvice (Erreurs JSON propres)
│       └── config/             # CORS (port 4200) & DataInitializer (Seed automatique)
└── frontend/                   # Application SPA Angular 18 Standalone (port 4200)
    └── src/app/
        ├── models/             # Interfaces TypeScript (Employee, Department)
        ├── services/           # @Injectable (HttpClient + Mode Démo Hors-Ligne automatique)
        ├── interceptors/       # Intercepteur HTTP JWT (Atelier Sécurité Jour 5)
        └── components/
            ├── navbar/         # Barre de navigation MF / SIT
            ├── employee-list/  # Tableau, KPIs, Recherche & Filtres multicritères
            ├── employee-form/  # Formulaire Réactif (Création & Modification)
            └── login-demo/     # Écran de démonstration JWT (Jour 5)
```

---

## 🚀 Démarrage Rapide

### 1. Démarrer le Frontend Angular (Fonctionne immédiatement, même sans Backend !)
> **Astuce pour votre préparation :** Le service Angular [`EmployeeService`](frontend/src/app/services/employee.service.ts) détecte automatiquement si Spring Boot est arrêté et bascule sur un jeu de données en mémoire. Vous pouvez donc tester et enseigner Angular de manière 100% autonome.

```powershell
cd frontend
npm install
npm start
```
- Ouvrez **http://localhost:4200** dans votre navigateur.

### 2. Démarrer PostgreSQL (Option A : Docker / Option B : PostgreSQL Local)
- **Avec Docker :**
  ```powershell
  docker compose up -d
  ```
- **Avec PostgreSQL installé sur Windows :**
  Créez une base `ems_db` (utilisateur `ems_user` / mot de passe `ems_password`), ou modifiez `backend/src/main/resources/application.yml`.
- **Mode de secours sans PostgreSQL (Base en mémoire H2) :**
  Si PostgreSQL n'est pas disponible sur un poste stagiaire, lancez Spring Boot avec le profil `h2` !

### 3. Démarrer le Backend Spring Boot (Java 17 ou 21 requis)
```powershell
cd backend
mvn spring-boot:run
```
*(Ou sans PostgreSQL : `mvn spring-boot:run -Dspring-boot.run.profiles=h2`)*

- **API REST Employés :** http://localhost:8080/api/employees
- **API REST Directions :** http://localhost:8080/api/departments
- **Documentation interactive Swagger UI :** http://localhost:8080/swagger-ui.html
