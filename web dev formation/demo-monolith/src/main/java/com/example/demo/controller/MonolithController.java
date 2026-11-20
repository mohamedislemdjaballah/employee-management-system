package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class MonolithController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public MonolithController(EmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("employees", Collections.emptyList());
        model.addAttribute("departments", Collections.emptyList());
        return "index";
    }

    @GetMapping("/refresh")
    public String refresh(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        return "index";
    }

    @PostMapping("/employees")
    public String createEmployee(@RequestParam String name,
                                 @RequestParam String lastName1,
                                 @RequestParam String role,
                                 @RequestParam(required = false) Integer departmentId) {
        Employee employee = new Employee();
        employee.name = name;
        employee.lastName1 = lastName1;
        employee.role = role;

        if (departmentId != null) {
            employee.department = departmentRepository.findById(departmentId).orElse(null);
        }

        employeeRepository.save(employee);
        return "redirect:/refresh";
    }

    @PostMapping("/employees/delete")
    public String deleteEmployee(@RequestParam(required = false) Integer id) {
        if (id != null && employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }
        return "redirect:/refresh";
    }

    @PostMapping("/departments")
    public String createDepartment(@RequestParam String name,
                                   @RequestParam String location) {
        Department department = new Department();
        department.name = name;
        department.location = location;

        departmentRepository.save(department);
        return "redirect:/refresh";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(@RequestParam(required = false) Integer id) {
        if (id != null && departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
        return "redirect:/refresh";
    }
}
