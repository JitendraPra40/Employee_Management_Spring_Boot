package com.antsskill.employee_management.service;

import com.antsskill.employee_management.dto.EmployeeDto;
import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.enums.EmployeeStatus;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getEmployees();
//EmployeeDto getEmployeeById(Long id);
//    Optional<EmployeeDto> getById(Long id);
//    Optional<EmployeeDto> getByEmployeeId(String employeeId);
//    List<Employee> getAllEmployee();
//    List<EmployeeDto> getByFirstname(String firstName);
//    List<EmployeeDto> getByGender(String gender);
//    Optional<EmployeeDto> getByPhone(String phone);
//    List<EmployeeDto> getByStatus(EmployeeStatus status);
//    Optional<EmployeeDto> getByEmail(String email);
//    List<EmployeeDto> getByDesignation(String designation);
//    List<EmployeeDto> getByDepartmentId(String departmentId);
//
//    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
//
//    void deleteEmployee(Long id);
}
