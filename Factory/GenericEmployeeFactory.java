package com.example.Backend.Factory;

import com.example.Backend.Entity.Company;
import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.Role;

public class GenericEmployeeFactory implements EmployeeFactory {
    @Override
    public Employee createEmployee(String email, String firstname, String lastname, String position, Company company) {
        return Employee.builder()
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .position(position != null ? position : "Employee")
                .password("defaultPassword") // Vous pouvez générer un mot de passe par défaut
                .company(company)
                .role(Role.Employee)
                .availability(false)
                .build();
    }
}