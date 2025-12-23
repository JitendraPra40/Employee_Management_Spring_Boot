package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.UserDto;
import com.antsskill.employee_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperStruct {
//    @Mapping(source = "employee.id", target = "employee")
    UserDto toDto(User user);

//    @Mapping(source = "employee", target = "employee.id")
    User toEntity(UserDto userDto);
}
