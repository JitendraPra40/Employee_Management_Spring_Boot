package com.antsskill.employee_management.entity;

import com.antsskill.employee_management.enums.EmailLogStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="email_log")
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee employee;

    @Column(name="recipient_email", nullable = false)
    private String recipientEmail;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name="sent_date")
    private LocalDateTime sentDate;

    @Enumerated(EnumType.STRING)
    private EmailLogStatus status;

}
