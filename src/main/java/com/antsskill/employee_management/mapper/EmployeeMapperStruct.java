package com.antsskill.employee_management.mapper;

import com.antsskill.employee_management.dto.EmployeeDto;
import com.antsskill.employee_management.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = DepartmentMapperStruct.class)
public interface EmployeeMapperStruct {
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    EmployeeDto toDto(Employee employee);


    List<EmployeeDto> toDtoList(List<Employee> employees);

    @Mapping(source = "departmentId", target = "department.id")
    Employee toEntity(EmployeeDto employeeDto);
}
