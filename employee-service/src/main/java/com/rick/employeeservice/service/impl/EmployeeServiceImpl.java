package com.rick.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.rick.employeeservice.dto.APIResponseDto;
import com.rick.employeeservice.dto.DepartmentDto;
import com.rick.employeeservice.dto.EmployeeDto;
import com.rick.employeeservice.entity.Employee;
import com.rick.employeeservice.repository.EmployeeRepository;
import com.rick.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;
    private RestTemplate restTemplate;
    private WebClient webClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper, RestTemplate restTemplate,
            WebClient webClient) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);

        Employee savedEmployee = employeeRepository.save(employee);

        return mapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        // ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
        // "http://127.0.0.1:8080/api/departments/" + employee.getDepartmentCode(),
        // DepartmentDto.class);
        // DepartmentDto departmentDto = responseEntity.getBody();

        DepartmentDto departmentDto = webClient.get()
                .uri("http://127.0.0.1:8080/api/departments/" + employee.getDepartmentCode()).retrieve()
                .bodyToMono(DepartmentDto.class).block();

        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto);

        return apiResponseDto;
    }

}
