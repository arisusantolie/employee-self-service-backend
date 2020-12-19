package com.project.ess.services;

import com.project.ess.dto.DepartmentDTO;
import com.project.ess.entity.DepartmentEntity;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public ResponseEntity<CustomMessageWithId> addDepartment(DepartmentDTO request){
        DepartmentEntity departmentEntity=new DepartmentEntity();
        departmentEntity.setDepartmentName(request.getDepartmentName());

        departmentRepository.save(departmentEntity);

        return new ResponseEntity<CustomMessageWithId>(new CustomMessageWithId("Department Successfully Added",false,departmentEntity.getDepartmentId()), HttpStatus.OK);
    }
}
