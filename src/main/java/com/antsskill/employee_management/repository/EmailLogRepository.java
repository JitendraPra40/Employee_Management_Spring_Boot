package com.antsskill.employee_management.repository;

import com.antsskill.employee_management.entity.EmailLog;
import com.antsskill.employee_management.enums.EmailLogStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
//    List<EmailLog> findByEmpId(Long employeeId);
//    List<EmailLog> findByStatus(EmailLogStatus status);

}
