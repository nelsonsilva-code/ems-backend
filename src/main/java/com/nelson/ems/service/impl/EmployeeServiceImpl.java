package com.nelson.ems.service.impl;

import com.nelson.ems.dto.EmployeeDto;
import com.nelson.ems.entity.Department;
import com.nelson.ems.entity.Employee;
import com.nelson.ems.exception.ResourceNotFoundException;
import com.nelson.ems.mapper.EmployeeMapper;
import com.nelson.ems.repository.DepartmentRepository;
import com.nelson.ems.repository.EmployeeRepository;
import com.nelson.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public void createEmployee(EmployeeDto employeeDto) {

        if(this.getEmployeeByEmail(employeeDto.getEmail()).isPresent()) { throw new IllegalArgumentException("User with given email already exists");}

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department with given ID does not exist"));

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee with given ID does not exist"));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public Optional<EmployeeDto> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(EmployeeMapper::mapToEmployeeDto);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee) )
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with given ID does not exist"));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department with given ID does not exist"));

        employee.setDepartment(department);

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with given ID does not exist"));
        employeeRepository.deleteById(employeeId);
    }

}
