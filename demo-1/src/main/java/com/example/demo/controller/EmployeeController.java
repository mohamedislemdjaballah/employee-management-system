package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.Employee;
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
public class EmployeeController {

    private int idCounter = 3;

    // GET http://localhost:8080/api/employees
    @GetMapping("/api/employees")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Employee> getAllEmployees() {
        return DemoApplication.employees;
    }

    // GET http://localhost:8080/api/employees/{id}
    @GetMapping("/api/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable int id) {
        for (Employee employee : DemoApplication.employees) {
            if (employee.id == id) {
                return employee;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // POST http://localhost:8080/api/employees
    @PostMapping("/api/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.id = idCounter;
        idCounter = idCounter + 1;
        DemoApplication.employees.add(employee);
        return employee;
    }

    // DELETE http://localhost:8080/api/employees/{id}
    @DeleteMapping("/api/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        for (Employee employee : DemoApplication.employees) {
            if (employee.id == id) {
                DemoApplication.employees.remove(employee);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
