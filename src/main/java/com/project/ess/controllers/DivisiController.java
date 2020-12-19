package com.project.ess.controllers;

import com.project.ess.dto.DivisiDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.services.DivisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/divisi")
public class DivisiController {

    @Autowired
    DivisiService divisiService;

    @PostMapping("adddivisi")
    public ResponseEntity<CustomMessageWithId> addDivisi(@RequestBody DivisiDTO request){
        return divisiService.addDivisi(request);
    }
}
