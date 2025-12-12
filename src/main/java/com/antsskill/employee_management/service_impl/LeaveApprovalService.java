package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.dto.HrLeaveApprovalDto;
import com.antsskill.employee_management.dto.ManagerLeaveApprovalDto;
import com.antsskill.employee_management.entity.LeaveRequest;
import com.antsskill.employee_management.enums.LeaveRequestStatus;
import com.antsskill.employee_management.exception.ResourceAlreadyExistsException;
import com.antsskill.employee_management.exception.ResourceNotFoundException;
import com.antsskill.employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaveApprovalService {
    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveApprovalService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    public String managerApproval(ManagerLeaveApprovalDto managerLeaveApprovalDto) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(managerLeaveApprovalDto.getLeaveId())
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));

        if (leaveRequest.getStatus() != LeaveRequestStatus.PENDING_MANAGER) {
            throw new ResourceAlreadyExistsException("Leave is not pending manager approval");
        }

        if (managerLeaveApprovalDto.isApproved()) {
            leaveRequest.setStatus(LeaveRequestStatus.PENDING_HR);
            leaveRequest.setApprovedByManager(managerLeaveApprovalDto.getManagerName());
            leaveRequest.setRejectionReason(null);
            leaveRequestRepository.save(leaveRequest);
            return "Leave approved by Manager";
        } else {
            leaveRequest.setStatus(LeaveRequestStatus.REJECTED_BY_MANAGER);
            leaveRequest.setRejectionReason(managerLeaveApprovalDto.getRejectionReason());
            leaveRequest.setApprovedByManager(managerLeaveApprovalDto.getManagerName());
            leaveRequestRepository.save(leaveRequest);
            return "Leave rejected by Manager";
        }
    }

    public String hrApproval(HrLeaveApprovalDto hrLeaveApprovalDto) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(hrLeaveApprovalDto.getLeaveId())
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));

        if (leaveRequest.getStatus() != LeaveRequestStatus.PENDING_HR) {
            throw new ResourceAlreadyExistsException("Leave is not pending HR approval");
        }

        if (hrLeaveApprovalDto.isApproved()) {

            leaveRequest.setStatus(LeaveRequestStatus.APPROVED);
            leaveRequest.setApprovedByHr(hrLeaveApprovalDto.getHrName());
            leaveRequest.setRejectionReason(null);
            LeaveRequest leaveRequest1 = leaveRequestRepository.save(leaveRequest);

            Long employeeId = leaveRequest1.getEmployee().getId();
            int remainingLeave = leaveCalculation(employeeId);

            leaveRequest.setRemainingLeave(remainingLeave);
            leaveRequestRepository.save(leaveRequest);

            return "Leave approved by HR and balance deducted";
        } else {
            leaveRequest.setStatus(LeaveRequestStatus.REJECTED_BY_HR);
            leaveRequest.setRejectionReason(hrLeaveApprovalDto.getRejectionReason());
            leaveRequest.setApprovedByHr(hrLeaveApprovalDto.getHrName());
            leaveRequestRepository.save(leaveRequest);
            return "Leave rejected by HR";
        }

    }
    public int leaveCalculation(Long employeeId) {
        int ALLOWED_LEAVE = 24;
        int alreadyTaken = leaveRequestRepository.getTotalLeaveTakenThisYear(employeeId);
        return ALLOWED_LEAVE - alreadyTaken;
    }
}
