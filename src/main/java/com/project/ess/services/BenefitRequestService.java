package com.project.ess.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.dto.BenefitRequestDTO;
import com.project.ess.entity.BenefitBalanceEntity;
import com.project.ess.entity.BenefitRequestEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.repository.BenefitBalanceRepository;
import com.project.ess.repository.BenefitRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class BenefitRequestService {

    @Autowired
    UploadFileService uploadFileService;


    @Autowired
    BenefitBalanceRepository benefitBalanceRepository;

    @Autowired
    BenefitRequestRepository benefitRequestRepository;


    @Transactional
    public ResponseEntity<CustomMessageWithRequestNo> createClaimBenefit(String benefitrequest, MultipartFile file){


        BenefitRequestDTO request=new BenefitRequestDTO();

        try {
            ObjectMapper objectMapper=new ObjectMapper();
            request=objectMapper.readValue(benefitrequest, BenefitRequestDTO.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        BenefitBalanceEntity existBenefitBalance=benefitBalanceRepository.findById(request.getBenefitBalanceId()).orElseThrow(
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
        benefitRequestEntity.setAttachment(uploadFileService.storeFile(file));

        benefitRequestRepository.save(benefitRequestEntity);

        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo("Submit Benefit Claim Successfully",false,benefitRequestEntity.getRequestNo()), HttpStatus.OK);
    }
}
