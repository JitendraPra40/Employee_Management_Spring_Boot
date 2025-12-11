package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.dto.DepartmentDto;
import com.antsskill.employee_management.entity.Department;
import com.antsskill.employee_management.exception.ResourceNotFoundException;
import com.antsskill.employee_management.mapper.DepartmentMapperStruct;
import com.antsskill.employee_management.repository.DepartmentRepository;
import com.antsskill.employee_management.service.DepartmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapperStruct departmentMapperStruct;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapperStruct departmentMapperStruct) {
        this.departmentRepository = departmentRepository;
        this.departmentMapperStruct = departmentMapperStruct;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department entity = departmentMapperStruct.toEntity(departmentDto);
        Department saved =departmentRepository.save(entity);
        return departmentMapperStruct.toDto(saved);

    }

    @Override
    public List<DepartmentDto> getAllDepartments(){
        List<Department> departments = departmentRepository.findAll();
        if(departments.isEmpty()){
            throw new ResourceNotFoundException("No Department found");
        }
        return departmentMapperStruct.toDtoList(departments);

    }

    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return departmentMapperStruct.toDto(department);
    }

    public void deleteById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No department found with id: " + id));
        departmentRepository.delete(department);
    }


    public DepartmentDto updateById(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Department found with id: " + id));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDescription(departmentDto.getDescription());
        Department saved = departmentRepository.save(department);
        return departmentMapperStruct.toDto(saved);
    }
}
