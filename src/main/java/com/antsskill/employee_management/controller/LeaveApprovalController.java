package com.antsskill.employee_management.controller;

import com.antsskill.employee_management.dto.ApiResponse;
import com.antsskill.employee_management.dto.HrLeaveApprovalDto;
import com.antsskill.employee_management.dto.ManagerLeaveApprovalDto;
import com.antsskill.employee_management.service_impl.LeaveApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leave/approval")
public class LeaveApprovalController {
    private final LeaveApprovalService leaveApprovalService;

    public LeaveApprovalController(LeaveApprovalService leaveApprovalService) {
        this.leaveApprovalService = leaveApprovalService;
    }

    @PostMapping("/manager")
    public ResponseEntity<ApiResponse<String>> managerApproval(@RequestBody ManagerLeaveApprovalDto managerLeaveApprovalDto){
       String leaveApproval = leaveApprovalService.managerApproval(managerLeaveApprovalDto);
       return ResponseEntity.ok(new ApiResponse<>(200, "Leave Status Updated", leaveApproval));
    }

    @PostMapping("/hr")
    public ResponseEntity<ApiResponse<String>> hrApproval(@RequestBody HrLeaveApprovalDto hrLeaveApprovalDto){
        String leaveApproval = leaveApprovalService.hrApproval(hrLeaveApprovalDto);
        return ResponseEntity.ok(new ApiResponse<>(200, "Leave Status Updated", leaveApproval));
    }
}
