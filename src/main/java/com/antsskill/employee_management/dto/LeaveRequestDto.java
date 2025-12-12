package com.antsskill.employee_management.dto;

import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.enums.LeaveRequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
public class LeaveRequestDto {
    private Long id;

    @NotNull(message = "EmployeeId is required")
    private Long employeeId;

    private String firstName;

    @NotNull(message = "Leave Start Date is required")
    private LocalDate startDate;

    @NotNull(message = "Leave End Date is required")
    private LocalDate endDate;

    @NotBlank(message = "Reason cannot blank")
    private String reason;

    @NotNull(message = "Applied Date is required")
    private LocalDate appliedDate = LocalDate.now();;

    @NotBlank(message = "Approved By is required")
    private String approvedByManager;
    private String approvedByHr;

    private String rejectionReason;

    @NotBlank(message = "Status must be provided")
    @Enumerated(EnumType.STRING)
    private LeaveRequestStatus status;

    private int remainingLeave;


}
