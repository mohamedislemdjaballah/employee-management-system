-- ============================================================================
-- Script SQL de référence (Jour 1 - Formation Ministère des Finances / SIT)
-- Base de données : PostgreSQL (ems_db)
-- Note : Spring Boot (Hibernate JPA) peut générer ces tables automatiquement
-- via "spring.jpa.hibernate.ddl-auto=update" et "DataInitializer.java",
-- mais ce script permet de montrer le schéma SQL explicite aux ingénieurs.
-- ============================================================================

-- 1. Création de la table des Directions / Services (departments)
CREATE TABLE IF NOT EXISTS departments (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL UNIQUE,
    location VARCHAR(120),
    description VARCHAR(255)
);

-- 2. Création de la table des Agents / Employés (employees)
CREATE TABLE IF NOT EXISTS employees (
    id BIGSERIAL PRIMARY KEY,
    matricule VARCHAR(30) NOT NULL UNIQUE,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(30),
    job_title VARCHAR(120) NOT NULL,
    salary NUMERIC(12, 2) NOT NULL CHECK (salary >= 0),
    hire_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE', -- ACTIVE, ON_LEAVE, INACTIVE
    department_id BIGINT NOT NULL,
    CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id)
        REFERENCES departments (id)
        ON DELETE RESTRICT
);

-- 3. Données initiales de démonstration (Directions du Ministère des Finances)
INSERT INTO departments (code, name, location, description) VALUES
    ('SIT', 'Système d''Information du Trésor', 'Bâtiment A - 3ème Étage', 'Modernisation et développement des applications du Trésor'),
    ('DGT', 'Direction Générale du Trésor', 'Bâtiment Central - 1er Étage', 'Gestion de la trésorerie et comptabilité publique'),
    ('DB',  'Direction du Budget', 'Bâtiment B - 2ème Étage', 'Préparation et suivi de l''exécution budgétaire'),
    ('DRH', 'Direction des Ressources Humaines', 'Bâtiment Central - RDC', 'Gestion des carrières, de la paie et de la formation')
ON CONFLICT (code) DO NOTHING;
