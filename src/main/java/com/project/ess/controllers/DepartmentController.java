package com.project.ess.controllers;

import com.project.ess.dto.DepartmentDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("adddepartment")
    public ResponseEntity<CustomMessageWithId> addDepartment(@RequestBody DepartmentDTO request){

        return departmentService.addDepartment(request);
    }
}
