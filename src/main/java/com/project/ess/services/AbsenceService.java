package com.project.ess.services;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AbsenceDTO;
import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.approval.AbsenceStatus;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.AbsenceNeedApproveResponse;
import com.project.ess.model.AbsenceResponse;
import com.project.ess.model.AddressResponse;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.projection.EmploymentBaseProj;
import com.project.ess.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;

@Service
public class AbsenceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    AbsenceStatusRepository absenceStatusRepository;

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    ManagerRepository managerRepository;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime timenow=LocalDateTime.now();

    @Autowired
    AbsenceTypeRepository absenceTypeRepository;

    @Transactional
    public ResponseEntity<CustomMessageWithRequestNo> addAbsence(String absence, MultipartFile file,String email){


        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );
        EmploymentBaseProj employmentBaseProj=employmentRepository.getEmployment(employeeEntity);
        AbsenceDTO absenceDTO=new AbsenceDTO();

        try{
            ObjectMapper objectMapper=new ObjectMapper();
            absenceDTO=objectMapper.readValue(absence,AbsenceDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadFileResponse uploadFileResponse= uploadFileService.storeFile(file);

        AbsenceEntity absenceEntity=new AbsenceEntity();
        BeanUtils.copyProperties(absenceDTO,absenceEntity);
        absenceEntity.setAmount(Integer.parseInt(absenceDTO.getAmount()));
        absenceEntity.setStartDate(LocalDate.parse(absenceDTO.getStartDate()));
        absenceEntity.setEndDate(LocalDate.parse(absenceDTO.getEndDate()));
        absenceEntity.setEmployeeNo(employeeEntity);
        absenceEntity.setRequestDateTime(LocalDateTime.now());

        absenceEntity.setAttachment(uploadFileResponse.getAttachment());
        absenceEntity.setFileName(uploadFileResponse.getFileName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");

        String formatDateTime = LocalDateTime.now().format(formatter);
        absenceEntity.setRequestNo("ABSENCE/"+absenceDTO.getType().replace(" ","-").toUpperCase()+"/"+formatDateTime+"/"+employeeEntity.getEmployeeNo());


        absenceEntity.setAbsenceTypeEntity(absenceTypeRepository.findById(absenceDTO.getAbsenceTypeId()).get());
        absenceRepository.save(absenceEntity);
        AbsenceStatus absenceStatus=new AbsenceStatus();


        absenceStatus.setAbsenceEntity(absenceEntity);
        absenceStatus.setStatus("PENDING");
        absenceStatus.setManagerId(managerRepository.findById(employmentBaseProj.getManagerId()).get());

        absenceStatusRepository.save(absenceStatus);

        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo("Submit Absence Succesfully",false,absenceEntity.getRequestNo()), HttpStatus.OK);
    }

    public List<AbsenceResponse> getDataAbsence(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        List<AbsenceEntity> absenceEntityList=absenceRepository.findByEmployeeNoOrderByRequestDateTimeDesc(employeeEntity);
        List<AbsenceResponse> allList=new ArrayList<>();
        ModelMapper modelMapper=new ModelMapper();

        absenceEntityList.forEach(x->{
            AbsenceStatus absenceStatus=absenceStatusRepository.findByAbsenceEntity(x);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/v1/downloadFile/")
                    .path(x.getFileName())
                    .toUriString();
            x.setAttachment(fileDownloadUri);

            AbsenceResponse absenceResponse=modelMapper.map(x, AbsenceResponse.class);
            BeanUtils.copyProperties(absenceStatus,absenceResponse);

            allList.add(absenceResponse);
        });



        BeanUtils.copyProperties(absenceEntityList,allList);

        return allList;
    }

    public List<AbsenceNeedApproveResponse> getListNeedApprove(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        return absenceRepository.getListAbsenceNeedApprove(employeeEntity.getEmployeeNo());
    }

    public List<AbsenceNeedApproveResponse> getListHistoryAbsence(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        return absenceRepository.getListHistoryAbsence(employeeEntity.getEmployeeNo());
    }

    public ResponseEntity<CustomMessageWithId> cancelRequestAbsence(String requestNo){

        AbsenceStatus absenceStatus=absenceStatusRepository.findByAbsenceEntity(absenceRepository.findByRequestNo(requestNo));

        if(!absenceStatus.getStatus().equalsIgnoreCase("PENDING")){
            throw new CustomGenericException("This Absence Cant be cancel");
        }

        absenceStatus.setStatus("CANCEL");

        absenceStatusRepository.save(absenceStatus);

        return new ResponseEntity<>(new CustomMessageWithId("Request Cancel Successfully",false,null),HttpStatus.OK);
    }


}
