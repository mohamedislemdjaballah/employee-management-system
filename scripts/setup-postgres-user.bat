@echo off
title Setup PostgreSQL ems_user and ems_db
cls
echo =====================================================================
echo   CREATING 'ems_user' AND 'ems_db' IN LOCAL POSTGRESQL (Port 5432)
echo =====================================================================
echo.
echo Please enter your local PostgreSQL superuser ('postgres') password when prompted:
echo.

set PSQL_PATH=C:\Program Files\PostgreSQL\17\bin\psql.exe
if not exist "%PSQL_PATH%" (
    set PSQL_PATH=psql.exe
)

"%PSQL_PATH%" -U postgres -h localhost -p 5432 -f "%~dp0..\database\create-ems-user.sql"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo [SUCCESS] User 'ems_user' and database 'ems_db' created successfully!
) else (
    echo.
    echo [NOTE] If you prefer using TablePlus or pgAdmin, open TablePlus,
    echo        connect as user 'postgres', and run database\create-ems-user.sql.
)

echo.
pause
