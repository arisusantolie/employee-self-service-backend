package com.project.ess.controllers;

import com.project.ess.dto.AddressDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.model.AddressResponse;
import com.project.ess.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping()
    public AddressResponse addNewAddress(@RequestParam("address") String addess, @RequestParam("file")MultipartFile file, Authentication authentication){

        return addressService.addAddress(addess,file, authentication.getName());
    }



    @GetMapping()
    public List<AddressResponse> getListAddress(Authentication authentication){


        return addressService.getAllListAddressByEmployee(authentication.getName());
    }
}
