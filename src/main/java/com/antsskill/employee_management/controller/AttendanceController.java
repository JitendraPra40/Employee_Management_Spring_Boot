package com.antsskill.employee_management.controller;

import com.antsskill.employee_management.dto.ApiResponse;
import com.antsskill.employee_management.dto.AttendanceDto;
import com.antsskill.employee_management.service_impl.AttendanceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceServiceImpl attendanceServiceImpl;

    public AttendanceController(AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceServiceImpl = attendanceServiceImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AttendanceDto>> saveAttendance(@Valid @RequestBody AttendanceDto attendanceDto){
        AttendanceDto saved = attendanceServiceImpl.saveAttendance(attendanceDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Attendance successfully saved", saved ));

    }
    @GetMapping("/attendance")
    public ResponseEntity<ApiResponse<List<AttendanceDto>>> getAttendanceByEmpId(@RequestParam Long employeeId){
        List<AttendanceDto> attendanceDto = attendanceServiceImpl.getAttendanceByEmployeeId(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Successfully Fetched", attendanceDto ));
    }

}
