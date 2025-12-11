package com.antsskill.employee_management.service;

import com.antsskill.employee_management.dto.AttendanceDto;
import com.antsskill.employee_management.entity.Attendance;

public interface AttendanceService {
    AttendanceDto saveAttendance(AttendanceDto attendanceDto);
}
