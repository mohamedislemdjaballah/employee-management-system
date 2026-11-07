package com.finances.ems.controller;

import com.finances.ems.dto.EmployeeRequestDto;
import com.finances.ems.dto.EmployeeResponseDto;
import com.finances.ems.entity.EmployeeStatus;
import com.finances.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST (@RestController) exposant le CRUD complet des Employés / Agents.
 * URL de base : http://localhost:8080/api/employees
 *
 * Points pédagogiques :
 * - @GetMapping    -> Lecture (HTTP GET)
 * - @PostMapping   -> Création (HTTP POST + code 201 Created)
 * - @PutMapping    -> Modification (HTTP PUT)
 * - @DeleteMapping -> Suppression (HTTP DELETE + code 204 No Content)
 * - @Valid         -> Déclenche la validation automatique du DTO avant d'entrer dans la méthode
 */
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employés / Agents", description = "API CRUD complète pour la gestion des agents du Ministère")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Rechercher et filtrer les agents")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployees(
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) EmployeeStatus status,
            @RequestParam(required = false) String search
    ) {
        return ResponseEntity.ok(employeeService.getEmployees(departmentId, status, search));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir le détail d'un agent par son identifiant")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel agent")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto created = employeeService.createEmployee(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un agent existant")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDto requestDto
    ) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un agent")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
