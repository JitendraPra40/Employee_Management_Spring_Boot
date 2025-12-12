package com.antsskill.employee_management.repository;

import com.antsskill.employee_management.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    @Query(value = "SELECT COALESCE(SUM(DATEDIFF(end_date, start_date) + 1), 0) " +
            "FROM leave_request " +
            "WHERE employee_id = :empId " +
            "AND status = 'APPROVED' " +
            "AND YEAR(start_date) = YEAR(CURDATE())",
            nativeQuery = true)
    int getTotalLeaveTakenThisYear(@Param("empId") Long empId);


//    Optional<LeaveRequest> findByLeaveId(Long id);
//    List<LeaveRequest> findByStatus(LeaveRequestStatus status);
//    List<LeaveRequest> findByAppliedDate(LocalDate date);
//    List<LeaveRequest> findByEmpId(Long EmployeeId);
}
