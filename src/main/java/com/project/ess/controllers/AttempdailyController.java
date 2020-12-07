package com.project.ess.controllers;

import com.project.ess.dto.AttempdailyDTO;
import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.repository.AttempdailyRepository;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.services.AttempdailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("api/v1/attempdaily")
public class AttempdailyController {

    @Autowired
    AttempdailyService attempdailyService;

    @PostMapping
    public ResponseEntity<CustomMessageWithRequestNo> checkIn(@RequestBody AttempdailyDTO request, Authentication authentication){

        return attempdailyService.checkInNow(request,authentication.getName());
    }
}
