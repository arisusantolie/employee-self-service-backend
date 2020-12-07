package com.project.ess.controllers;

import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/family/request")
public class FamilyRequestController {
    @Autowired
    FamilyService familyService;

    @PostMapping
    public ResponseEntity<CustomMessageWithId> updateNewFamily(@RequestParam("family") String family, @RequestParam("file") MultipartFile file, Authentication authentication){

        return familyService.addOrUpdateFamily(family,file,authentication.getName());
    }
}
