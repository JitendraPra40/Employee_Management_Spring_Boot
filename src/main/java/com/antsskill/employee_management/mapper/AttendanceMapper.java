package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.AttendanceDto;
import com.antsskill.employee_management.entity.Attendance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public class AttendanceMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AttendanceDto toDto(Attendance attendance) {
        AttendanceDto dto = modelMapper.map(attendance, AttendanceDto.class);
        if (attendance.getEmployee() != null) {
            dto.setEmployeeId(attendance.getEmployee().getId());
        }
        return dto;
    }

    public Attendance toEntity(AttendanceDto attendanceDto) {
        Attendance attendance = modelMapper.map(attendanceDto, Attendance.class);
        return attendance;
    }
}