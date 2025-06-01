package com.nelson.ems.controller;

import com.nelson.ems.dto.EmployeeDto;
import com.nelson.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("create")
    public ResponseEntity<String> createEmployee(@Validated @RequestBody EmployeeDto employeeDto) {

        String firstName = employeeDto.getFirstName();
        String lastName = employeeDto.getLastName();
        String email = employeeDto.getEmail();

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            log.info("Request submitted with missing fields. Request Body was: {}}", employeeDto );
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(employeeService.getEmployeeByEmail(email).isPresent()) {
            return new ResponseEntity<>("User with given email already exists", HttpStatus.ALREADY_REPORTED);
        }

        employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok("Employee deleted");
    }
}
