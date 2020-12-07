package com.project.ess.controllers;

import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/employee/request")
public class EmployeeRequestController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<CustomMessageWithId> updateDataEmp(@RequestParam("employee") String employee, @RequestParam(value = "file",required = false) MultipartFile file, Authentication authentication){

        return employeeService.updateEmployeeData(employee,file,authentication.getName());
    }
}
