package com.project.ess.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.dto.BenefitApproveDTO;
import com.project.ess.dto.BenefitRequestDTO;
import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.ManagerEntity;
import com.project.ess.entity.approval.BenefitRequestStatus;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.BenefitBalanceResponse;
import com.project.ess.model.BenefitNeedApproveResponse;
import com.project.ess.model.BenefitRequestResponse;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.projection.EmploymentBaseProj;
import com.project.ess.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BenefitRequestService {

    @Autowired
    UploadFileService uploadFileService;


    @Autowired
    BenefitBalanceRepository benefitBalanceRepository;

    @Autowired
    BenefitRequestRepository benefitRequestRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    BenefitRequestStatusRepository benefitRequestStatusRepository;


    @Transactional
    public ResponseEntity<CustomMessageWithRequestNo> createClaimBenefit(String email,String benefitrequest, MultipartFile file){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        EmploymentBaseProj employmentBaseProj=employmentRepository.getEmployment(employeeEntity);

        BenefitRequestDTO request=new BenefitRequestDTO();

        try {
            ObjectMapper objectMapper=new ObjectMapper();
            request=objectMapper.readValue(benefitrequest, BenefitRequestDTO.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }



        BenefitBalanceEntity existBenefitBalance=benefitBalanceRepository.findByEmployeeAndPeriod(employeeEntity,LocalDate.now().getYear()).orElseThrow(
                ()->new CustomGenericException("This Benefit Doesn't Exist")
        );


        BenefitRequestEntity benefitRequestEntity=new BenefitRequestEntity();

        benefitRequestEntity.setBenefitBalanceId(existBenefitBalance);
        benefitRequestEntity.setBenefitReason(request.getBenefitReason());
        benefitRequestEntity.setAmount(request.getAmount());
        benefitRequestEntity.setTransactionDate(LocalDate.parse(request.getTransactionDate()));
        benefitRequestEntity.setRemark(request.getRemark());

        benefitRequestEntity.setRequestDateTime(LocalDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");

        String formatDateTime = LocalDateTime.now().format(formatter);

        benefitRequestEntity.setRequestNo("BENEFIT/REQ"+existBenefitBalance.getPeriod()+"/"+ formatDateTime+"/"+existBenefitBalance.getBenefitBalanceId());
        UploadFileResponse uploadFileResponse=uploadFileService.storeFile(file);

        benefitRequestEntity.setAttachment(uploadFileResponse.getAttachment());
        benefitRequestEntity.setFileName(uploadFileResponse.getFileName());

        benefitRequestRepository.save(benefitRequestEntity);

        BenefitRequestStatus benefitRequestStatus=new BenefitRequestStatus();
        benefitRequestStatus.setBenefitRequestEntity(benefitRequestEntity);
        benefitRequestStatus.setStatus("PENDING");
        benefitRequestStatus.setManagerId(managerRepository.findById(employmentBaseProj.getManagerId()).get());

        benefitRequestStatusRepository.save(benefitRequestStatus);


        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo("Submit Benefit Claim Successfully",false,benefitRequestEntity.getRequestNo()), HttpStatus.OK);
    }

    public List<BenefitRequestResponse> getListBenefitRequest(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        BenefitBalanceEntity existBenefitBalance=benefitBalanceRepository.findByEmployee(employeeEntity).orElseThrow(
                ()->new CustomGenericException("This Employee Benefit Doesn't Exist")
        );

        List<BenefitRequestResponse> allListBenefit=benefitRequestRepository.findByBenefitBalanceIdOrderByRequestDateTimeAsc(existBenefitBalance);


        return allListBenefit;
    }

    public List<BenefitNeedApproveResponse> getListBenefitRequestNeedApprove(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        return benefitRequestRepository.getListBenefitNeedApprove(employeeEntity);
    }

    public List<BenefitNeedApproveResponse> getListBenefitHistoryRequest(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        return benefitRequestRepository.getListBenefitHistory(employeeEntity);
    }

    public ResponseEntity<CustomMessageWithId> cancelRequestBenefitClaim(String requestNo){
        BenefitRequestEntity benefitRequestEntity=benefitRequestRepository.findByRequestNo(requestNo).orElseThrow(
                ()->new CustomGenericException("Benefit request Doesnt exist")
        );
        BenefitRequestStatus benefitRequestStatus=benefitRequestStatusRepository.findByBenefitRequestEntity(benefitRequestEntity);

        System.out.println(benefitRequestStatus.getStatus());

        if(!benefitRequestStatus.getStatus().equalsIgnoreCase("PENDING")){
            throw new CustomGenericException("Claim Request Cant Be cancel");
        }

        benefitRequestStatus.setStatus("CANCELED");
        benefitRequestStatusRepository.save(benefitRequestStatus);

        return new ResponseEntity<>(new CustomMessageWithId("Claim Was Canceled",false,null),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<CustomMessageWithId> approveRequestBenefitClaim(BenefitApproveDTO request,String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        ManagerEntity managerEntity=managerRepository.findByEmployee(employeeEntity).orElseThrow(
                ()-> new CustomGenericException("You Dont Have Manager Roles Access")
        );

        BenefitRequestEntity benefitRequestEntity=benefitRequestRepository.findByRequestNo(request.getRequestNo()).orElseThrow(
                ()->new CustomGenericException("Benefit Request Doesnt Exist")
        );
        BenefitRequestStatus benefitRequestStatus=benefitRequestStatusRepository.findByBenefitRequestEntity(benefitRequestEntity);

        if(!benefitRequestStatus.getStatus().equalsIgnoreCase("PENDING")){
            throw new CustomGenericException("This Request Doesnt exit");
        }

        if(request.getStatus().equalsIgnoreCase("APPROVED")){
            BenefitBalanceEntity benefitBalanceEntity=benefitBalanceRepository.findById(benefitRequestEntity.getBenefitBalanceId().getBenefitBalanceId()).orElseThrow(
                    ()-> new CustomGenericException("This Request Doesnt Have balance")
            );

            benefitBalanceEntity.setBalanceUsed(benefitBalanceEntity.getBalanceUsed().add(benefitRequestEntity.getAmount()));
            benefitBalanceEntity.setBalanceEnd(benefitBalanceEntity.getBalanceLimit().subtract(benefitBalanceEntity.getBalanceUsed()));
            benefitBalanceRepository.save(benefitBalanceEntity);
        }

        benefitRequestStatus.setStatus(request.getStatus());
        benefitRequestStatus.setApprovedDatetime(LocalDateTime.now());
        benefitRequestStatus.setRemark(request.getRemark());

        benefitRequestStatusRepository.save(benefitRequestStatus);

        return new ResponseEntity<>(new CustomMessageWithId(request.getStatus().substring(0,1)+request.getStatus().substring(1).toLowerCase()+" Successfully"
        ,false,null ),HttpStatus.OK);
    }
}
