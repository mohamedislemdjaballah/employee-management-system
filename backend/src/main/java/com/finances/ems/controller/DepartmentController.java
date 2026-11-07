package com.finances.ems.controller;

import com.finances.ems.dto.DepartmentDto;
import com.finances.ems.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST (@RestController) exposant les Directions du Ministère en JSON.
 * URL de base : http://localhost:8080/api/departments
 */
@RestController
@RequestMapping("/api/departments")
@Tag(name = "Directions", description = "Consultation des Directions et Services du Ministère")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @Operation(summary = "Lister toutes les directions", description = "Retourne la liste des directions triées par code")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
