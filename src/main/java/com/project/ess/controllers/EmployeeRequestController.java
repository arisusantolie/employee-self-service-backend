package com.project.ess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.ess.dto.EmployeeRequestApproveDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.EmployeeNeedApproveResponse;
import com.project.ess.model.EmployeeRequestResponse;
import com.project.ess.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee/request")
public class EmployeeRequestController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<CustomMessageWithRequestNo> updateDataEmp(@RequestParam("employee") String employee, @RequestParam(value = "file",required = false) MultipartFile file, Authentication authentication){

        return employeeService.updateEmployeeData(employee,file,authentication.getName());
    }

    @GetMapping
    public List<EmployeeRequestResponse> getListRequestEmp(Authentication authentication){

        return employeeService.getListEmployeeRequest(authentication.getName());
    }

    @GetMapping("/needapprove")
    public List<EmployeeNeedApproveResponse> getListEmployeeRequestNeedApprove(Authentication authentication){


        return employeeService.getAllEmpRequestNeedApprove(authentication.getName());
    }

    @GetMapping("/history")
    public List<EmployeeNeedApproveResponse> getListEmployeeRequestHistory(Authentication authentication){


        return employeeService.getAllEmpRequestHistory(authentication.getName());
    }

    @GetMapping("cancel")
    public ResponseEntity<CustomMessageWithId> cancelRequestEmpBio(@RequestParam("requestNo") String requestNo) throws JsonProcessingException {


        return employeeService.cancelEmployeeRequest(requestNo,"CANCELED",null,null);
    }

    @PostMapping("approve")
    public ResponseEntity<CustomMessageWithId> approveRequestEmpBio(@RequestBody EmployeeRequestApproveDTO request,Authentication authentication) throws JsonProcessingException {

        return employeeService.approveEmpRequest(request,authentication.getName());
    }
}
