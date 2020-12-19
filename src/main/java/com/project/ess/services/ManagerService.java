package com.project.ess.services;

import com.project.ess.dto.ManagerDTO;
import com.project.ess.entity.ManagerEntity;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.repository.DepartmentRepository;
import com.project.ess.repository.DivisiRepository;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.repository.ManagerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    DivisiRepository divisiRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<CustomMessageWithId> addManager(ManagerDTO request){
        ManagerEntity managerEntity=new ManagerEntity();

        BeanUtils.copyProperties(request,managerEntity);

        managerEntity.setDivisiEntity(divisiRepository.findById(request.getDivisiId()).get());
        managerEntity.setEmployeeEntity(employeeRepository.findById(request.getEmployeeNo()).get());

        managerRepository.save(managerEntity);

        return new ResponseEntity<CustomMessageWithId>(new CustomMessageWithId("Manager Added Successfully",false,managerEntity.getManagerId()), HttpStatus.OK);
    }
}
