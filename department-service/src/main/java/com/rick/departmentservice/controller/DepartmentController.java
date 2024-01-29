package com.rick.departmentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rick.departmentservice.dto.DepartmentDto;
import com.rick.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // save department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("/{department_code}")
    public ResponseEntity<DepartmentDto> findDepartmentByCode(@PathVariable String department_code) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(department_code);

        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
