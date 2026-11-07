package com.finances.ems.repository;

import com.finances.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Couche d'accès aux données (DAO / Repository) pour les Directions.
 *
 * Note pédagogique :
 * En héritant de JpaRepository<Department, Long>, Spring génère automatiquement :
 * - findAll()
 * - findById(Long id)
 * - save(Department dept)
 * - deleteById(Long id)
 * sans écrire une seule ligne de SQL !
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCase(String code);
}
