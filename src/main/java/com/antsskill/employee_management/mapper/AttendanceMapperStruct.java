package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.AttendanceDto;
import com.antsskill.employee_management.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapperStruct {
    // Entity -> DTO
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.firstName", target = "employeeName")
    AttendanceDto toDto(Attendance attendance);

    // DTO -> Entity
    @Mapping(source = "employeeId", target = "employee.id")
    Attendance toEntity(AttendanceDto attendanceDto);

    List<AttendanceDto> toDtoList(List<Attendance> attendance);
}
