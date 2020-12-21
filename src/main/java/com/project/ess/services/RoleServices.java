package com.project.ess.services;

import com.project.ess.dto.RoleDTO;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.UserEntity;
import com.project.ess.entity.security.AuthorityEntity;
import com.project.ess.entity.security.RoleEntity;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.repository.AuthorityRepository;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.repository.RolesRepository;
import com.project.ess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class RoleServices {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public ResponseEntity<CustomMessageWithId> addroles(RoleDTO request){

        AuthorityEntity readAuthority= createAuthority("READ_AUTHORITY");
        AuthorityEntity writeAuthority= createAuthority("WRITE_AUTHORITY");
        AuthorityEntity deleteAuthority= createAuthority("DELETE_AUTHORITY");

        EmployeeEntity employeeEntity=employeeRepository.findById(request.getEmployeeNo()).get();



        UserEntity userEntity= userRepository.findByEmail(employeeEntity.getEmail());

        System.out.println(userEntity.getEmail());

//        AuthorityEntity readAuthority=authorityRepository.findByName("READ_AUTHORITY");
//
//        if(readAuthority==null){
//            readAuthority = new AuthorityEntity("READ_AUTHORITY");
//            authorityRepository.save(readAuthority);
//        }
//
//        AuthorityEntity writeAuthority=authorityRepository.findByName("WRITE_AUTHORITY");
//
//        if(writeAuthority==null){
//            writeAuthority = new AuthorityEntity("WRITE_AUTHORITY");
//            authorityRepository.save(writeAuthority);
//        }
//
//        AuthorityEntity deleteAuthority=authorityRepository.findByName("DELETE_AUTHORITY");
//
//        if(deleteAuthority==null){
//            deleteAuthority = new AuthorityEntity("DELETE_AUTHORITY");
//            authorityRepository.save(deleteAuthority);
//        }

//        RoleEntity roleEntity= createRole(request.getName(), Arrays.asList(readAuthority,writeAuthority,deleteAuthority));

        List<RoleEntity> roleEntityList=new ArrayList<>();
        rolesRepository.getListExistRoles(userEntity.getId()).forEach(x->{
            RoleEntity roleEntity=rolesRepository.findById(Long.parseLong(x.get("roles_id").toString())).get();

            roleEntityList.add(roleEntity);

        });

        roleEntityList.add(rolesRepository.findById(request.getRoleId()).get());


        userEntity.setRoles(roleEntityList);

        userRepository.save(userEntity);

        return new ResponseEntity<>(new CustomMessageWithId("Success Added user role",false,null), HttpStatus.OK);
    }



    private AuthorityEntity createAuthority(String name){
        AuthorityEntity authorityEntity=authorityRepository.findByName(name);

        if(authorityEntity==null){
            authorityEntity = new AuthorityEntity(name);
            authorityRepository.save(authorityEntity);
        }
        return authorityEntity;
    }


    private RoleEntity createRole(String name,
                                  List<AuthorityEntity> authorities){
        RoleEntity roleEntity=rolesRepository.findByName(name);

        if(roleEntity==null){
            roleEntity = new RoleEntity(name);
            roleEntity.setAuthorities(authorities);
            rolesRepository.save(roleEntity);
        }
        return roleEntity;
    }
}
