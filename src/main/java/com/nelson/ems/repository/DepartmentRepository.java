package com.nelson.ems.repository;

import com.nelson.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
    // Custom query methods can be defined here if needed
    // For example, to find an employee by email:
    // Optional<Employee> findByEmail(String email);
}
