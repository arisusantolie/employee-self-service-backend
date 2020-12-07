package com.project.ess.services;

import com.project.ess.dto.AttempdailyDTO;
import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.repository.AttempdailyRepository;
import com.project.ess.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AttempdailyService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttempdailyRepository attempdailyRepository;

    public ResponseEntity<CustomMessageWithRequestNo> checkInNow(AttempdailyDTO request,String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        if(attempdailyRepository.findByEmployeeNoAndActualTime(employeeEntity.getEmployeeNo(), LocalDateTime.now().toLocalDate()).isPresent()){
            throw new CustomGenericException("You Have Been Checkin Today");
        }

        AttempdailyEntity attempdailyEntity=new AttempdailyEntity();

        attempdailyEntity.setActual_lat(request.getActual_lat());
        attempdailyEntity.setActual_lng(request.getActual_lng());
        attempdailyEntity.setStatus("PENDING");
        attempdailyEntity.setOutOffice(request.getOutOffice());
        attempdailyEntity.setPurpose(request.getPurpose());
        attempdailyEntity.setActualTime(LocalDateTime.now());
        attempdailyEntity.setRemark(request.getRemark());
        attempdailyEntity.setType(request.getType());
        attempdailyEntity.setRequestNo("CHECKIN/"+ LocalDate.now()+"/"+employeeEntity.getEmployeeNo());
        attempdailyEntity.setEmployeeNo(employeeEntity);

        attempdailyRepository.save(attempdailyEntity);

        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo("Checkin Today Successfully",false,attempdailyEntity.getRequestNo()), HttpStatus.OK);


    }
}
