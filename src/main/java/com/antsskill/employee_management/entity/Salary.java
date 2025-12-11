package com.antsskill.employee_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="basic_pay")
    private BigDecimal basicPay;
    private BigDecimal hra;
    @Column(name="other_allowance")
    private BigDecimal otherAllowance;
    private BigDecimal deductions;
    @Column(name="net_salary")
    private BigDecimal netSalary;
    private String month;
    private Long year;
    private String pdf_path;

    @OneToOne
    @JoinColumn(name="emp_id")
    private Employee employee;
}
