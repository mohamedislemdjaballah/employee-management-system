# ============================================================================
# Script de lancement rapide pour la démonstration en salle
# Usage : .\scripts\start-demo.ps1
# ============================================================================

$RepoRoot = Resolve-Path "$PSScriptRoot\.."

Write-Host "Démarrage du Frontend Angular sur http://localhost:4200 ..." -ForegroundColor Cyan
Start-Process powershell -ArgumentList "-NoExit", "-Command", "Set-Location '$RepoRoot\frontend'; npm start"

Write-Host ""
Write-Host "Une fenêtre PowerShell a été ouverte pour le serveur Angular (http://localhost:4200)." -ForegroundColor Green
Write-Host "Astuce : Si Spring Boot est lancé sur le port 8080, Angular s'y connectera automatiquement." -ForegroundColor Gray
