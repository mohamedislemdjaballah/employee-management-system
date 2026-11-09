@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.
@REM ----------------------------------------------------------------------------
@echo off
setlocal

:: Auto-detect JDK 21 if JAVA_HOME is not set or points to an old JRE
if not defined JAVA_HOME (
    for /d %%D in ("C:\Program Files\Eclipse Adoptium\jdk-21*") do (
        if exist "%%D\bin\javac.exe" (
            set "JAVA_HOME=%%D"
            set "PATH=%%D\bin;%PATH%"
        )
    )
)

set WRAPPER_DIR=%~dp0
set MAVEN_VERSION=3.9.9
set MAVEN_HOME_DIR=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%
set MAVEN_BIN=%MAVEN_HOME_DIR%\apache-maven-%MAVEN_VERSION%\bin\mvn.cmd

where mvn >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    mvn %*
    exit /b %ERRORLEVEL%
)

if exist "%MAVEN_BIN%" (
    call "%MAVEN_BIN%" %*
    exit /b %ERRORLEVEL%
)

echo [Maven Wrapper] Telechargement automatique d'Apache Maven %MAVEN_VERSION%...
powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "$ErrorActionPreference = 'Stop'; " ^
  "$url = 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip'; " ^
  "$destDir = '%MAVEN_HOME_DIR%'; " ^
  "New-Item -ItemType Directory -Force -Path $destDir | Out-Null; " ^
  "$zipPath = Join-Path $destDir 'maven.zip'; " ^
  "Invoke-WebRequest -Uri $url -OutFile $zipPath; " ^
  "Expand-Archive -Path $zipPath -DestinationPath $destDir -Force; " ^
  "Remove-Item $zipPath -Force"

if exist "%MAVEN_BIN%" (
    call "%MAVEN_BIN%" %*
    exit /b %ERRORLEVEL%
) else (
    echo [ERREUR] Impossible de telecharger Maven automatiquement. Verifiez votre connexion Internet.
    exit /b 1
)
