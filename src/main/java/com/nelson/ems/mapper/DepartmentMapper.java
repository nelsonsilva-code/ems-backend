package com.nelson.ems.mapper;

import com.nelson.ems.dto.DepartmentDto;
import com.nelson.ems.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }

    public static Department mapToDepartment(DepartmentDto department) {
        return new Department(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }

}
