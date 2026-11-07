package com.finances.ems.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Base64;
import java.util.Map;

/**
 * Contrôleur pédagogique pour le Module Sécurité du Jour 5.
 *
 * Pourquoi ce contrôleur ?
 * Pendant les Jours 1 à 4, les endpoints /api/employees sont ouverts pour ne pas
 * bloquer l'apprentissage du CRUD.
 * Au Jour 5, ce endpoint permet de démontrer concrètement le flux d'authentification
 * Stateless (Login -> Jeton JWT -> Intercepteur HTTP Angular -> Header Authorization: Bearer).
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Sécurité (Atelier Jour 5)", description = "Démonstration de l'authentification par Jeton (Bearer Token)")
public class AuthDemoController {

    public record LoginRequest(String username, String password) {}

    public record LoginResponse(
            String token,
            String username,
            String fullName,
            String role,
            String expiresAt
    ) {}

    @PostMapping("/login")
    @Operation(summary = "Authentification de démonstration (Jour 5)")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.username() == null || request.password() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Identifiant et mot de passe requis"));
        }

        // Compte de démonstration pour l'atelier Jour 5 : admin / finances2026
        if ("admin".equalsIgnoreCase(request.username().trim()) && "finances2026".equals(request.password())) {
            String payload = "sub:admin|role:ADMIN_DRH|iat:" + Instant.now().getEpochSecond();
            String demoToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                    + Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes())
                    + ".demo-signature-sit-finances";

            return ResponseEntity.ok(new LoginResponse(
                    demoToken,
                    "admin",
                    "Administrateur DRH / SIT",
                    "ROLE_ADMIN",
                    Instant.now().plusSeconds(28800).toString()
            ));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Identifiants incorrects. Utilisez admin / finances2026 pour la démo."));
    }
}
