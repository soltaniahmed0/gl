package com.example.Backend.Factory;

import com.example.Backend.Entity.Company;
import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.Role;

public class AdminFactory implements EmployeeFactory {
    @Override
    public Employee createEmployee(String email, String firstname, String lastname, String position, Company company) {
        return Employee.builder()
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .position(position)
                .company(company)
                .role(Role.ADMIN)
                .availability(true)
                .firstTime(true)
                .theme(false)
                .build();
    }
}
