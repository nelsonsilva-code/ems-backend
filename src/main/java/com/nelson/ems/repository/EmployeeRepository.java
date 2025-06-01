package com.nelson.ems.repository;

import com.nelson.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    // Custom query methods can be defined here if needed
    // For example, to find an employee by email:
    // Optional<Employee> findByEmail(String email);
}
