package com.project.ess.controllers;

import com.project.ess.dto.HRAdminDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.services.HRAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hradmin")
public class HRAdminController {
    @Autowired
    HRAdminService hrAdminService;

    @PostMapping("addhradmin")
    public ResponseEntity<CustomMessageWithId> addHradmin(@RequestBody HRAdminDTO request){
        return hrAdminService.addHRAdmin(request);
    }
}
