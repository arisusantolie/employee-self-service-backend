package com.project.ess.controllers;

import com.project.ess.dto.ManagerDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping("addmanager")
    public ResponseEntity<CustomMessageWithId> addManagerToDivisi(@RequestBody ManagerDTO request){

        return managerService.addManager(request);
    }
}
