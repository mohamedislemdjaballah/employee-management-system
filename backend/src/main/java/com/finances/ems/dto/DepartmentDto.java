package com.finances.ems.dto;

/**
 * DTO (Data Transfer Object) représentant une Direction renvoyée en JSON à Angular.
 *
 * Note pédagogique :
 * Le mot-clé Java "record" crée automatiquement une classe immuable avec constructeur,
 * getters, equals(), hashCode() et toString().
 */
public record DepartmentDto(
        Long id,
        String code,
        String name,
        String location,
        String description
) {
}
