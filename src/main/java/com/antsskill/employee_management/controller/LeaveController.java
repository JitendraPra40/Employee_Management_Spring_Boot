package com.antsskill.employee_management.controller;

import com.antsskill.employee_management.dto.ApiResponse;
import com.antsskill.employee_management.dto.LeaveRequestDto;
import com.antsskill.employee_management.entity.LeaveRequest;
import com.antsskill.employee_management.service_impl.LeaveServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    public final LeaveServiceImpl leaveServiceImpl;

    public LeaveController(LeaveServiceImpl leaveServiceImpl) {
        this.leaveServiceImpl = leaveServiceImpl;
    }

    @PostMapping("/request")
    public ResponseEntity<ApiResponse<LeaveRequestDto>> applyLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto){
        LeaveRequestDto leaveRequest = leaveServiceImpl.applyLeaveRequest(leaveRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(201, "Successfully applied for leave wait for appoval", leaveRequest));
    }

    @GetMapping("/empid")
    public ResponseEntity<ApiResponse<List<LeaveRequestDto>>> getleaveRequestByEmployeeId(@RequestParam Long employeeId){
        List<LeaveRequestDto> leaveRequestDto = leaveServiceImpl.getleaveRequestByEmployeeId(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Leave Request successfully fetched", leaveRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteLeaveRequestByEmployeeId(@RequestParam Long leaveId){
        leaveServiceImpl.deleteLeaveRequestByEmployeeId(leaveId);
        return ResponseEntity.ok(new ApiResponse(200, "Leave Request Successfully Deleted", null));
    }


}
