package com.antsskill.employee_management.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="departments")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="department_name", nullable = false)
    private String departmentName;
    @Column(name="description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employee;

}

