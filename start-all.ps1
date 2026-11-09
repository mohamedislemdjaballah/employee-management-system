# ============================================================================
# One-Click PowerShell Launcher for Classroom & Demo
# Usage: .\start-all.ps1
# ============================================================================

$RepoDir = Resolve-Path "$PSScriptRoot"

Write-Host ""
Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host "   EMPLOYEE MANAGEMENT SYSTEM - ONE-CLICK LAUNCHER (PowerShell)       " -ForegroundColor Cyan
Write-Host "   Spring Boot (Port 8080) + Angular (Port 4200) + PostgreSQL (5432) " -ForegroundColor Cyan
Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host ""

# 1. Docker / Database check
$dockerCheck = Get-Command docker -ErrorAction SilentlyContinue
if ($dockerCheck) {
    Write-Host "[1/3] Starting PostgreSQL container (docker compose up -d)..." -ForegroundColor Yellow
    docker compose -f "$RepoDir\docker-compose.yml" up -d
} else {
    Write-Host "[1/3] Docker not detected. Using local PostgreSQL or H2 in-memory." -ForegroundColor Yellow
}

# 2. Start Backend in separate window
Write-Host "[2/3] Starting Spring Boot Backend on http://localhost:8080 ..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "Set-Location '$RepoDir\backend'; .\mvnw.cmd spring-boot:run"

# 3. Start Frontend in separate window
Write-Host "[3/3] Starting Angular Frontend on http://localhost:4200 ..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "Set-Location '$RepoDir\frontend'; npm start"

Write-Host ""
Write-Host "=====================================================================" -ForegroundColor Green
Write-Host " SERVICES LAUNCHED IN DEDICATED WINDOWS:" -ForegroundColor Green
Write-Host "  - Angular Frontend:  http://localhost:4200" -ForegroundColor White
Write-Host "  - REST API Swagger:  http://localhost:8080/swagger-ui.html" -ForegroundColor White
Write-Host "  - TablePlus Connect: localhost:5432 (ems_db / ems_user / ems_password)" -ForegroundColor White
Write-Host "=====================================================================" -ForegroundColor Green
Write-Host ""
