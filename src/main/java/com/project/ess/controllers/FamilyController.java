package com.project.ess.controllers;

import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.model.FamilyResponse;
import com.project.ess.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/family")
public class FamilyController {

    @Autowired
    FamilyService familyService;

    @PostMapping
    public ResponseEntity<CustomMessageWithId> createNewFamily(@RequestParam("family") String family, @RequestParam("file")MultipartFile file, Authentication authentication){

        return familyService.addOrUpdateFamily(family,file,authentication.getName());
    }

    @GetMapping
    public List<FamilyResponse> getListFamily(Authentication authentication){

        return familyService.getListFamily(authentication.getName());
    }
}
