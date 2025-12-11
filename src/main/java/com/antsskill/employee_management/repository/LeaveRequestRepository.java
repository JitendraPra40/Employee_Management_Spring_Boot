package com.antsskill.employee_management.repository;

import com.antsskill.employee_management.entity.LeaveRequest;
import com.antsskill.employee_management.enums.LeaveRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);

//    Optional<LeaveRequest> findByLeaveId(Long id);
//    List<LeaveRequest> findByStatus(LeaveRequestStatus status);
//    List<LeaveRequest> findByAppliedDate(LocalDate date);
//    List<LeaveRequest> findByEmpId(Long EmployeeId);
}
