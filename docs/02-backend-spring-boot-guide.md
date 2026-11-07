# Guide 02 : Volet Backend — Spring Boot 3 & PostgreSQL (Jours 1 & 2)

Ce guide détaille pas-à-pas l'architecture et le code du répertoire [`backend/`](../backend/) afin d'accompagner le formateur Backend et les 7 ingénieurs durant les **Jours 1 et 2**.

---

## 1. Configuration du Projet (`pom.xml` & `application.yml`)

### A. Le fichier [`backend/pom.xml`](../backend/pom.xml) (Gestionnaire Maven)
Dans l'écosystème Java/Spring Boot, **Maven** joue le rôle de gestionnaire de dépendances (comme `pip`/`requirements.txt` en Python ou `composer.json` en PHP).
Les 4 dépendances essentielles déclarées dans notre `pom.xml` sont :
1. **`spring-boot-starter-web`** : Embarque le serveur HTTP Tomcat (port `8080`), le convertisseur JSON Jackson et les annotations REST (`@RestController`, `@GetMapping`).
2. **`spring-boot-starter-data-jpa`** : Embarque Hibernate (ORM) et Spring Data JPA pour manipuler PostgreSQL via des objets Java sans écrire de plomberie JDBC.
3. **`spring-boot-starter-validation`** : Permet de valider les données entrantes avec `@NotBlank`, `@Email`, `@DecimalMin`.
4. **`postgresql`** : Le pilote JDBC officiel permettant à Java de se connecter au SGBD PostgreSQL sur le port `5432`.

### B. Le fichier [`application.yml`](../backend/src/main/resources/application.yml)
Ce fichier centralise toute la configuration technique :
- `spring.datasource.url: jdbc:postgresql://localhost:5432/ems_db` : Adresse de la base PostgreSQL.
- `spring.jpa.hibernate.ddl-auto: update` : Demande à Hibernate de créer ou mettre à jour automatiquement les tables `departments` et `employees` au démarrage si elles n'existent pas encore.
- `spring.jpa.show-sql: true` : Affiche dans la console toutes les requêtes SQL générées par Spring Boot (**très apprécié par les ingénieurs habitués au SQL !**).

---

## 2. Étape 1 : La Couche Données — Entités JPA (`@Entity`)

Ouvrez [`Department.java`](../backend/src/main/java/com/finances/ems/entity/Department.java) et [`Employee.java`](../backend/src/main/java/com/finances/ems/entity/Employee.java).

### Points clés à expliquer aux stagiaires :
1. **`@Entity` et `@Table(name = "employees")`** : Indiquent que chaque instance de la classe Java `Employee` correspond à une ligne de la table PostgreSQL `employees`.
2. **`@Id` et `@GeneratedValue(strategy = GenerationType.IDENTITY)`** : Correspondent au type PostgreSQL `BIGSERIAL PRIMARY KEY` (auto-incrémenté par la base de données).
3. **`@ManyToOne` et `@JoinColumn(name = "department_id")`** :
   ```java
   @ManyToOne(fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "department_id", nullable = false)
   private Department department;
   ```
   Plusieurs employés (`Many`) appartiennent à une même direction (`One`). En base de données, cela crée la clé étrangère `department_id` pointant vers `departments(id)`.

---

## 3. Étape 2 : La Couche d'Accès aux Données — `@Repository`

Ouvrez [`DepartmentRepository.java`](../backend/src/main/java/com/finances/ems/repository/DepartmentRepository.java) et [`EmployeeRepository.java`](../backend/src/main/java/com/finances/ems/repository/EmployeeRepository.java).

### La puissance de `JpaRepository<Employee, Long>`
C'est souvent le moment "waouh" du Jour 1 pour des ingénieurs qui écrivaient des centaines de lignes de `PreparedStatement` JDBC :
- Il suffit de déclarer une **interface** qui hérite de `JpaRepository<Employee, Long>`.
- Au démarrage, Spring Boot génère automatiquement la classe d'implémentation avec `findAll()`, `findById()`, `save()`, `deleteById()`, `count()`.
- **Requêtes dérivées du nom de méthode** :
  ```java
  boolean existsByMatriculeIgnoreCase(String matricule);
  ```
  Spring analyse le nom de la méthode et génère automatiquement la requête SQL :
  `SELECT count(*) > 0 FROM employees WHERE UPPER(matricule) = UPPER(?)` !
- **Requêtes personnalisées JPQL (`@Query`)** :
  Dans [`EmployeeRepository.java`](../backend/src/main/java/com/finances/ems/repository/EmployeeRepository.java), la méthode `searchEmployees(...)` montre comment écrire une requête multicritères propre avec des paramètres nommés (`:departmentId`, `:status`, `:search`).

---

## 4. Étape 3 : Pourquoi utiliser des DTOs (`record`) ?

Ouvrez [`EmployeeRequestDto.java`](../backend/src/main/java/com/finances/ems/dto/EmployeeRequestDto.java) et [`EmployeeResponseDto.java`](../backend/src/main/java/com/finances/ems/dto/EmployeeResponseDto.java).

### Pourquoi ne pas exposer directement l'entité `@Entity Employee` dans le contrôleur REST ?
1. **Sécurité & Découplage** : Si demain on renomme une colonne interne en base de données ou qu'on ajoute un champ sensible, on ne casse pas l'application Angular.
2. **Aplatissement JSON** : Au lieu d'envoyer un objet imbriqué complexe pour la direction, `EmployeeResponseDto` renvoie directement `departmentId`, `departmentCode` (`"SIT"`) et `departmentName` (`"Système d'Information du Trésor"`), ce qui simplifie énormément le tableau Angular.
3. **Validation Déclarative** : Sur `EmployeeRequestDto`, les annotations `@NotBlank`, `@Email`, `@DecimalMin` définissent le contrat d'entrée.

---

## 5. Étape 4 : La Couche Métier — `@Service` et `@Transactional`

Ouvrez [`EmployeeService.java`](../backend/src/main/java/com/finances/ems/service/EmployeeService.java).

- **Rôle** : C'est le cœur métier de l'application.
- Avant d'insérer un agent (`createEmployee`), le Service vérifie que le matricule et l'email n'existent pas déjà. S'ils existent, il lève une `DuplicateResourceException` (qui produira un code HTTP `409 Conflict`).
- L'annotation `@Transactional` garantit que toutes les opérations SQL de la méthode s'exécutent dans une transaction atomique PostgreSQL (`BEGIN ... COMMIT`, ou `ROLLBACK` automatique en cas d'exception).

---

## 6. Étape 5 : La Couche Exposition REST — `@RestController` & Swagger UI

Ouvrez [`EmployeeController.java`](../backend/src/main/java/com/finances/ems/controller/EmployeeController.java) et [`GlobalExceptionHandler.java`](../backend/src/main/java/com/finances/ems/exception/GlobalExceptionHandler.java).

| Verbe HTTP | URL | Méthode Java | Code HTTP Retourné |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/employees` | `getEmployees(departmentId, status, search)` | `200 OK` |
| `GET` | `/api/employees/{id}` | `getEmployeeById(id)` | `200 OK` (ou `404 Not Found`) |
| `POST` | `/api/employees` | `createEmployee(@Valid @RequestBody dto)` | `201 Created` (ou `400`/`409`) |
| `PUT` | `/api/employees/{id}` | `updateEmployee(id, @Valid @RequestBody dto)` | `200 OK` |
| `DELETE` | `/api/employees/{id}` | `deleteEmployee(id)` | `204 No Content` |

### Tester l'API sans une seule ligne d'Angular grâce à Swagger UI !
Au Jour 2 après-midi, faites ouvrir **http://localhost:8080/swagger-ui.html** à tous les stagiaires.
Ils verront l'interface graphique interactive générée automatiquement par Spring Boot et pourront tester `GET`, `POST`, `PUT` et `DELETE` en direct sur PostgreSQL avant même de démarrer le cours Angular du Jour 3 !
