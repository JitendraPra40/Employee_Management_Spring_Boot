package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.DepartmentDto;
import com.antsskill.employee_management.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    @Autowired
    private ModelMapper modelMapper;

    public DepartmentDto toDto(Department department){
        return modelMapper.map(department, DepartmentDto.class);

    }

    public Department toEntity(DepartmentDto departmentDto) {
        return modelMapper.map(departmentDto, Department.class);
    }
}
