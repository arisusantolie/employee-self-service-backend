package com.project.ess.controllers;

import com.project.ess.dto.EmploymentDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.projection.EmploymentBaseProj;
import com.project.ess.services.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employment")
public class EmploymentController {

    @Autowired
    EmploymentService employmentService;

    @PostMapping
    public ResponseEntity<CustomMessageWithId> createEmployment(@RequestBody EmploymentDTO request){

        return employmentService.addManagerForEmployee(request);
    }

    @GetMapping
    public EmploymentBaseProj getEmployment(Authentication authentication){

        return employmentService.getEmploymentByEmployee(authentication.getName());
    }
}
