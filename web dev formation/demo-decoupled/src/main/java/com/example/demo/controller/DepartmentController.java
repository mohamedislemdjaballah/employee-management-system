package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // GET http://localhost:8080/api/departments
    @GetMapping("/api/departments")
    @ResponseStatus(HttpStatus.OK)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // GET http://localhost:8080/api/departments/{id}
    @GetMapping("/api/departments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Department getDepartmentById(@PathVariable int id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return department;
    }

    // POST http://localhost:8080/api/departments
    @PostMapping("/api/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    // DELETE http://localhost:8080/api/departments/{id}
    @DeleteMapping("/api/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable int id) {
        if (departmentRepository.existsById(id) == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        departmentRepository.deleteById(id);
    }
}
