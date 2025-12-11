package com.antsskill.employee_management.repository;

import com.antsskill.employee_management.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
//    Optional<Salary> findById(Long id);
//    Optional<Salary> findByEmployeesEmpId(Long empId);
}
