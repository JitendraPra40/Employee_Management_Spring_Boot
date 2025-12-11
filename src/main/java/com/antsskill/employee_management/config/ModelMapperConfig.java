package com.antsskill.employee_management.config;


import com.antsskill.employee_management.dto.AttendanceDto;
import com.antsskill.employee_management.entity.Attendance;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();

//        ModelMapper mapper = new ModelMapper();
//        mapper.typeMap(Attendance.class, AttendanceDto.class)
//                .addMappings(m -> m.skip(AttendanceDto::setEmployee));
//
//        return mapper;
    }
}
