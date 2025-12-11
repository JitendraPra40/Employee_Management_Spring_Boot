package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.EmployeeDto;
import com.antsskill.employee_management.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDto toDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public Employee toEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }
}


