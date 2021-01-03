package com.project.ess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.ess.dto.AddressApproveDTO;
import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.model.AddressNeedApproveResponse;
import com.project.ess.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/address/request")
public class AddressRequestController {

    @Autowired
    AddressService addressService;

    @PostMapping("edit")
    public ResponseEntity<CustomMessageWithId> addRequestUpdate(@RequestParam("address") String address,@RequestParam("file") MultipartFile file){

        return addressService.createRequest(address,file);
    }
    @GetMapping()
    public List<AddressNeedApproveResponse> getListAddressRequest(Authentication authentication){
        return addressService.getListAddreesRequest(authentication.getName());
    }

    @GetMapping("needapprove")
    public List<AddressNeedApproveResponse> getListAddressNeedApproveByManager(Authentication authentication){
        System.out.println("masuk");
        return addressService.getListAddreesNeedApprove(authentication.getName());
    }

    @GetMapping("/history") //untuk history approval untuk admin, bukan employee biasa
    public List<AddressNeedApproveResponse> getListAddressHistory(Authentication authentication){
        return addressService.getListAddreesHistory(authentication.getName());
    }

    @GetMapping("cancel")
    public ResponseEntity<CustomMessageWithId> cancelRequestAddress(@RequestParam("requestNo") String requestNo) throws JsonProcessingException {

        return addressService.cancelRequestAddressEmployee(requestNo,"CANCELED",null,null);
    }

    @PostMapping("approve")
    public ResponseEntity<CustomMessageWithId> approveRequestAddress(@RequestBody AddressApproveDTO request,Authentication authentication) throws JsonProcessingException {


        return addressService.approveAddressRequest(request,authentication.getName());
    }
}
