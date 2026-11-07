# Support de Travaux Pratiques (TP) — 5 Jours
## Développement d'une Application Web Fullstack Découplée (Spring Boot 3, PostgreSQL & Angular 18)
**Cadre :** Modernisation des outils de développement — Ministère des Finances / Système d'Information du Trésor (SIT)

---

## Programme Récapitulatif des 5 Jours

| Jour | Thématique Principale | Livrable de fin de journée |
| :--- | :--- | :--- |
| **Jour 1** | Architecture Découplée, SGBD PostgreSQL & Entités/Repositories Spring Boot | Base PostgreSQL `ems_db` opérationnelle + Couche d'accès aux données JPA |
| **Jour 2** | Services Métiers, DTOs, Contrôleurs REST, Validation & Swagger UI | API REST complète sur `http://localhost:8080/api/employees` testée avec Swagger |
| **Jour 3** | Fondamentaux TypeScript, Architecture Angular 18, Composants & Services | Application Angular sur `http://localhost:4200` affichant les KPIs et l'annuaire |
| **Jour 4** | Intégration HTTP REST, Routage SPA & Formulaires Réactifs (Création / Édition) | CRUD complet de bout en bout (Angular $\leftrightarrow$ Spring Boot $\leftrightarrow$ PostgreSQL) |
| **Jour 5** | Sécurité des APIs REST (JWT / Intercepteur) & Atelier Pratique d'Évolution | Authentification par jeton + Ajout d'une nouvelle fonctionnalité de bout en bout |

---

## JOUR 1 : Architecture Découplée, PostgreSQL & Socle Spring Boot

### TP 1.1 — Préparation de la Base de Données PostgreSQL
1. Ouvrez **pgAdmin 4** (ou utilisez Docker : `docker compose up -d`).
2. Créez une base de données nommée `ems_db`.
3. Ouvrez l'outil de requête (*Query Tool*) et inspectez le script [`database/init.sql`](../database/init.sql).
4. Observez la relation entre la table `departments` (Directions du Ministère : `SIT`, `DGT`, `DB`, `DRH`) et la table `employees` (`department_id` en clé étrangère).

### TP 1.2 — Découverte et Configuration du Projet Spring Boot
1. Ouvrez le dossier [`backend/`](../backend/) dans votre IDE (IntelliJ IDEA).
2. Inspectez le fichier [`pom.xml`](../backend/pom.xml) et repérez les 4 starters principaux :
   - `spring-boot-starter-web`
   - `spring-boot-starter-data-jpa`
   - `spring-boot-starter-validation`
   - `postgresql`
3. Ouvrez [`src/main/resources/application.yml`](../backend/src/main/resources/application.yml) et vérifiez les paramètres de connexion (`url`, `username`, `password`).
   > *Astuce : Si PostgreSQL n'est pas démarré sur votre poste, vous pouvez lancer l'application avec le profil en mémoire `h2`.*

### TP 1.3 — Création du Modèle JPA (`@Entity`) et des Repositories (`@Repository`)
1. Examinez les classes [`Department.java`](../backend/src/main/java/com/finances/ems/entity/Department.java) et [`Employee.java`](../backend/src/main/java/com/finances/ems/entity/Employee.java).
   - Repérez les annotations `@Entity`, `@Table`, `@Id`, `@GeneratedValue` et `@ManyToOne`.
2. Examinez [`EmployeeRepository.java`](../backend/src/main/java/com/finances/ems/repository/EmployeeRepository.java).
   - Observez comment l'héritage de `JpaRepository<Employee, Long>` fournit automatiquement toutes les opérations CRUD SQL.
   - Analysez la requête multicritères `@Query` dans la méthode `searchEmployees(...)`.

---

## JOUR 2 : API REST Spring Boot, Validation, Gestion des Erreurs & Swagger

### TP 2.1 — Structuration par DTOs (`record`) et Validation (`@Valid`)
1. Ouvrez [`EmployeeRequestDto.java`](../backend/src/main/java/com/finances/ems/dto/EmployeeRequestDto.java) et [`EmployeeResponseDto.java`](../backend/src/main/java/com/finances/ems/dto/EmployeeResponseDto.java).
2. Identifiez les règles de contrôle déclaratives :
   - `@NotBlank(message = "...")`
   - `@Email(message = "...")`
   - `@DecimalMin(value = "0.0", ...)`
3. **Question de réflexion :** Pourquoi ne renvoie-t-on pas directement l'objet `@Entity Employee` au navigateur ? *(Réponse : découplage entre le schéma physique SQL et le contrat JSON public).*

### TP 2.2 — Implémentation du Service Métier et du Contrôleur REST
1. Dans [`EmployeeService.java`](../backend/src/main/java/com/finances/ems/service/EmployeeService.java), observez comment la méthode `createEmployee()` vérifie l'unicité du matricule et de l'email avant d'appeler `employeeRepository.save()`.
2. Dans [`EmployeeController.java`](../backend/src/main/java/com/finances/ems/controller/EmployeeController.java), repérez le mapping entre les verbes HTTP et les méthodes Java :
   - `@GetMapping` $\rightarrow$ Consultation
   - `@PostMapping` $\rightarrow$ Création (code `201 Created`)
   - `@PutMapping("/{id}")` $\rightarrow$ Modification
   - `@DeleteMapping("/{id}")` $\rightarrow$ Suppression (code `204 No Content`)

### TP 2.3 — Test Interactif de l'API avec Swagger UI & Configuration CORS
1. Démarrez l'application Spring Boot (`EmsApplication.java`).
2. Ouvrez votre navigateur sur **http://localhost:8080/swagger-ui.html**.
3. Testez l'endpoint `GET /api/employees` : vérifiez que les 6 agents initiaux (créés par `DataInitializer.java`) sont retournés au format JSON.
4. Testez `POST /api/employees` avec un email invalide : observez la réponse `400 Bad Request` générée par [`GlobalExceptionHandler.java`](../backend/src/main/java/com/finances/ems/exception/GlobalExceptionHandler.java).
5. Ouvrez [`CorsConfig.java`](../backend/src/main/java/com/finances/ems/config/CorsConfig.java) et comprenez pourquoi l'origine `http://localhost:4200` doit être explicitement autorisée.

---

## JOUR 3 : Prise en Main d'Angular 18, Composants & Services

### TP 3.1 — Démarrage et Structure d'une Application Angular (SPA)
1. Ouvrez un terminal dans le dossier [`frontend/`](../frontend/) et lancez :
   ```powershell
   npm install
   npm start
   ```
2. Ouvrez **http://localhost:4200**.
3. Inspectez [`src/index.html`](../frontend/src/index.html), [`src/main.ts`](../frontend/src/main.ts) et [`src/app/app.component.ts`](../frontend/src/app/app.component.ts).
4. Repérez la balise `<router-outlet></router-outlet>` qui permet de changer d'écran sans jamais recharger la page web.

### TP 3.2 — Contrats TypeScript & Services Injectables (`@Injectable`)
1. Comparez [`frontend/src/app/models/employee.model.ts`](../frontend/src/app/models/employee.model.ts) avec la classe Java `EmployeeResponseDto.java`.
2. Ouvrez [`frontend/src/app/services/employee.service.ts`](../frontend/src/app/services/employee.service.ts).
3. Observez comment `HttpClient` est injecté via `inject(HttpClient)` et comment chaque méthode retourne un `Observable`.

### TP 3.3 — Le Composant Liste des Agents (`EmployeeListComponent`)
1. Ouvrez [`employee-list.component.ts`](../frontend/src/app/components/employee-list/employee-list.component.ts) et [`employee-list.component.html`](../frontend/src/app/components/employee-list/employee-list.component.html).
2. Repérez dans le template HTML :
   - Les expressions `{{ totalEmployees }}` et le pipe de formatage `{{ emp.salary | number:'1.2-2' }}`.
   - Les blocs conditionnels `@if (isLoading) { ... } @else { ... }`.
   - La boucle `@for (emp of employees; track emp.id) { ... }`.
   - La liaison bidirectionnelle `[(ngModel)]="searchTerm"` sur le champ de recherche.

---

## JOUR 4 : Formulaires Réactifs, Routage & Intégration Complète

### TP 4.1 — Configuration du Routeur Angular
1. Ouvrez [`src/app/app.routes.ts`](../frontend/src/app/app.routes.ts).
2. Observez comment les routes `/employees/new` et `/employees/:id/edit` pointent toutes les deux vers le composant `EmployeeFormComponent`.

### TP 4.2 — Création et Modification avec `ReactiveFormsModule`
1. Ouvrez [`employee-form.component.ts`](../frontend/src/app/components/employee-form/employee-form.component.ts) et [`employee-form.component.html`](../frontend/src/app/components/employee-form/employee-form.component.html).
2. Étudiez la méthode `initForm()` qui déclare le `FormGroup` et les validateurs (`Validators.required`, `Validators.email`, `Validators.min(0)`).
3. Étudiez comment `this.route.snapshot.paramMap.get('id')` permet de détecter si l'utilisateur est en mode **Création** ou en mode **Modification**.
4. Testez dans le navigateur :
   - Cliquez sur **+ Ajouter un Agent**, laissez le champ Email vide et cliquez sur **Créer l'agent** $\rightarrow$ observez la validation immédiate côté client.
   - Créez un agent avec le matricule `FIN-2026-001` (qui existe déjà) $\rightarrow$ observez le message d'erreur `409 Conflict` renvoyé par Spring Boot et affiché dans le bandeau rouge Angular !

---

## JOUR 5 : Sécurité (JWT) & Atelier d'Évolution de Bout en Bout

### TP 5.1 — Atelier Sécurité : Authentification Stateless & Intercepteur HTTP
1. Dans l'application Angular, cliquez sur l'onglet **Atelier Sécurité (Jour 5)** (`http://localhost:4200/login-demo`).
2. Ouvrez les outils de développement du navigateur (**Touche F12 $\rightarrow$ Onglet Réseau / Network**).
3. Connectez-vous avec le compte de démonstration (`admin` / `finances2026`).
4. Inspectez le jeton JWT reçu depuis [`AuthDemoController.java`](../backend/src/main/java/com/finances/ems/controller/AuthDemoController.java).
5. Retournez sur **Annuaire des Agents** et observez dans l'onglet Réseau (F12) la requête `GET http://localhost:8080/api/employees` : vérifiez que l'en-tête `Authorization: Bearer ...` a été ajouté automatiquement par [`auth.interceptor.ts`](../frontend/src/app/interceptors/auth.interceptor.ts).

---

### TP 5.2 — Exercice Récapitulatif de Fin de Formation (Fil Rouge)
**Objectif :** Réaliser en autonomie (ou en binôme) une évolution complète traversant les 3 couches (**PostgreSQL $\rightarrow$ Spring Boot $\rightarrow$ Angular**).

#### Sujet de l'exercice : Ajouter le « Grade / Échelon » (`grade`) à la fiche Agent
1. **Étape 1 (Backend - Entité & DTO) :**
   - Dans `Employee.java`, ajoutez un attribut `private String grade;` (ex: `"Ingénieur Principal"`, `"Inspecteur"`, `"Administrateur"`).
   - Ajoutez `String grade` dans `EmployeeRequestDto.java` et `EmployeeResponseDto.java`.
   - Mettez à jour `EmployeeService.java` (`applyDtoToEntity` et `toResponseDto`).
2. **Étape 2 (Vérification Swagger) :**
   - Redémarrez Spring Boot et vérifiez sur `http://localhost:8080/swagger-ui.html` que le champ `grade` apparaît dans le JSON.
3. **Étape 3 (Frontend Angular) :**
   - Ajoutez `grade?: string;` dans `employee.model.ts`.
   - Ajoutez une colonne `Grade` dans `employee-list.component.html`.
   - Ajoutez le champ `grade` dans le `FormGroup` de `employee-form.component.ts` et un `<input formControlName="grade">` dans `employee-form.component.html`.
4. **Validation finale :** Créez et modifiez un agent avec son grade depuis Angular et vérifiez sa persistance dans PostgreSQL !
