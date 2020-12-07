package com.project.ess.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AddressDTO;
import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.entity.AddressEntity;
import com.project.ess.entity.AddressRequestEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.ErrorMessage;
import com.project.ess.model.AddressResponse;
import com.project.ess.model.jsondata.AddressRequestJsonData;
import com.project.ess.repository.AddressRepository;
import com.project.ess.repository.AddressRequestRepository;
import com.project.ess.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRequestRepository addressRequestRepository;

    @Autowired
    UploadFileService uploadFileService;



    @Transactional
    public AddressResponse addAddress(String address,MultipartFile file,String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        AddressRequestDTO request=new AddressRequestDTO();

        try {
            ObjectMapper objectMapper=new ObjectMapper();
            request=objectMapper.readValue(address,AddressRequestDTO.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }


        if(request.getPrimaryFlag()==true){
            addressRepository.setPrimaryFlagAddressFalse(employeeEntity.getEmployeeNo());
        }

        AddressEntity addressEntity=new AddressEntity();
        addressEntity.setAddress(request.getAddress());
        addressEntity.setCity(request.getCity());
        addressEntity.setCountry(request.getCountry());
        addressEntity.setPrimaryFlag(request.getPrimaryFlag());
        addressEntity.setProvince(request.getProvince());
        addressEntity.setCountry(request.getCountry());
        addressEntity.setStayStatus(request.getStayStatus());
        addressEntity.setType(request.getType());
        addressEntity.setZipCode(request.getZipCode());
        addressEntity.setEmployee(employeeEntity);

        addressRepository.save(addressEntity);

        AddressRequestJsonData addressRequestJsonData=new AddressRequestJsonData();
        AddressRequestEntity addressRequestEntity=new AddressRequestEntity();

        addressRequestEntity.setAddressId(addressEntity);
        addressRequestEntity.setStatus("PENDING");
        addressRequestEntity.setRequestDateTime(LocalDateTime.now());
        addressRequestEntity.setRequestData(addressRequestJsonData.toString());
        addressRequestEntity.setAttachmentPath(uploadFileService.storeFile(file));

        addressRequestRepository.save(addressRequestEntity);

        AddressResponse returnValue=new AddressResponse();
        BeanUtils.copyProperties(addressEntity,returnValue);
        returnValue.setStatus(addressRequestEntity.getStatus());

        return returnValue;
    }

    @Transactional
    public ResponseEntity<CustomMessageWithId> createRequest(String address, MultipartFile file){

        AddressRequestDTO request=new AddressRequestDTO();

        try {
            ObjectMapper objectMapper=new ObjectMapper();
            request=objectMapper.readValue(address,AddressRequestDTO.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        AddressEntity addressEntity=addressRepository.findById(request.getAddressId()).orElseThrow(
                ()->new CustomGenericException("address Doesnt Exist")
        );

        if(request.getPrimaryFlag()==true){
            addressRepository.setPrimaryFlagAddressFalse(addressEntity.getEmployee().getEmployeeNo());
        }

        AddressRequestEntity addressRequestEntity=new AddressRequestEntity();
        AddressRequestJsonData addressRequestJsonData=new AddressRequestJsonData();

        BeanUtils.copyProperties(addressEntity,addressRequestJsonData);
        addressRequestJsonData.setNewAdddress(request.getAddress());
        addressRequestJsonData.setNewCity(request.getCity());
        addressRequestJsonData.setNewCountry(request.getCountry());
        addressRequestJsonData.setNewPrimaryFlag(request.getPrimaryFlag());
        addressRequestJsonData.setNewProvince(request.getProvince());
        addressRequestJsonData.setNewStayStatus(request.getStayStatus());
        addressRequestJsonData.setNewType(request.getType());
        addressRequestJsonData.setNewZipCode(request.getZipCode());


        addressRequestEntity.setAddressId(addressEntity);
        addressRequestEntity.setStatus("PENDING");
        addressRequestEntity.setRequestDateTime(LocalDateTime.now());
        addressRequestEntity.setRequestData(addressRequestJsonData.toString());
        addressRequestEntity.setAttachmentPath(uploadFileService.storeFile(file));

//        System.out.println(addressRequestJsonData.toString());

        addressRequestRepository.save(addressRequestEntity);

        BeanUtils.copyProperties(request,addressEntity);
//        System.out.println(addressEntity);
        addressRepository.save(addressEntity);


        return new ResponseEntity<CustomMessageWithId>(new CustomMessageWithId("Berhasil",false,addressEntity.getAddressId()), HttpStatus.OK);
    }

    public List<AddressResponse> getAllListAddressByEmployee(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        List<AddressResponse> allList=new ArrayList<>();

        addressRepository.getAllListAddress(employeeEntity.getEmployeeNo()).forEach(
               x->{
                   AddressResponse addressResponse=new AddressResponse();
                   addressResponse.setAddressId(Long.parseLong(x.get("address_id").toString()));
                   addressResponse.setAddress(x.get("address").toString());
                   addressResponse.setCity(x.get("city").toString());
                   addressResponse.setCountry(x.get("country").toString());
                   addressResponse.setPrimaryFlag(Boolean.valueOf(x.get("primary_flag").toString()));
                   addressResponse.setProvince(x.get("province").toString());
                   addressResponse.setStayStatus(x.get("stay_status").toString());
                   addressResponse.setType(x.get("type").toString());
                   addressResponse.setZipCode(x.get("zip_code").toString());
                   addressResponse.setStatus(x.get("status").toString());


                   allList.add(addressResponse);
               }
        );

        return allList;
    }
}
