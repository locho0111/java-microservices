package com.rick.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rick.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
