package com.project.ess.controllers;

import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/absence")
public class AbsenceController {

    @Autowired
    AbsenceService absenceService;

    @PostMapping
    public ResponseEntity<CustomMessageWithRequestNo> addAbsence(@RequestParam("absence") String absence, @RequestParam("file")MultipartFile file, Authentication authentication){

        return absenceService.addAbsence(absence,file,authentication.getName());
    }
}
