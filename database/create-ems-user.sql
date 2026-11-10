-- ============================================================================
-- SQL Script to create the application user and database in PostgreSQL
-- Run this connected as superuser 'postgres' in TablePlus, pgAdmin, or psql
-- ============================================================================

-- 1. Create the user ems_user with password ems_password
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'ems_user') THEN
        CREATE ROLE ems_user WITH LOGIN PASSWORD 'ems_password' CREATEDB;
    ELSE
        ALTER ROLE ems_user WITH PASSWORD 'ems_password';
    END IF;
END
$$;

-- 2. Create the database ems_db owned by ems_user
SELECT 'CREATE DATABASE ems_db OWNER ems_user'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'ems_db')\gexec

-- 3. Grant privileges
GRANT ALL PRIVILEGES ON DATABASE ems_db TO ems_user;
\connect ems_db
GRANT ALL ON SCHEMA public TO ems_user;
