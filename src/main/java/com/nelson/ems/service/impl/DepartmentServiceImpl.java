package com.nelson.ems.service.impl;

import com.nelson.ems.dto.DepartmentDto;
import com.nelson.ems.entity.Department;
import com.nelson.ems.exception.ResourceNotFoundException;
import com.nelson.ems.mapper.DepartmentMapper;
import com.nelson.ems.repository.DepartmentRepository;
import com.nelson.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public void createDepartment(DepartmentDto departmentDto) {

        if(this.getDepartmentByName(departmentDto.getName()).isPresent()) { throw new IllegalArgumentException("Department with given name already exists");}

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department employee = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department with given ID does not exist"));

        return DepartmentMapper.mapToDepartmentDto(employee);
    }

    @Override
    public Optional<DepartmentDto> getDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(DepartmentMapper::mapToDepartmentDto);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department) )
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department with given ID does not exist"));

        department.setDescription(updatedDepartment.getDescription());
        department.setName(updatedDepartment.getName());

        Department updatedDepartmentObj = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(updatedDepartmentObj);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department with given ID does not exist"));
        departmentRepository.deleteById(departmentId);
    }

}
