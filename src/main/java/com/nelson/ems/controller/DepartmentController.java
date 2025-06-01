package com.nelson.ems.controller;

import com.nelson.ems.dto.DepartmentDto;
import com.nelson.ems.service.DepartmentService;
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
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping("create")
    public ResponseEntity<String> createDepartment(@Validated @RequestBody DepartmentDto departmentDto) {

        String departmentName = departmentDto.getName();
        String departmentDescription = departmentDto.getDescription();

        if(departmentName.isEmpty() || departmentDescription.isEmpty()) {
            log.info("Request submitted with missing fields. Request Body was: {}}", departmentDto );
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(departmentService.getDepartmentByName(departmentName).isPresent()) {
            return new ResponseEntity<>("Department with given name already exists", HttpStatus.ALREADY_REPORTED);
        }

        departmentService.createDepartment(departmentDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto updatedDepartment){
        DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);

        return ResponseEntity.ok(departmentDto);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);

        return ResponseEntity.ok("Department deleted");
    }
}
