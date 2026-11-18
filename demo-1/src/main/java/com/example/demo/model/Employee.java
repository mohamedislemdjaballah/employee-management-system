package com.example.demo.model;

public class Employee {
    public int id;
    public String name;
    public String role;
    public int departmentId;

    public Employee() {
    }

    public Employee(int id, String name, String role, int departmentId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.departmentId = departmentId;
    }
}
