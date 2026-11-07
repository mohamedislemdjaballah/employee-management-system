package com.finances.ems.dto;

import com.finances.ems.entity.EmployeeStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO pour la création (POST) ou la modification (PUT) d'un employé envoyé par Angular.
 *
 * Équivalent Laravel : FormRequest rules()
 * Équivalent Django  : ModelSerializer validation
 */
public record EmployeeRequestDto(

        @NotBlank(message = "Le matricule est obligatoire (ex: FIN-2026-001)")
        @Size(max = 30, message = "Le matricule ne doit pas dépasser 30 caractères")
        String matricule,

        @NotBlank(message = "Le prénom est obligatoire")
        @Size(max = 80, message = "Le prénom ne doit pas dépasser 80 caractères")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 80, message = "Le nom ne doit pas dépasser 80 caractères")
        String lastName,

        @NotBlank(message = "L'adresse email est obligatoire")
        @Email(message = "Le format de l'adresse email est invalide")
        String email,

        @Size(max = 30, message = "Le numéro de téléphone ne doit pas dépasser 30 caractères")
        String phone,

        @NotBlank(message = "L'intitulé du poste / grade est obligatoire")
        @Size(max = 120, message = "L'intitulé du poste ne doit pas dépasser 120 caractères")
        String jobTitle,

        @NotNull(message = "Le salaire mensuel est obligatoire")
        @DecimalMin(value = "0.0", inclusive = true, message = "Le salaire doit être positif")
        BigDecimal salary,

        @NotNull(message = "La date de recrutement est obligatoire")
        LocalDate hireDate,

        @NotNull(message = "Le statut administratif est obligatoire")
        EmployeeStatus status,

        @NotNull(message = "La direction d'affectation est obligatoire")
        Long departmentId
) {
}
