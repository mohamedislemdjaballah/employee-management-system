package com.finances.ems.service;

import com.finances.ems.dto.DepartmentDto;
import com.finances.ems.entity.Department;
import com.finances.ems.exception.ResourceNotFoundException;
import com.finances.ems.repository.DepartmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Couche Métier (@Service) pour la gestion des Directions / Services.
 */
@Service
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Injection de dépendances par constructeur (Bonne pratique recommandée par Spring)
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "code"))
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Department getDepartmentEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direction introuvable avec l'ID : " + id));
    }

    private DepartmentDto toDto(Department dept) {
        return new DepartmentDto(
                dept.getId(),
                dept.getCode(),
                dept.getName(),
                dept.getLocation(),
                dept.getDescription()
        );
    }
}
