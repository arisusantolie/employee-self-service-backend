package com.project.ess.controllers;

import com.project.ess.dto.AttempdailyDTO;
import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.AttempdailyNeedResponse;
import com.project.ess.repository.AttempdailyRepository;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.services.AttempdailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/attempdaily")
public class AttempdailyController {

    @Autowired
    AttempdailyService attempdailyService;

    @Autowired
    AttempdailyRepository attempdailyRepository;

    @PostMapping
    public ResponseEntity<CustomMessageWithRequestNo> checkIn(@RequestBody AttempdailyDTO request, Authentication authentication){

        return attempdailyService.checkInNow(request,authentication.getName());
    }

    @GetMapping
    public List<AttempdailyEntity> getListAttempEmp(Authentication authentication){

        return attempdailyService.getListByEmp(authentication.getName());
    }

    @GetMapping("/timesheet")
    public List<AttempdailyEntity> getTimeshett(){

        return attempdailyRepository.getTimeSheet(12,2020);
    }

    @GetMapping("/needapprove")
    public List<AttempdailyNeedResponse> getListCheckInCheckOutNeedApprove(Authentication authentication){
        return attempdailyService.getListCheckInCheckOutNeedApprove(authentication.getName());
    }

    @GetMapping("/history")
    public List<AttempdailyNeedResponse> getListCheckInCheckOutHistory(Authentication authentication){
        return attempdailyService.getListCheckInCheckOutHistory(authentication.getName());
    }
}
