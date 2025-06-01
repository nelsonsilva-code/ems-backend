package com.nelson.ems.service;

import com.nelson.ems.dto.DepartmentDto;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    void createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long departmentId);
    Optional<DepartmentDto> getDepartmentByName(String email);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment);
    void deleteDepartment(Long departmentId);

}
