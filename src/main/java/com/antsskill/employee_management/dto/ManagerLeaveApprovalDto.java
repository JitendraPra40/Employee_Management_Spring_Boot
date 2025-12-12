package com.antsskill.employee_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ManagerLeaveApprovalDto {
    @NotNull(message = "Leave Id required")
    private Long leaveId;

    @NotBlank(message = "Manager Name is required")
    private String managerName;
    @NotBlank(message = "true: APPROVED false: REJECTED is required")
    private boolean approved;

    private String rejectionReason;
}
