package com.antsskill.employee_management.service_impl;
import com.antsskill.employee_management.dto.EmployeeDto;
import com.antsskill.employee_management.entity.Department;
import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.exception.ResourceAlreadyExistsException;
import com.antsskill.employee_management.exception.ResourceNotFoundException;
import com.antsskill.employee_management.mapper.EmployeeMapperStruct;
import com.antsskill.employee_management.repository.DepartmentRepository;
import com.antsskill.employee_management.repository.EmployeeRepository;
import com.antsskill.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapperStruct employeeMapperStruct;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, EmployeeMapperStruct employeeMapperStruct) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapperStruct = employeeMapperStruct;
    }

    @Override
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

        if(employeeRepository.existsByEmail(employeeDto.getEmail())){
            throw new ResourceAlreadyExistsException("Email already exist");
        }
        if(employeeRepository.existsByPhone(employeeDto.getPhone())){
            throw new ResourceAlreadyExistsException("Phone Number already exist");
        }
        Employee employee = employeeMapperStruct.toEntity(employeeDto);
        Department department = departmentRepository
                .findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee saved = employeeRepository.save(employee);
        return employeeMapperStruct.toDto(saved);

    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }
        return employeeMapperStruct.toDtoList(employees);
    }

    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        return employeeMapperStruct.toDto(employee);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id:" + id));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        employee.setPhone(employeeDto.getPhone());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setStatus(employeeDto.getStatus());
        if(employeeDto.getDepartmentId() != null){
            Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not existing with id: " + id));
            employee.setDepartment(department);
        }
        Employee saved = employeeRepository.save(employee);
        return employeeMapperStruct.toDto(saved);
    }

    public void deleteEmployee(Long id) {
        if(id != null){
            employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
            employeeRepository.deleteById(id);
        }

    }

}
