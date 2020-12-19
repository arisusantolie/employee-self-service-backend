package com.project.ess.services;

import com.project.ess.dto.EmploymentDTO;
import com.project.ess.entity.DivisiEntity;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.EmploymentEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.projection.EmploymentBaseProj;
import com.project.ess.repository.DivisiRepository;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmploymentService {

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DivisiRepository divisiRepository;

    public ResponseEntity<CustomMessageWithId> addManagerForEmployee(EmploymentDTO request){

        EmploymentEntity employmentEntity=new EmploymentEntity();

        EmployeeEntity employee=employeeRepository.findById(request.getEmployeeNo()).orElseThrow(
                ()-> new CustomGenericException("This employee Doesn't exist")
        );

        DivisiEntity divisiEntity=divisiRepository.findById(request.getDivisiId()).orElseThrow(
                ()-> new CustomGenericException("This Division Doesn't exist")
        );

        employmentEntity.setEmployee(employee);
        employmentEntity.setDivisiEntity(divisiEntity);
//        employmentEntity.setEmployeeDirectToId(employeeManager);
        employmentEntity.setEmployeeStatus(request.getEmployeeStatus());
//        employmentEntity.setDepartment(request.getDepartment());
        employmentEntity.setPosition(request.getPosition());
        employmentEntity.setStartDate(request.getStartDate());
        employmentEntity.setEndDate(request.getEndDate());

        employmentRepository.save(employmentEntity);





        return new ResponseEntity<CustomMessageWithId>(new CustomMessageWithId("Add Employment Successfully",false,employmentEntity.getEmploymentId()), HttpStatus.OK);

    }

    public EmploymentBaseProj getEmploymentByEmployee(String email){
        EmployeeEntity employee=employeeRepository.findByEmail(email).orElseThrow(
                ()-> new CustomGenericException("This employee Doesn't exist")
        );

        return employmentRepository.getEmployment(employee);
    }
}
