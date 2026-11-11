# =====================================================================
# Developer Environment Automated Installation Script
# =====================================================================

Write-Host "Starting automated installation of development tools..." -ForegroundColor Cyan

# Array of applications with their exact WinGet package IDs
$apps = @(
    "Microsoft.VisualStudioCode",
    "Postman.Postman",
    "TablePlus.TablePlus",
    "Git.Git",
    "Docker.DockerDesktop"
)

foreach ($app in $apps) {
    Write-Host "Installing $app..." -ForegroundColor Yellow
    
    # Runs winget silently, automatically accepting agreements and source terms
    winget install --id $app --silent --accept-package-agreements --accept-source-agreements
    
    if ($?) {
        Write-Host "Successfully installed: $app" -ForegroundColor Green
    } else {
        Write-Host "Failed or skipped: $app" -ForegroundColor Red
    }
}

Write-Host "All installations completed! Please restart your terminal/PC to apply PATH variables." -ForegroundColor Cyan
