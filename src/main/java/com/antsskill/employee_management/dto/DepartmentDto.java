package com.antsskill.employee_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @NotBlank(message = "Department name cannot be empty")
    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters")
    private String departmentName;
    private String description;
}
