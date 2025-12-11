package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.LeaveRequestDto;
import com.antsskill.employee_management.entity.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapperStruct {

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.firstName", target = "firstName")
    LeaveRequestDto toDto(LeaveRequest leaveRequest);

    List<LeaveRequestDto> toDtoList(List<LeaveRequest> leaveRequest);

    LeaveRequest toEntity(LeaveRequestDto leaveRequestDto);
}
