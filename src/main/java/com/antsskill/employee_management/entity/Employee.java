package com.antsskill.employee_management.entity;
import com.antsskill.employee_management.enums.EmployeeStatus;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="employees")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name= "email",unique = true, nullable = false)
    private String email;
    @Column(name= "phone",unique = true, nullable = false)
    private String phone;
    @Column(name= "gender",nullable = false)
    private String gender;
    @Column(name="date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name="date_of_joining", nullable = false)
    private LocalDate dateOfJoining;
    @Column(name = "designation", nullable = false)
    private String designation;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendance;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeaveRequest> leaveRequest;
//
//    @OneToOne(mappedBy = "employee")
//    private Salary salary;
//
//    @OneToMany(mappedBy = "employee")
//    private List<EmailLog> emailLog;


}
