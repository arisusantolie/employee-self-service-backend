package com.project.ess.services;

import com.project.ess.dto.DivisiDTO;
import com.project.ess.entity.DivisiEntity;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.repository.DepartmentRepository;
import com.project.ess.repository.DivisiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DivisiService {

    @Autowired
    DivisiRepository divisiRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public ResponseEntity<CustomMessageWithId> addDivisi(DivisiDTO request){

        DivisiEntity divisiEntity=new DivisiEntity();

        divisiEntity.setDivisiName(request.getDivisiName());
        divisiEntity.setDepartmentEntity(departmentRepository.findById(request.getDepartmentId()).get());
        divisiRepository.save(divisiEntity);

        return new ResponseEntity<>(new CustomMessageWithId("Divisi Successfully Added",false,divisiEntity.getDivisiId()), HttpStatus.OK);
    }
}
