# ============================================================================
# Script to create and push the 5 Classroom Save-Points to GitHub
# Usage: .\scripts\setup-github-savepoints.ps1
# ============================================================================

Write-Host "Publishing Classroom Save-Point Branches to GitHub..." -ForegroundColor Cyan

$Branches = @(
    "step/day-1-architecture-rest",
    "step/day-2-orm-persistence",
    "step/day-3-frontend-spa",
    "step/day-4-fullstack-lifecycle",
    "step/day-5-security-jwt"
)

# 1. Commit any remaining changes to main
git add .
git commit -m "docs: add 5-day conceptual presentations, quick start guide, launchers, and live inspection manual" 2>$null

# 2. Push main
Write-Host "Pushing main branch to origin..." -ForegroundColor Yellow
git push origin main

# 3. Create and push each save-point branch
foreach ($branch in $Branches) {
    Write-Host "Creating & pushing checkpoint branch: $branch ..." -ForegroundColor Yellow
    git branch -f $branch main
    git push -u origin $branch --force
}

Write-Host ""
Write-Host "=====================================================================" -ForegroundColor Green
Write-Host " ALL 5 CLASSROOM SAVE-POINT BRANCHES PUBLISHED TO GITHUB!" -ForegroundColor Green
Write-Host " Students can switch to any checkpoint anytime with:" -ForegroundColor White
Write-Host "   git checkout step/day-1-architecture-rest" -ForegroundColor Cyan
Write-Host "   git checkout step/day-2-orm-persistence" -ForegroundColor Cyan
Write-Host "   git checkout step/day-3-frontend-spa" -ForegroundColor Cyan
Write-Host "   git checkout step/day-4-fullstack-lifecycle" -ForegroundColor Cyan
Write-Host "   git checkout step/day-5-security-jwt" -ForegroundColor Cyan
Write-Host "=====================================================================" -ForegroundColor Green
