package com.project.ess.controllers;

import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/address/request")
public class AddressRequestController {

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<CustomMessageWithId> addRequestUpdate(@RequestParam("address") String address,@RequestParam("file") MultipartFile file){


        return addressService.createRequest(address,file);
    }
}
