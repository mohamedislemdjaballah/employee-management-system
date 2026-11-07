package com.finances.ems.repository;

import com.finances.ems.entity.Employee;
import com.finances.ems.entity.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Couche d'accès aux données (DAO / Repository) pour les Employés.
 *
 * Équivalent Django : Employee.objects.filter(...)
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByMatriculeIgnoreCase(String matricule);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    boolean existsByMatriculeIgnoreCaseAndIdNot(String matricule, Long id);

    /**
     * Recherche multicritères (par Direction, par Statut, et par mot-clé Nom/Prénom/Matricule/Email).
     */
    @Query("""
            SELECT e FROM Employee e
            WHERE (:departmentId IS NULL OR e.department.id = :departmentId)
              AND (:status IS NULL OR e.status = :status)
              AND (
                  :search IS NULL OR :search = ''
                  OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(e.matricule) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(e.jobTitle) LIKE LOWER(CONCAT('%', :search, '%'))
              )
            ORDER BY e.lastName ASC, e.firstName ASC
            """)
    List<Employee> searchEmployees(
            @Param("departmentId") Long departmentId,
            @Param("status") EmployeeStatus status,
            @Param("search") String search
    );
}
