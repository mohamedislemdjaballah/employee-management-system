package com.finances.ems.service;

import com.finances.ems.dto.EmployeeRequestDto;
import com.finances.ems.dto.EmployeeResponseDto;
import com.finances.ems.entity.Department;
import com.finances.ems.entity.Employee;
import com.finances.ems.entity.EmployeeStatus;
import com.finances.ems.exception.DuplicateResourceException;
import com.finances.ems.exception.ResourceNotFoundException;
import com.finances.ems.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Couche Métier (@Service) pour la gestion des Employés.
 *
 * Responsabilités de la couche @Service :
 * 1. Appliquer les règles de gestion métier (unicité du matricule et de l'email).
 * 2. Orchestrer les appels aux Repositories (EmployeeRepository + DepartmentService).
 * 3. Convertir les Entités JPA en DTOs JSON.
 */
@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public List<EmployeeResponseDto> getEmployees(Long departmentId, EmployeeStatus status, String search) {
        String normalizedSearch = (search != null && !search.isBlank()) ? search.trim() : null;
        return employeeRepository.searchEmployees(departmentId, status, normalizedSearch)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = findEntityById(id);
        return toResponseDto(employee);
    }

    @Transactional
    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
        if (employeeRepository.existsByMatriculeIgnoreCase(dto.matricule().trim())) {
            throw new DuplicateResourceException("Un agent avec le matricule '" + dto.matricule() + "' existe déjà.");
        }
        if (employeeRepository.existsByEmailIgnoreCase(dto.email().trim())) {
            throw new DuplicateResourceException("Un agent avec l'email '" + dto.email() + "' existe déjà.");
        }

        Department department = departmentService.getDepartmentEntityById(dto.departmentId());

        Employee employee = new Employee();
        applyDtoToEntity(dto, employee, department);

        Employee saved = employeeRepository.save(employee);
        return toResponseDto(saved);
    }

    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {
        Employee existing = findEntityById(id);

        if (employeeRepository.existsByMatriculeIgnoreCaseAndIdNot(dto.matricule().trim(), id)) {
            throw new DuplicateResourceException("Le matricule '" + dto.matricule() + "' est déjà attribué à un autre agent.");
        }
        if (employeeRepository.existsByEmailIgnoreCaseAndIdNot(dto.email().trim(), id)) {
            throw new DuplicateResourceException("L'email '" + dto.email() + "' est déjà utilisé par un autre agent.");
        }

        Department department = departmentService.getDepartmentEntityById(dto.departmentId());
        applyDtoToEntity(dto, existing, department);

        Employee updated = employeeRepository.save(existing);
        return toResponseDto(updated);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee existing = findEntityById(id);
        employeeRepository.delete(existing);
    }

    private Employee findEntityById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent introuvable avec l'ID : " + id));
    }

    private void applyDtoToEntity(EmployeeRequestDto dto, Employee employee, Department department) {
        employee.setMatricule(dto.matricule().trim().toUpperCase());
        employee.setFirstName(dto.firstName().trim());
        employee.setLastName(dto.lastName().trim().toUpperCase());
        employee.setEmail(dto.email().trim().toLowerCase());
        employee.setPhone(dto.phone() != null ? dto.phone().trim() : "");
        employee.setJobTitle(dto.jobTitle().trim());
        employee.setSalary(dto.salary());
        employee.setHireDate(dto.hireDate());
        employee.setStatus(dto.status());
        employee.setDepartment(department);
    }

    private EmployeeResponseDto toResponseDto(Employee emp) {
        Department dept = emp.getDepartment();
        return new EmployeeResponseDto(
                emp.getId(),
                emp.getMatricule(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getPhone(),
                emp.getJobTitle(),
                emp.getSalary(),
                emp.getHireDate(),
                emp.getStatus(),
                dept.getId(),
                dept.getCode(),
                dept.getName()
        );
    }
}
