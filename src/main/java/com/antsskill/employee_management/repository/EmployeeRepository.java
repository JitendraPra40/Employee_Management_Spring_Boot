package com.antsskill.employee_management.repository;

import com.antsskill.employee_management.dto.EmployeeDto;
import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.enums.EmployeeStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee save(Employee employee);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

//    @Query("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id")
//    Optional<Employee> findEmployeeWithDept(@Param("id") Long id);


//    Optional<Employee> findById(Long id);
//    Optional<Employee> findByEmployeeId(String employeeId);
//    List<Employee> findAll();
//    List<Employee> findByFirstname(String firstName);
//    List<Employee> findByGender(String gender);
//    Optional<Employee> findByPhone(String phone);
//    List<Employee> findByStatus(EmployeeStatus status);
//    Optional<Employee> findByEmail(String email);
//    List<Employee> findByDesignation(String designation);
//    List<Employee> findByDepartmentId(String departmentId);

//    void deleteById(Long id);
}
