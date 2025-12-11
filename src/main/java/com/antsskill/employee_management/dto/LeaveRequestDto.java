package com.antsskill.employee_management.dto;

import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.enums.LeaveRequestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
public class LeaveRequestDto {
    private Long id;

    @NotNull(message = "Leave Start Date is required")
    private LocalDate startDate;

    @NotNull(message = "Leave End Date is required")
    private LocalDate endDate;

    @NotBlank(message = "Reason cannot blank")
    private String reason;

    @NotNull(message = "Applied Date is required")
    private LocalDate appliedDate;

    @NotBlank(message = "Approved By is required")
    private String approvedBy;

    @NotNull(message = "EmployeeId is required")
    private Long employeeId;

    private String firstName;

    @NotBlank(message = "Status must be provided")
    private LeaveRequestStatus status;
}
