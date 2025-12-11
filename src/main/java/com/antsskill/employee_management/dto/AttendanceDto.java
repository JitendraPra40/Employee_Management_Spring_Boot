package com.antsskill.employee_management.dto;
import com.antsskill.employee_management.enums.AttendanceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AttendanceDto {
    private Long id;

    @NotNull(message = "Date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Check-in time is required")
    private LocalTime checkInTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Check-out time is required")
    private LocalTime checkOutTime;

    @NotNull(message = "Status is required")
    private AttendanceStatus status;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    private String employeeName;

}
