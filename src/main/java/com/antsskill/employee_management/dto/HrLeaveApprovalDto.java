package com.antsskill.employee_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HrLeaveApprovalDto {
    @NotNull(message = "Leave Id required")
    private Long leaveId;

    @NotBlank(message = "HR Name is required")
    private String hrName;

    @NotBlank(message = "true: APPROVED false: REJECTED is required")
    private boolean approved;

    private String rejectionReason;
}
