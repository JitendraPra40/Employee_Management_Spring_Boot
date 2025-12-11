package com.antsskill.employee_management.dto;

import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.enums.EmailLogStatus;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmailDto {
    private Long id;
    private Employee employee;
    private String recipientEmail;
    private String subject;
    private String message;
    private LocalDateTime sentDate;
    private EmailLogStatus status;
}
