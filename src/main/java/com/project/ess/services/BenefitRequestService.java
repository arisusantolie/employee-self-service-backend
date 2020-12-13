package com.project.ess.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.dto.BenefitRequestDTO;
import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.BenefitBalanceResponse;
import com.project.ess.model.BenefitNeedApproveResponse;
import com.project.ess.model.BenefitRequestResponse;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.repository.BenefitBalanceRepository;
import com.project.ess.repository.BenefitRequestRepository;
import com.project.ess.repository.EmployeeRepository;
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

    @Transactional
    public ResponseEntity<CustomMessageWithRequestNo> createClaimBenefit(String email,String benefitrequest, MultipartFile file){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

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
        benefitRequestEntity.setStatus("PENDING");
        benefitRequestEntity.setRequestDateTime(LocalDateTime.now());
        benefitRequestEntity.setRequestNo("BENEFIT/REQ"+existBenefitBalance.getPeriod()+"/"+ LocalDate.now()+"/"+existBenefitBalance.getBenefitBalanceId());
        UploadFileResponse uploadFileResponse=uploadFileService.storeFile(file);

        benefitRequestEntity.setAttachment(uploadFileResponse.getAttachment());
        benefitRequestEntity.setFileName(uploadFileResponse.getFileName());

        benefitRequestRepository.save(benefitRequestEntity);

        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo("Submit Benefit Claim Successfully",false,benefitRequestEntity.getRequestNo()), HttpStatus.OK);
    }

    public List<BenefitRequestResponse> getListBenefitRequest(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        BenefitBalanceEntity existBenefitBalance=benefitBalanceRepository.findByEmployee(employeeEntity).orElseThrow(
                ()->new CustomGenericException("This Employee Benefit Doesn't Exist")
        );

        List<BenefitRequestResponse> allListBenefit=new ArrayList<>();
        ModelMapper modelMapper=new ModelMapper();
        benefitRequestRepository.findByBenefitBalanceIdOrderByRequestDateTimeDesc(existBenefitBalance).forEach(x->{


            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/v1/downloadFile/")
                    .path(x.getFileName())
                    .toUriString();
            x.setAttachment(fileDownloadUri);
            allListBenefit.add(modelMapper.map(x,BenefitRequestResponse.class));
        });

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
}
