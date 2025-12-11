package com.antsskill.employee_management.entity;
import com.antsskill.employee_management.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name="attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name="date", nullable = false)
    private LocalDate date;
    @Column(name="check_in_time", nullable = false)
    private LocalTime checkInTime;
    @Column(name="check_out_time", nullable = false)
    private LocalTime checkOutTime;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private AttendanceStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="emp_id", nullable = false)
    private Employee employee;

}
