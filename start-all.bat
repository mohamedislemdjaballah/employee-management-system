@echo off
title Employee Management System - Quick Launcher
color 0A
cls
echo =====================================================================
echo    EMPLOYEE MANAGEMENT SYSTEM - ONE-CLICK LAUNCHER
echo    Spring Boot 3 (Port 8080) + Angular 18 (Port 4200) + PostgreSQL (5432)
echo =====================================================================
echo.

set REPO_DIR=%~dp0

:: 1. Check/Start Docker PostgreSQL container if Docker is available
docker --version >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo [1/3] Starting PostgreSQL container via Docker Compose...
    cd /d "%REPO_DIR%"
    docker compose up -d
) else (
    echo [1/3] Docker not detected. Assuming local PostgreSQL is running on port 5432
    echo       (or Spring Boot will use in-memory H2 database).
)

:: 2. Start Backend Spring Boot in a new window
echo [2/3] Launching Spring Boot Backend (http://localhost:8080)...
start "Spring Boot Backend (Port 8080)" cmd /k "cd /d "%REPO_DIR%backend" && (mvnw.cmd spring-boot:run || (echo If Java 21 is not installed, install Eclipse Temurin 21 && pause))"

:: 3. Start Frontend Angular in a new window
echo [3/3] Launching Angular Frontend (http://localhost:4200)...
start "Angular Frontend (Port 4200)" cmd /k "cd /d "%REPO_DIR%frontend" && npm start"

echo.
echo =====================================================================
echo  SERVICES STARTING UP IN SEPARATE WINDOWS:
echo   - Angular App:   http://localhost:4200
echo   - REST API:      http://localhost:8080/api/employees
echo   - Swagger UI:    http://localhost:8080/swagger-ui.html
echo   - TablePlus DB:  localhost:5432 (DB: ems_db, User: ems_user, Pass: ems_password)
echo =====================================================================
echo.
pause
