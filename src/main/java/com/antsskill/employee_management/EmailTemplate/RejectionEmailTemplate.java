package com.antsskill.employee_management.EmailTemplate;

import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.entity.LeaveRequest;
import org.springframework.stereotype.Service;

@Service
public class RejectionEmailTemplate {
    public String rejectionEmailTemplate(Employee employee, LeaveRequest leave) {
        return """
        <html>
        <body style="font-family: Arial, sans-serif;">
        
        <h2 style="color: red;">Leave Rejected ✘</h2>
        
        <p>Hello <b>%s</b>,</p>
        
        <p>Your leave request from <b>%s</b> to <b>%s</b> was 
        <b style="color:red;">REJECTED</b>.</p>
        
        <h3>Reason:</h3>
        <p style="color: #b30000;"><b>%s</b></p>

        <br>
        <p>Regards,<br><b>HR Department</b></p>
        
        </body>
        </html>
        """.formatted(
                employee.getFirstName(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getRejectionReason()
        );
    }

}
