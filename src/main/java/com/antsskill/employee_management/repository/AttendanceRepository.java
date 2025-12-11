package com.antsskill.employee_management.repository;

import com.antsskill.employee_management.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
//    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId")
//    Optional<Attendance> findByEmployeeId(Long employeeId);
    List<Attendance> findByEmployeeId(Long employeeId);
    boolean existsByEmployeeIdAndDate(Long employeeId, LocalDate date);

//    Optional<Attendance> findByEmpIdAndDate(Long employeeId, LocalDate date);
}
