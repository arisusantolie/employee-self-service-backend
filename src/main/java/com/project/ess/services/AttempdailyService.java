package com.project.ess.services;

import com.project.ess.dto.AttempdailyDTO;
import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.approval.AttempdailyStatus;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.AttempdailyNeedResponse;
import com.project.ess.model.TimesheetResponse;
import com.project.ess.projection.EmploymentBaseProj;
import com.project.ess.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AttempdailyService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttempdailyRepository attempdailyRepository;

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    AttempdailyStatusRepository attempdailyStatusRepository;

    public ResponseEntity<CustomMessageWithRequestNo> checkInNow(AttempdailyDTO request,String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        if(attempdailyRepository.findByEmployeeNoAndActualTimeAndType(employeeEntity.getEmployeeNo(),request.getType(), LocalDateTime.now().toLocalDate()).isPresent()){
            throw new CustomGenericException("You Have Been "+request.getType().substring(0,1).toUpperCase()+request.getType().substring(1).toLowerCase() +" Today");
        }

        EmploymentBaseProj employmentBaseProj=employmentRepository.getEmployment(employeeEntity);

        AttempdailyEntity attempdailyEntity=new AttempdailyEntity();

        attempdailyEntity.setActual_lat(request.getActual_lat());
        attempdailyEntity.setActual_lng(request.getActual_lng());

        attempdailyEntity.setOutOffice(request.getOutOffice());
        attempdailyEntity.setPurpose(request.getPurpose());
        attempdailyEntity.setActualTime(LocalDateTime.now());
        attempdailyEntity.setRemark(request.getRemark());
        attempdailyEntity.setType(request.getType());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");

        String formatDateTime = LocalDateTime.now().format(formatter);

        attempdailyEntity.setRequestNo(request.getType().toUpperCase()+"/"+ formatDateTime+"/"+employeeEntity.getEmployeeNo());
        attempdailyEntity.setEmployeeNo(employeeEntity);

        attempdailyRepository.save(attempdailyEntity);

        AttempdailyStatus attempdailyStatus=new AttempdailyStatus();

        attempdailyStatus.setAttempdailyEntity(attempdailyEntity);
        attempdailyStatus.setStatus("PENDING");
        attempdailyStatus.setManagerId(managerRepository.findById(employmentBaseProj.getManagerId()).get());

        attempdailyStatusRepository.save(attempdailyStatus);

        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo(request.getType().substring(0,1).toUpperCase()+request.getType().substring(1).toLowerCase() +" Today Successfully",false,attempdailyEntity.getRequestNo()), HttpStatus.OK);


    }

    public List<AttempdailyEntity> getListByEmp(String email){


        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        return attempdailyRepository.findByEmployeeNoOrderByActualTime(employeeEntity);
    }

    public List<AttempdailyNeedResponse> getListCheckInCheckOutNeedApprove(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        return attempdailyRepository.getListCheckInCheckOutNeedApprove(employeeEntity);
    }

    public List<AttempdailyNeedResponse> getListCheckInCheckOutHistory(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        return attempdailyRepository.getListCheckInCheckOutHistory(employeeEntity);
    }

    public List<TimesheetResponse> getListTimeSheet(String email,String month,String year){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        List<TimesheetResponse> timesheetResponseList=new ArrayList<>();

        attempdailyRepository.getListTimeSheet(employeeEntity.getEmployeeNo(),month,year).forEach(x->{
            TimesheetResponse  timesheetResponse=new TimesheetResponse();
            timesheetResponse.setCheckInTime(x.get("checkinTime")==null?"-":x.get("checkinTime").toString());
            timesheetResponse.setCheckOutTime(x.get("checkoutTime")==null?"-":x.get("checkoutTime").toString());
            timesheetResponse.setDate(x.get("date").toString());
            timesheetResponse.setStatus(x.get("status").toString());
            timesheetResponse.setRemark(x.get("remark")==null?"-":x.get("remark").toString());

            timesheetResponseList.add(timesheetResponse);
        });

        return timesheetResponseList;
    }

    @Transactional
    public ResponseEntity<CustomMessageWithId> cancelRequestAttempdaily(String requestNo){
        AttempdailyEntity attempdailyEntity=attempdailyRepository.findByRequestNo(requestNo).orElseThrow(
                ()-> new CustomGenericException("Attempdaily Request Doesnt Exist")
        );



        AttempdailyStatus attempdailyStatus=attempdailyStatusRepository.findByAttempdailyEntity(attempdailyEntity);

        if(!attempdailyStatus.getStatus().equals("PENDING")){
            throw new CustomGenericException("Request Attempdaily cant be cancel");
        }

        attempdailyStatusRepository.delete(attempdailyStatus);
        attempdailyRepository.delete(attempdailyEntity);

        return new ResponseEntity<>(new CustomMessageWithId("Attempdaily was canceled",false,null),HttpStatus.OK);
    }
}
