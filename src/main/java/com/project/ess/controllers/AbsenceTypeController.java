package com.project.ess.controllers;

import com.project.ess.entity.AbsenceTypeEntity;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.repository.AbsenceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/absencetype")
public class AbsenceTypeController {

    @Autowired
    AbsenceTypeRepository absenceTypeRepository;

    @GetMapping
    public List<AbsenceTypeEntity> getListAbsenceTypeByName(@RequestParam("name") String name){


        return absenceTypeRepository.findByTypeName(name);
    }

    @PostMapping
    public ResponseEntity<CustomMessageWithId> addNewType(@RequestBody AbsenceTypeEntity request){

        AbsenceTypeEntity absenceTypeEntity=new AbsenceTypeEntity();
        absenceTypeEntity.setName(request.getName());
        absenceTypeEntity.setTypeName(request.getTypeName());
        absenceTypeRepository.save(absenceTypeEntity);

        return new ResponseEntity<>(new CustomMessageWithId("Add Absence Type Succesfully",false,absenceTypeEntity.getAbsenceTypeId()), HttpStatus.OK);
    }
}
