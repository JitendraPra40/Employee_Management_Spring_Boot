package com.antsskill.employee_management.entity;

import com.antsskill.employee_management.enums.LeaveRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;



    @Column(name = "start_date", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    @FutureOrPresent
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    @FutureOrPresent
    private LocalDate endDate;

    @Column(name = "reason", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String reason;

    @Column(name = "applied_date", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    @PastOrPresent
    private LocalDate appliedDate;

    private String approvedByManager;
    private String approvedByHr;

    @Enumerated(EnumType.STRING)
    private LeaveRequestStatus status;

    private String rejectionReason;

    private int remainingLeave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id", nullable = false)
    private Employee employee;
}
