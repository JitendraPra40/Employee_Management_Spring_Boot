package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.LeaveRequestDto;
import com.antsskill.employee_management.entity.LeaveRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {
    @Autowired
    private ModelMapper modelMapper;

    public LeaveRequestDto toDto(LeaveRequest leaveRequest){
        return modelMapper.map(leaveRequest, LeaveRequestDto.class);
    }

    public LeaveRequest toEntity(LeaveRequestDto leaveRequestDto){
        return modelMapper.map(leaveRequestDto, LeaveRequest.class);
    }

}
