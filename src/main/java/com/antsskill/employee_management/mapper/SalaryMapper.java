package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.SalaryDto;
import com.antsskill.employee_management.entity.Salary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalaryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SalaryDto toDto(Salary salary){
        return modelMapper.map(salary, SalaryDto.class);
    }
    public Salary toEntity(SalaryDto salaryDto){
        return modelMapper.map(salaryDto, Salary.class);
    }
}
