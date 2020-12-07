package com.project.ess.controllers;

import com.project.ess.dto.EmployeeDTO;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.model.EmployeeResponse;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/newemployee")
    public EmployeeResponse createNewEmployee(@RequestBody EmployeeDTO request){

        return employeeService.createEmployee(request);
    }

    @GetMapping
    public EmployeeEntity getDetailData(Authentication authentication){
        return employeeRepository.findByEmail(authentication.getName()).orElseThrow(
                ()-> new CustomGenericException("Employee Data Doesn't Exist")
        );
    }
}
