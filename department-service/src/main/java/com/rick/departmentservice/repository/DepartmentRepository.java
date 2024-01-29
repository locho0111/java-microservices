package com.rick.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rick.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentCode(String departmentCode);
}
