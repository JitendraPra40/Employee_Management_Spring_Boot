package com.antsskill.employee_management.service_impl;

import com.antsskill.employee_management.repository.SalaryRepository;
import com.antsskill.employee_management.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService{
    @Autowired
    private SalaryRepository salaryRepository;
}
