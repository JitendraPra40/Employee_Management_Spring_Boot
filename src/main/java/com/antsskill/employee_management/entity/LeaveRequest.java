package com.antsskill.employee_management.entity;

import com.antsskill.employee_management.enums.LeaveRequestStatus;
import jakarta.persistence.*;
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
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    private LocalDate endDate;

    @Column(name = "reason", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String reason;

    @Column(name = "applied_date", nullable = false)
    @JdbcTypeCode(SqlTypes.DATE)
    private LocalDate appliedDate;

    private String approvedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private LeaveRequestStatus status = LeaveRequestStatus.PENDING;

}
