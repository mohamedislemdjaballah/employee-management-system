package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.Department;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
public class DepartmentController {

    private int idCounter = 3;

    // GET http://localhost:8080/api/departments
    @GetMapping("/api/departments")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Department> getAllDepartments() {
        return DemoApplication.departments;
    }

    // GET http://localhost:8080/api/departments/{id}
    @GetMapping("/api/departments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Department getDepartmentById(@PathVariable int id) {
        for (Department department : DemoApplication.departments) {
            if (department.id == id) {
                return department;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // POST http://localhost:8080/api/departments
    @PostMapping("/api/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody Department department) {
        department.id = idCounter;
        idCounter = idCounter + 1;
        DemoApplication.departments.add(department);
        return department;
    }

    // DELETE http://localhost:8080/api/departments/{id}
    @DeleteMapping("/api/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable int id) {
        for (Department department : DemoApplication.departments) {
            if (department.id == id) {
                DemoApplication.departments.remove(department);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
