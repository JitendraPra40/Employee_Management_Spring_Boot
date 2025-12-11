package com.antsskill.employee_management.controller;

import com.antsskill.employee_management.dto.ApiResponse;
import com.antsskill.employee_management.dto.LeaveApprovalDto;
import com.antsskill.employee_management.dto.LeaveRequestDto;
import com.antsskill.employee_management.service_impl.LeaveApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leave/approval")
public class LeaveApprovalController {
    private final LeaveApprovalService leaveApprovalService;

    public LeaveApprovalController(LeaveApprovalService leaveApprovalService) {
        this.leaveApprovalService = leaveApprovalService;
    }

    @PostMapping("/leaveId")
    public ResponseEntity<ApiResponse<String>> leaveApproval(@RequestBody LeaveApprovalDto leaveApprovalDto){
       String leaveApproval = leaveApprovalService.leaveApproval(leaveApprovalDto);
       return ResponseEntity.ok(new ApiResponse<>(200, "Leave Status Updated", leaveApproval));
    }
}
