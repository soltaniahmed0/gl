package com.example.Backend.Factory;

import com.example.Backend.Entity.Company;
import com.example.Backend.Entity.Employee;

public interface EmployeeFactory {
    Employee createEmployee(String email, String firstname, String lastname, String position, Company company);
}
