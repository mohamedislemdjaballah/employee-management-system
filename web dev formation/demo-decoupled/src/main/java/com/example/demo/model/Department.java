package com.example.demo.model;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String name;
    public String location;

    public Department() {
    }
}
