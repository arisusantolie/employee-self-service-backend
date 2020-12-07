package com.project.ess.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AbsenceDTO;
import com.project.ess.entity.AbsenceEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithRequestNo;
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

        AbsenceEntity absenceEntity=new AbsenceEntity();
        BeanUtils.copyProperties(absenceDTO,absenceEntity);
        absenceEntity.setAmount(Integer.parseInt(absenceDTO.getAmount()));
        absenceEntity.setStartDate(LocalDate.parse(absenceDTO.getStartDate()));
        absenceEntity.setEndDate(LocalDate.parse(absenceDTO.getEndDate()));
        absenceEntity.setEmployeeNo(employeeEntity);
        absenceEntity.setRequestDateTime(LocalDateTime.now());
        absenceEntity.setStatus("PENDING");
        absenceEntity.setAttachment(uploadFileService.storeFile(file));
        absenceEntity.setRequestNo("ABSENCE/"+absenceDTO.getType().replace(" ","-").toUpperCase()+"/"+ LocalDate.now()+"/"+employeeEntity.getEmployeeNo());

        absenceRepository.save(absenceEntity);

        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo("Submit Absence Succesfully",false,absenceEntity.getRequestNo()), HttpStatus.OK);
    }
}
