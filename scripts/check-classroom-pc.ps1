# ============================================================================
# Script de Diagnostic Poste Stagiaire / Formateur (Windows PowerShell)
# Formation : Développement Web Fullstack (Spring Boot 3 + PostgreSQL + Angular)
# Usage     : .\scripts\check-classroom-pc.ps1
# ============================================================================

Write-Host ""
Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host "  DIAGNOSTIC POSTE DE FORMATION (SPRING BOOT + POSTGRESQL + ANGULAR) " -ForegroundColor Cyan
Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host ""

# 1. Node.js
$nodeVer = node -v 2>$null
if ($LASTEXITCODE -eq 0 -and $nodeVer) {
    Write-Host "[OK] Node.js installe        : $nodeVer" -ForegroundColor Green
} else {
    Write-Host "[MANQUANT] Node.js           : Installez Node.js LTS (https://nodejs.org)" -ForegroundColor Red
}

# 2. NPM
$npmVer = npm -v 2>$null
if ($LASTEXITCODE -eq 0 -and $npmVer) {
    Write-Host "[OK] NPM installe            : v$npmVer" -ForegroundColor Green
} else {
    Write-Host "[MANQUANT] NPM               : Inclus avec l'installateur Node.js" -ForegroundColor Red
}

# 3. Java JDK (Verification version >= 17 pour Spring Boot 3)
$javaOut = cmd /c "java -version 2>&1"
if ($javaOut -match 'version "(\d+)\.?(\d+)?') {
    $major = [int]$Matches[1]
    if ($major -eq 1 -and $Matches[2]) {
        $major = [int]$Matches[2]
    }
    if ($major -ge 17) {
        Write-Host "[OK] Java JDK (>= 17)        : Detecte (Java $major)" -ForegroundColor Green
    } else {
        Write-Host "[ATTENTION] Java trop ancien : Java $major detecte. Spring Boot 3 requiert JDK 17 ou 21." -ForegroundColor Yellow
        Write-Host "   -> Commande rapide : winget install EclipseAdoptium.Temurin.21.JDK" -ForegroundColor Gray
    }
} else {
    Write-Host "[MANQUANT] Java JDK          : Installez JDK 21 (winget install EclipseAdoptium.Temurin.21.JDK)" -ForegroundColor Red
}

# 4. Maven ou Maven Wrapper
$mvnVer = mvn -v 2>$null
if ($LASTEXITCODE -eq 0 -and $mvnVer) {
    Write-Host "[OK] Apache Maven            : Installe sur le PATH" -ForegroundColor Green
} elseif (Test-Path "$PSScriptRoot\..\backend\mvnw.cmd") {
    Write-Host "[OK] Maven Wrapper (mvnw)    : Present dans backend\mvnw.cmd (telechargement auto)" -ForegroundColor Green
} else {
    Write-Host "[MANQUANT] Maven             : winget install Apache.Maven" -ForegroundColor Yellow
}

# 5. PostgreSQL ou Docker
$psqlVer = psql --version 2>$null
$dockerVer = docker -v 2>$null
if ($psqlVer) {
    Write-Host "[OK] PostgreSQL local        : $psqlVer" -ForegroundColor Green
} elseif ($dockerVer) {
    Write-Host "[OK] Docker disponible       : $dockerVer (utilisez 'docker compose up -d' pour PostgreSQL)" -ForegroundColor Green
} else {
    Write-Host "[INFO] PostgreSQL non detecte: Vous pouvez installer PostgreSQL 16 OU utiliser le profil H2 en memoire" -ForegroundColor Yellow
}

# 6. Verification du cache node_modules Frontend
if (Test-Path "$PSScriptRoot\..\frontend\node_modules") {
    Write-Host "[OK] Dependances Angular     : frontend\node_modules deja installe (pret hors-ligne)" -ForegroundColor Green
} else {
    Write-Host "[A FAIRE] Dependances Angular: Lancez 'cd frontend && npm install'" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host "  Pour lancer Angular maintenant : cd frontend ; npm start" -ForegroundColor White
Write-Host "  Pour lancer Spring Boot        : cd backend  ; .\mvnw.cmd spring-boot:run" -ForegroundColor White
Write-Host "=====================================================================" -ForegroundColor Cyan
Write-Host ""
