package com.antsskill.employee_management.controller;

import com.antsskill.employee_management.dto.ApiResponse;
import com.antsskill.employee_management.dto.EmployeeDto;
import com.antsskill.employee_management.service_impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImp;

    public EmployeeController(EmployeeServiceImpl employeeServiceImp) {
        this.employeeServiceImp = employeeServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto saved = employeeServiceImp.createEmployee(employeeDto);
        ApiResponse<EmployeeDto> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(201);
        apiResponse.setMessage("Employee Successfully Created");
        apiResponse.setData(saved);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @GetMapping("/employee")
    public ResponseEntity<ApiResponse<List<EmployeeDto>>>
    getEmployees(){
        List<EmployeeDto> employee = employeeServiceImp.getEmployees();
        ApiResponse<List<EmployeeDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(200);
        apiResponse.setMessage("Successfully employee fetched");
        apiResponse.setData(employee);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // http://localhost:8080/api/employees/empid?id=1
    @GetMapping("/empid")
    public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@RequestParam Long id) {

        EmployeeDto empDto = employeeServiceImp.getEmployeeById(id);
        ApiResponse<EmployeeDto> response = new ApiResponse<>(
                200,
                "Employee Details",
                empDto
        );

        return ResponseEntity.ok(response);
    }


    // http://localhost:8080/api/employees/update?id=1
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<EmployeeDto>> updateEmployee(@RequestParam Long id, @RequestBody EmployeeDto employeeDto){
       EmployeeDto updateEmp = employeeServiceImp.updateEmployee(id, employeeDto);
       ApiResponse<EmployeeDto> response = new ApiResponse<>(
               200,
               "Employee data successfully updated",
               updateEmp);

       return ResponseEntity.ok(response);
    }

    // http://localhost:8080/api/employees/delete?id=1
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@RequestParam Long id){
        employeeServiceImp.deleteEmployee(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Employee Successfully deleted", null));
    }

}
