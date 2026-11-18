package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET http://localhost:8080/api/employees
    @GetMapping("/api/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // GET http://localhost:8080/api/employees/{id}
    @GetMapping("/api/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return employee;
    }

    // POST http://localhost:8080/api/employees
    @PostMapping("/api/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // DELETE http://localhost:8080/api/employees/{id}
    @DeleteMapping("/api/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        if (employeeRepository.existsById(id) == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        employeeRepository.deleteById(id);
    }
}
