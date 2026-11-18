package com.example.demo;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static ArrayList<Employee> employees = new ArrayList<>();
	public static ArrayList<Department> departments = new ArrayList<>();

	public static void main(String[] args) {
		departments.add(new Department(1, "Engineering", "Floor 3"));
		departments.add(new Department(2, "Design", "Floor 2"));

		employees.add(new Employee(1, "Alice", "Developer", 1));
		employees.add(new Employee(2, "Bob", "Designer", 2));

		SpringApplication.run(DemoApplication.class, args);
		
	}

}
