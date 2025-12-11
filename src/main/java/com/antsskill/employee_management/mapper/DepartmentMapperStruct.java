package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.DepartmentDto;
import com.antsskill.employee_management.entity.Department;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapperStruct {
    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);
    List<DepartmentDto> toDtoList(List<Department> department);
}
