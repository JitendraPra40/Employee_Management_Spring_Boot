package com.antsskill.employee_management.controller;

import com.antsskill.employee_management.dto.ApiResponse;
import com.antsskill.employee_management.dto.DepartmentDto;
import com.antsskill.employee_management.service_impl.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentServiceImpl departmentServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DepartmentDto>>
    createDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        DepartmentDto saved = departmentServiceImpl.createDepartment(departmentDto);
        ApiResponse<DepartmentDto> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(201);
        apiResponse.setMessage("Department Successfully created");
        apiResponse.setData(saved);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }
    @GetMapping("/departments")
    public ResponseEntity<ApiResponse<List<DepartmentDto>>> getAllDepartment(){
        List<DepartmentDto> departments = departmentServiceImpl.getAllDepartments();
        ApiResponse<List<DepartmentDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(200);
        apiResponse.setMessage("Departments list");
        apiResponse.setData(departments);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    // http://localhost:8080/api/department/deptid?id=1
    @GetMapping("/deptid")
    public ResponseEntity<ApiResponse<DepartmentDto>> departmentById(@RequestParam Long id){
        DepartmentDto departmentDto = departmentServiceImpl.getDepartmentById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Department by DeptId", departmentDto));
    }
//http://localhost:8080/api/department/deptid?id=1
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteDepartment(@RequestParam Long id){
        departmentServiceImpl.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Successfully deleted", null));
    }

    //http://localhost:8080/api/department/update?id=1
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<DepartmentDto>> updateDepartment(@Valid @RequestParam Long id, @RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDtoUpdate = departmentServiceImpl.updateById(id, departmentDto);
        return ResponseEntity.ok(new ApiResponse<>(200, "Successfully Update", departmentDtoUpdate));

    }
}

