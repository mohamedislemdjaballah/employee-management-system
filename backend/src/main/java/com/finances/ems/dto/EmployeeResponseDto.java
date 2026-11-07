package com.finances.ems.dto;

import com.finances.ems.entity.EmployeeStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO renvoyé en JSON à Angular lors de la consultation des employés.
 *
 * Bonne pratique d'architecture :
 * On ne renvoie jamais directement l'entité JPA @Entity au navigateur (risque de boucles
 * infinies JSON et d'exposition de colonnes internes). On renvoie un DTO plat et propre.
 */
public record EmployeeResponseDto(
        Long id,
        String matricule,
        String firstName,
        String lastName,
        String email,
        String phone,
        String jobTitle,
        BigDecimal salary,
        LocalDate hireDate,
        EmployeeStatus status,
        Long departmentId,
        String departmentCode,
        String departmentName
) {
}
