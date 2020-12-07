package com.project.ess.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AbsenceDTO;
import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.AbsenceResponse;
import com.project.ess.model.AddressResponse;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.repository.AbsenceRepository;
import com.project.ess.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class AbsenceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    UploadFileService uploadFileService;

    public ResponseEntity<CustomMessageWithRequestNo> addAbsence(String absence, MultipartFile file,String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

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
        absenceEntity.setStatus("PENDING");
        absenceEntity.setAttachment(uploadFileResponse.getAttachment());
        absenceEntity.setFileName(uploadFileResponse.getFileName());
        absenceEntity.setRequestNo("ABSENCE/"+absenceDTO.getType().replace(" ","-").toUpperCase()+"/"+ LocalDate.now()+"/"+employeeEntity.getEmployeeNo());

        absenceRepository.save(absenceEntity);

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

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/v1/downloadFile/")
                    .path(x.getFileName())
                    .toUriString();
            x.setAttachment(fileDownloadUri);

            allList.add(modelMapper.map(x, AbsenceResponse.class));
        });



        BeanUtils.copyProperties(absenceEntityList,allList);

        return allList;
    }
}
