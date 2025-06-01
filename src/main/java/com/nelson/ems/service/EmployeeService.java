package com.nelson.ems.service;

import com.nelson.ems.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    Optional<EmployeeDto> getEmployeeByEmail(String email);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
    void deleteEmployee(Long employeeId);

}
