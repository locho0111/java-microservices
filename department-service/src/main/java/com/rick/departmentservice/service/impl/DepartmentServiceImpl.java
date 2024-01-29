package com.rick.departmentservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rick.departmentservice.dto.DepartmentDto;
import com.rick.departmentservice.entity.Department;
import com.rick.departmentservice.repository.DepartmentRepository;
import com.rick.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // convert departmentDto to department entity
        Department department = mapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = mapper.map(savedDepartment, DepartmentDto.class);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);

        DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);

        return departmentDto;
    }

}
