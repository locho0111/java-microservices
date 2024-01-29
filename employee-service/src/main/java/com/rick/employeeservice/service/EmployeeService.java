package com.rick.employeeservice.service;

import com.rick.employeeservice.dto.APIResponseDto;
import com.rick.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
