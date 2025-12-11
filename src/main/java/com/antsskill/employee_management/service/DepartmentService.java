package com.antsskill.employee_management.service;

import com.antsskill.employee_management.dto.DepartmentDto;
import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments();
}
