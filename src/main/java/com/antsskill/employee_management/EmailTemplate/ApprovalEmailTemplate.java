package com.antsskill.employee_management.EmailTemplate;

import com.antsskill.employee_management.entity.Employee;
import com.antsskill.employee_management.entity.LeaveRequest;
import org.springframework.stereotype.Service;

@Service
public class ApprovalEmailTemplate {
    public String approvalEmailTemplate(Employee employee, LeaveRequest leave) {
        return """
        <html>
        <body style="font-family: Arial, sans-serif;">
        
        <h2 style="color: green;">Leave Approved ✔</h2>
        
        <p>Hello <b>%s</b>,</p>
        
        <p>Your leave request has been <b style="color:green;">APPROVED</b>.</p>
        
        <h3>Leave Details:</h3>
        <ul>
            <li><b>Start Date:</b> %s</li>
            <li><b>End Date:</b> %s</li>
            <li><b>Status:</b> %s</li>
            <li><b>Approved by Manager:</b> %s</li>
            <li><b>Approved by HR:</b> %s</li>
        </ul>

        <p>Remaining Annual Leave Balance: <b>%d days</b></p>

        <br>
        <p>Regards,<br><b>HR Department</b></p>
        
        </body>
        </html>
        """.formatted(
                employee.getFirstName(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getStatus(),
                leave.getApprovedByManager(),
                leave.getApprovedByHr(),
                leave.getRemainingLeave()
        );
    }

}
