package com.antsskill.employee_management.dto;
import com.antsskill.employee_management.entity.Department;
import com.antsskill.employee_management.entity.User;
import com.antsskill.employee_management.enums.EmployeeStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;


@Getter
@Setter
public class EmployeeDto {
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be a past date")
    private LocalDate dateOfBirth;

    @NotNull(message = "Date of joining is required")
    @PastOrPresent(message = "Date of joining cannot be in the future")
    private LocalDate dateOfJoining;

    @NotBlank(message = "Designation is required")
    @Size(min = 2, max = 50, message = "Designation must be between 2 and 50 characters")
    private String designation;

    @NotNull(message = "Employee status is required")
    private EmployeeStatus status;

    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be a positive number")
    private Long departmentId;

    private String departmentName;

    private Long userId;

}
