package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.dto.LeaveRequestDto;
import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.entity.LeaveRequest;
import com.antsskill.employee_management.enums.LeaveRequestStatus;
import com.antsskill.employee_management.exception.BadRequestException;
import com.antsskill.employee_management.exception.ResourceNotFoundException;
import com.antsskill.employee_management.mapper.LeaveRequestMapperStruct;
import com.antsskill.employee_management.repository.EmployeeRepository;
import com.antsskill.employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveServiceImpl {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestMapperStruct leaveRequestMapperStruct;

    public LeaveServiceImpl(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository, LeaveRequestMapperStruct leaveRequestMapperStruct) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveRequestMapperStruct = leaveRequestMapperStruct;
    }

    public LeaveRequestDto applyLeaveRequest(LeaveRequestDto leaveRequestDto) {

        if (leaveRequestDto.getStartDate().isBefore(LocalDate.now())){
            throw new BadRequestException("Start-date cannot be past of current date");
        }

        if (leaveRequestDto.getEndDate().isBefore(leaveRequestDto.getStartDate())){
            throw new BadRequestException("End-Date cannot be past of Start-Date");
        }

        Employee employee = employeeRepository.findById(leaveRequestDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id " + leaveRequestDto.getEmployeeId()));

        LeaveRequest  leaveRequest = leaveRequestMapperStruct.toEntity(leaveRequestDto);
        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(LeaveRequestStatus.PENDING_MANAGER);

        LeaveRequest saved = leaveRequestRepository.save(leaveRequest);
        return leaveRequestMapperStruct.toDto(saved);
    }

    public List<LeaveRequestDto> getLeaveRequestByEmployeeId(Long employeeId) {
        if(!employeeRepository.existsById(employeeId)){
            throw new BadRequestException("Employee does not exist with id: " + employeeId);
        }
        List<LeaveRequest> leaveRequest = leaveRequestRepository.findByEmployeeId(employeeId);
        return leaveRequestMapperStruct.toDtoList(leaveRequest);
    }
    public void deleteLeaveRequestByEmployeeId(Long leaveId){
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("No record found with id: " + leaveId));
        if (leaveRequest.getStatus() == LeaveRequestStatus.APPROVED){
            throw new BadRequestException("Approved Leave cannot be deleted");
        }
        leaveRequestRepository.delete(leaveRequest);
    }

//    public int getLeaveDays(LeaveRequestDto leaveRequestDto) {
//        return (int) ChronoUnit.DAYS.between(
//                leaveRequestDto.getStartDate(),
//                leaveRequestDto.getEndDate().plusDays(1)
//        );
//    }

}
