package com.project.ess.controllers;

import com.project.ess.dto.RoleDTO;
import com.project.ess.entity.UserEntity;
import com.project.ess.entity.security.AuthorityEntity;
import com.project.ess.entity.security.RoleEntity;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.repository.AuthorityRepository;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.repository.RolesRepository;
import com.project.ess.repository.UserRepository;
import com.project.ess.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/roles")
public class RolesController {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RoleServices roleServices;



    @PostMapping
    public ResponseEntity<CustomMessageWithId> createRoles(@RequestBody RoleEntity request){

        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setName(request.getName());
        rolesRepository.save(roleEntity);

        return new ResponseEntity<>(new CustomMessageWithId("Submit Role Succesfully",false,roleEntity.getId()), HttpStatus.OK);
    }

    @PostMapping("addroles")
    public ResponseEntity<CustomMessageWithId> addRoles(@RequestBody RoleDTO request){

        return roleServices.addroles(request);


    }




}
