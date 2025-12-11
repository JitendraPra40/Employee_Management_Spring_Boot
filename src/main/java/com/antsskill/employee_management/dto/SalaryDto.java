package com.antsskill.employee_management.dto;

import com.antsskill.employee_management.entity.Employee;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class SalaryDto {
    private Long id;
    private BigDecimal basicPay;
    private BigDecimal hra;
    private BigDecimal otherAllowance;
    private BigDecimal deductions;
    private BigDecimal netSalary;
    private String month;
    private Long year;
    private String pdf_path;
    private Employee employee;
}

