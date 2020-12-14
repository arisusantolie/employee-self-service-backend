package com.project.ess.controllers;

import com.project.ess.dto.ChangePasswordDTO;
import com.project.ess.entity.EmployeeEntity;
import com.project.ess.entity.UserEntity;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.repository.UserRepository;
import com.project.ess.services.UploadFileService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UploadFileService uploadFileService;

    @PostMapping("/changepassword")
    public ResponseEntity<CustomMessageWithId> changePasswordUser(@RequestBody ChangePasswordDTO request, Authentication authentication){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(authentication.getName()).orElseThrow(
                ()-> new CustomGenericException("Employee Doesn't Exist")
        );


        UserEntity userEntity=userRepository.findByEmail(employeeEntity.getEmail());

        if(bCryptPasswordEncoder.matches(request.getOldPassword(),userEntity.getPassword())){
            if(request.getNewPassword().equals(request.getConfirmationPassword())){
                userEntity.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
            }else {
                throw new CustomGenericException("New Password and Confirmation Password Doesn't Match");
            }
        }else{
            throw new CustomGenericException("Old Password Doesn't Match");
        }

        userRepository.save(userEntity);
        return new ResponseEntity<CustomMessageWithId>(new CustomMessageWithId("Change Password Succesfully",false,null), HttpStatus.OK);
    }

    @PostMapping("/changeprofilepicture")
    public ResponseEntity<CustomMessageWithId> changeProfilePicture(@RequestParam("file")MultipartFile file,Authentication authentication){

        UserEntity userEntity=userRepository.findByEmail(authentication.getName());

       UploadFileResponse uploadFileResponse= uploadFileService.storeFile(file);

       userEntity.setProfilePicture(uploadFileResponse.getAttachment());
        userEntity.setFileName(uploadFileResponse.getFileName());
       userRepository.save(userEntity);
       return new ResponseEntity<CustomMessageWithId>(new CustomMessageWithId("Profile Picture Updated Successfully",false,null),HttpStatus.OK);
    }

}
