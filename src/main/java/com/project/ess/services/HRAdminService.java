package com.project.ess.services;

import com.project.ess.dto.HRAdminDTO;
import com.project.ess.entity.HRAdminEntity;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.repository.HRAdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HRAdminService {

    @Autowired
    HRAdminRepository hrAdminRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<CustomMessageWithId> addHRAdmin(HRAdminDTO request){

        HRAdminEntity hrAdminEntity=new HRAdminEntity();
        hrAdminEntity.setEmployeeEntity(employeeRepository.findById(request.getEmployeeNo()).get());

        BeanUtils.copyProperties(request,hrAdminEntity);
        hrAdminRepository.save(hrAdminEntity);

        return new ResponseEntity<>(new CustomMessageWithId("HR Admin Successfully Addded",false,hrAdminEntity.getHraId()), HttpStatus.OK);
    }
}
