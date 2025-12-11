package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.dto.AttendanceDto;
import com.antsskill.employee_management.entity.Attendance;
import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.exception.BadRequestException;
import com.antsskill.employee_management.exception.ResourceAlreadyExistsException;
import com.antsskill.employee_management.exception.ResourceNotFoundException;
import com.antsskill.employee_management.mapper.AttendanceMapperStruct;
import com.antsskill.employee_management.repository.AttendanceRepository;
import com.antsskill.employee_management.repository.EmployeeRepository;
import com.antsskill.employee_management.service.AttendanceService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceMapperStruct attendanceMapperStruct;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository, AttendanceMapperStruct attendanceMapperStruct) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceMapperStruct = attendanceMapperStruct;
    }

    public AttendanceDto saveAttendance(AttendanceDto attendanceDto){
        // Rule 1: Date not in future
        if (!attendanceDto.getCheckInTime().isBefore(attendanceDto.getCheckOutTime())) {
            throw new IllegalArgumentException("Check-in time must be before check-out time");
        }
        // Rule 2: Check-in < Check-out
        if (attendanceDto.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Attendance date cannot be in the future");
        }
        // Rule 3: Prevent duplicate attendance for same day
        if (attendanceRepository.existsByEmployeeIdAndDate(attendanceDto.getEmployeeId(),
                attendanceDto.getDate())) {

            throw new ResourceAlreadyExistsException("Attendance already marked for this employee on this date");
        }

        Attendance attendance = attendanceMapperStruct.toEntity(attendanceDto);
        Employee employee = employeeRepository
                .findById(attendanceDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee Id not found: " + attendanceDto.getEmployeeId()));
        attendance.setEmployee(employee);
        Attendance saved = attendanceRepository.save(attendance);
        return attendanceMapperStruct.toDto(saved);

    }

    public List<AttendanceDto> getAttendanceByEmployeeId(Long employeeId) {
//        if(employeeRepository.existsById(employeeId)){
//            throw new BadRequestException("Employee does not exist with id: " + employeeId);
//        }
        List<Attendance> attendance = attendanceRepository.findByEmployeeId(employeeId);

        if (attendance.isEmpty()) {
            throw new ResourceNotFoundException("Attendance not found for employeeId: " + employeeId);
        }
        return attendanceMapperStruct.toDtoList(attendance);

    }


}