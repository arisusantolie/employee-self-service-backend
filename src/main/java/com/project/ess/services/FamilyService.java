package com.project.ess.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.project.ess.dto.FamilyDTO;
import com.project.ess.dto.FamilyRequestApproveDTO;
import com.project.ess.entity.*;
import com.project.ess.entity.approval.AddressRequestStatus;
import com.project.ess.entity.approval.FamilyRequestStatus;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.FamilyNeedApproveResponse;
import com.project.ess.model.FamilyResponse;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.model.jsondata.FamilyRequestJsonData;
import com.project.ess.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Autowired
    FamilyRequestRepository familyRequestRepository;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    FamilyRequestStatusRepository familyRequestStatusRepository;

    @Autowired
    HRAdminRepository hrAdminRepository;

    @Transactional
    public ResponseEntity<CustomMessageWithRequestNo> addOrUpdateFamily(String family, MultipartFile file, String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        FamilyEntity familyEntity=null;
        FamilyDTO request=new FamilyDTO();

        try {
            ObjectMapper objectMapper=new ObjectMapper();
            request=objectMapper.readValue(family, FamilyDTO.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        String message=null;

        if( request.getFamilyId()==null){
            familyEntity=new FamilyEntity();
            message="Submit New Family Successfulyy";
        }else{
            familyEntity=familyRepository.findById(request.getFamilyId()).orElseThrow(
                    ()->new CustomGenericException("Family doesn't exist")
            );
            message="Update Family Successfulyy";
        }
        FamilyRequestJsonData familyRequestJsonData=new FamilyRequestJsonData();

        BeanUtils.copyProperties(familyEntity,familyRequestJsonData);
        familyRequestJsonData.setBirthDate(familyEntity.getBirthDate()==null ? null  :  familyEntity.getBirthDate().toString());
        familyRequestJsonData.setMarriedDate(familyEntity.getMarriedDate()==null ? null  :familyEntity.getMarriedDate().toString());


        BeanUtils.copyProperties(request,familyEntity);
        familyEntity.setMarriedDate(request.getMarriedDate()==null ? null : LocalDate.parse(request.getMarriedDate()));
        familyEntity.setBirthDate(request.getBirthDate()==null ? null : LocalDate.parse(request.getBirthDate()));
        familyEntity.setEmployee(employeeEntity);
        familyRepository.save(familyEntity);





        familyRequestJsonData.setNewFullName(request.getFullName());
        familyRequestJsonData.setNewBirthDate(request.getBirthDate());
        familyRequestJsonData.setNewGender(request.getGender());
        familyRequestJsonData.setNewMaritalStatus(request.getMaritalStatus());
        familyRequestJsonData.setNewMarriedDate(request.getMarriedDate());
        familyRequestJsonData.setNewRelationship(request.getRelationship());
        familyRequestJsonData.setNewEmail(request.getEmail());
        familyRequestJsonData.setNewMobilePhone(request.getMobilePhone());
        familyRequestJsonData.setNewBloodType(request.getBloodType());
        familyRequestJsonData.setNewFamilyCardNo(request.getFamilyCardNo());
        familyRequestJsonData.setNewKtpNo(request.getKtpNo());
        familyRequestJsonData.setNewAliveStatus(request.getAliveStatus());
        familyRequestJsonData.setNewNationality(request.getNationality());
        familyRequestJsonData.setNewAddress(request.getAddress());
        familyRequestJsonData.setNewCity(request.getCity());
        familyRequestJsonData.setNewZipCode(request.getZipCode());
        familyRequestJsonData.setNewBirthPlace(request.getBirthPlace());


        FamilyRequestEntity familyRequestEntity=new FamilyRequestEntity();
        familyRequestEntity.setFamilyId(familyEntity);
//        familyRequestEntity.setStatus("PENDING");
        familyRequestEntity.setRequestDateTime(LocalDateTime.now());
        familyRequestEntity.setRequestData(familyRequestJsonData.toString());
        UploadFileResponse uploadFileResponse=uploadFileService.storeFile(file);

        familyRequestEntity.setAttachmentPath(uploadFileResponse.getAttachment());
        familyRequestEntity.setFileName(uploadFileResponse.getFileName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");

        String formatDateTime = LocalDateTime.now().format(formatter);
        familyRequestEntity.setRequestNo("FAMILY/REQ/"+formatDateTime+"/"+familyEntity.getFamilyId());

        familyRequestRepository.save(familyRequestEntity);

        FamilyRequestStatus familyRequestStatus=new FamilyRequestStatus();
        familyRequestStatus.setFamilyRequestEntity(familyRequestEntity);
        familyRequestStatus.setStatus("PENDING");
        familyRequestStatusRepository.save(familyRequestStatus);


        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo(message,false,familyRequestEntity.getRequestNo()), HttpStatus.OK);
    }


    public List<FamilyResponse> getListFamily(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        List<FamilyResponse> listFamily=new ArrayList<>();

        System.out.println(employeeEntity.getEmployeeNo());
        familyRepository.getFamilyListByEmployeeNo(employeeEntity.getEmployeeNo()).forEach(x->{
            FamilyResponse familyResponse=new FamilyResponse();

            familyResponse.setFamilyId(Long.parseLong(x.get("family_id").toString()));
            familyResponse.setFullName(x.get("full_name").toString());
            familyResponse.setBirthDate(x.get("birth_date").toString());
            familyResponse.setGender(x.get("gender").toString());
            familyResponse.setMaritalStatus(x.get("marital_status").toString());
            familyResponse.setMarriedDate(x.get("married_date")==null ? "-" : x.get("married_date").toString());
            familyResponse.setRelationship(x.get("relationship").toString());
            familyResponse.setEmail(x.get("email").toString());
            familyResponse.setMobilePhone(x.get("mobile_phone").toString());
            familyResponse.setBloodType(x.get("blood_type").toString());
            familyResponse.setFamilyCardNo(Long.parseLong(x.get("family_card_no").toString()));
            familyResponse.setKtpNo(Long.parseLong(x.get("ktp_no").toString()));
            familyResponse.setAliveStatus(x.get("alive_status").toString());
            familyResponse.setNationality(x.get("nationality").toString());
            familyResponse.setAddress(x.get("address").toString());
            familyResponse.setCity(x.get("city").toString());
            familyResponse.setZipCode(x.get("zip_code").toString());
            familyResponse.setBirthPlace(x.get("birth_place").toString());
            familyResponse.setStatus(x.get("status").toString());

            listFamily.add(familyResponse);

        });

        return listFamily;

    }

    public List<FamilyNeedApproveResponse> getListFamilyRequest(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        return familyRequestRepository.getListFamilyNeedApprove(employeeEntity);
    }

    public List<FamilyNeedApproveResponse> getListFamNeedApprove(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        return familyRequestRepository.getListFamilyNeedApprove(employeeEntity);
    }

    public List<FamilyNeedApproveResponse> getHistoryFamRequest(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        return familyRequestRepository.getListHistoryFamilyRequest(employeeEntity);
    }

    @Transactional
    public ResponseEntity<CustomMessageWithId> cancelFamilyRequest(String requestNo,String status,HRAdminEntity hrAdminEntity,String remark) throws JsonProcessingException {
        FamilyRequestEntity familyRequestEntity=familyRequestRepository.findByRequestNo(requestNo).orElseThrow(
                ()-> new CustomGenericException("Family Request Doesnt Exist")
        );
        FamilyEntity familyEntity=familyRepository.findById(familyRequestEntity.getFamilyId().getFamilyId()).get();
        FamilyRequestStatus familyRequestStatus=familyRequestStatusRepository.findByFamilyRequestEntity(familyRequestEntity);

        if(!familyRequestStatus.getStatus().equalsIgnoreCase("PENDING")){
            throw new CustomGenericException("Request Cant be "+status.substring(0,1)+status.substring(1).toLowerCase());
        }
        ObjectMapper objectMapper=new ObjectMapper();

        FamilyRequestJsonData familyRequestJsonData=objectMapper.readValue(familyRequestEntity.getRequestData(),FamilyRequestJsonData.class);



        if(familyRequestJsonData.getFamilyId()==null){
            if(hrAdminEntity==null){ //untuk user pas cancel
                familyRequestStatusRepository.deleteFamilyRequesStatusByFamilyReqEntity(familyRequestEntity);
                familyRequestRepository.deleteFamilyRequestByRequestNo(requestNo);
                familyRepository.deleteFamilyById(familyRequestEntity.getFamilyId().getFamilyId());
            }else{ //untuk admin
                familyRequestStatus.setStatus(status);
                familyRequestStatus.setApprovedDatetime(LocalDateTime.now());
                familyRequestStatus.setRemark(remark);
                familyRequestStatus.setHraId(hrAdminEntity);
                familyRequestStatusRepository.save(familyRequestStatus);
            }

        }else{
            BeanUtils.copyProperties(familyRequestJsonData,familyEntity);
            familyRepository.save(familyEntity);
            familyRequestStatus.setStatus(status);

            if(hrAdminEntity!=null){
                familyRequestStatus.setApprovedDatetime(LocalDateTime.now());
                familyRequestStatus.setRemark(remark);
                familyRequestStatus.setHraId(hrAdminEntity);
            }
            familyRequestStatusRepository.save(familyRequestStatus);


        }

        return new ResponseEntity<>(new CustomMessageWithId("Family Request Was Canceled",false,null),HttpStatus.OK);


    }

    @Transactional
    public ResponseEntity<CustomMessageWithId> approveFamilyRequest(FamilyRequestApproveDTO request, String email) throws JsonProcessingException {
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        HRAdminEntity hrAdminEntity=hrAdminRepository.findByEmployee(employeeEntity).orElseThrow(
                ()-> new CustomGenericException("U dont Have access")
        );

        FamilyRequestEntity familyRequestEntity=familyRequestRepository.findByRequestNo(request.getRequestNo()).orElseThrow(
                ()->new CustomGenericException("Request Doesnt exist")
        );
        FamilyRequestStatus familyRequestStatus=familyRequestStatusRepository.findByFamilyRequestEntity(familyRequestEntity);


        if(!familyRequestStatus.getStatus().equals("PENDING")){
            throw new CustomGenericException("Family Request Cant be "+request.getStatus().substring(0,1)+request.getStatus().substring(1).toLowerCase());
        }

        if(request.getStatus().equalsIgnoreCase("REJECTED")){
            cancelFamilyRequest(request.getRequestNo(), request.getStatus(),hrAdminEntity,request.getRemark());
        }else{
            familyRequestStatus.setStatus(request.getStatus());
            familyRequestStatus.setApprovedDatetime(LocalDateTime.now());
            familyRequestStatus.setRemark(request.getRemark());
            familyRequestStatus.setHraId(hrAdminEntity);
            familyRequestStatusRepository.save(familyRequestStatus);
        }

        return new ResponseEntity<>(new CustomMessageWithId(request.getStatus().substring(0,1)+request.getStatus().substring(1).toLowerCase()+" Request Successfully",false,null),HttpStatus.OK);

    }
}
