package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.EmailDto;
import com.antsskill.employee_management.entity.EmailLog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EmailDto toDto(EmailLog emailLog) {
        return modelMapper.map(emailLog, EmailDto.class);
    }

    public EmailLog toEntity(EmailDto emailDto) {
        return modelMapper.map(emailDto, EmailLog.class);
    }
}
