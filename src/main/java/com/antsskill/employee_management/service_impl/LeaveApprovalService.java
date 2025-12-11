package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.dto.LeaveApprovalDto;
import com.antsskill.employee_management.entity.LeaveRequest;
import com.antsskill.employee_management.enums.LeaveRequestStatus;
import com.antsskill.employee_management.exception.BadRequestException;
import com.antsskill.employee_management.exception.ResourceNotFoundException;
import com.antsskill.employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeaveApprovalService {
    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveApprovalService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    public String leaveApproval(LeaveApprovalDto leaveApprovalDto) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveApprovalDto.getLeaveId())
                .orElseThrow(() -> new ResourceNotFoundException("No Leave Request Found with id: " + leaveApprovalDto.getLeaveId()));

        if (leaveRequest.getEndDate().isBefore(LocalDate.now())){
            throw new BadRequestException("Cannot approve leave that has already ended");
        }

        if (leaveApprovalDto.isApproved()){
            leaveRequest.setStatus(LeaveRequestStatus.APPROVED);
        } else {
            leaveRequest.setStatus(LeaveRequestStatus.REJECTED);
        }

        leaveRequest.setApprovedBy(leaveApprovalDto.getHrName());
        leaveRequestRepository.save(leaveRequest);
        return "Leave " + leaveRequest.getStatus().name().toLowerCase() + " successfully!";
    }
}
