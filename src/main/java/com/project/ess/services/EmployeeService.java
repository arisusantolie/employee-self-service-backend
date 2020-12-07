package com.project.ess.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.dto.EmployeeDTO;
import com.project.ess.dto.EmployeeRequestDTO;
import com.project.ess.entity.*;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.model.EmployeeResponse;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.model.jsondata.AddressRequestJsonData;
import com.project.ess.model.jsondata.EmployeeRequestJsonData;
import com.project.ess.repository.EmployeeRepository;
import com.project.ess.repository.EmployeeRequestRepository;
import com.project.ess.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    EmployeeRequestRepository employeeRequestRepository;

    @Transactional
    public EmployeeResponse createEmployee(EmployeeDTO request){
        EmployeeEntity newEmployee=new EmployeeEntity();

        BeanUtils.copyProperties(request,newEmployee);


        employeeRepository.save(newEmployee);

        UserEntity userEntity=new UserEntity();

        String defaultPassword=newEmployee.getBirthDate().toString().replace("-","");

        System.out.println(defaultPassword);

        userEntity.setEmail(newEmployee.getEmail());
        userEntity.setEmployee(newEmployee);

        userEntity.setPassword(bCryptPasswordEncoder.encode(defaultPassword));
        userRepository.save(userEntity);

        EmployeeResponse returnValue=new EmployeeResponse();

        BeanUtils.copyProperties(newEmployee,returnValue);

        return returnValue;
    }

    public Optional<EmployeeEntity> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }


    @Transactional
    public ResponseEntity<CustomMessageWithId> updateEmployeeData(String employee, MultipartFile file,String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );

        EmployeeRequestDTO request=new EmployeeRequestDTO();

        try {
            ObjectMapper objectMapper=new ObjectMapper();
            request=objectMapper.readValue(employee,EmployeeRequestDTO.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        EmployeeRequestEntity employeeRequestEntity=new EmployeeRequestEntity();
        EmployeeRequestJsonData employeeRequestJsonData=new EmployeeRequestJsonData();



        employeeRequestEntity.setEmployeeNo(employeeEntity);
        employeeRequestEntity.setStatus("PENDING");
        employeeRequestEntity.setRequestDateTime(LocalDateTime.now());
        UploadFileResponse uploadFileResponse=uploadFileService.storeFile(file);

        employeeRequestEntity.setAttachment(uploadFileResponse.getAttachment());
        employeeRequestEntity.setFileName(uploadFileResponse.getFileName());



        BeanUtils.copyProperties(employeeEntity,employeeRequestJsonData);
        employeeRequestJsonData.setBirthDate(employeeEntity.getBirthDate().toString());

        employeeRequestJsonData.setNewFirstName(request.getFirstName());
        employeeRequestJsonData.setNewLastName(request.getLastName());
        employeeRequestJsonData.setNewBirthDate(request.getBirthDate());
        employeeRequestJsonData.setNewReligion(request.getReligion());
        employeeRequestJsonData.setNewBloodType(request.getBloodType());
        employeeRequestJsonData.setNewEmail(request.getEmail());
        employeeRequestJsonData.setNewKtpNo(request.getKtpNo());
        employeeRequestJsonData.setNewBpjsNo(request.getBpjsNo());
        employeeRequestJsonData.setNewFamilyCardNo(request.getFamilyCardNo());
        employeeRequestJsonData.setNewNpwpNo(request.getNpwpNo());
        employeeRequestJsonData.setNewLastEducation(request.getLastEducation());
        employeeRequestJsonData.setNewNationality(request.getNationality());
        employeeRequestJsonData.setNewMobilePhone(request.getMobilePhone());
        employeeRequestJsonData.setNewMaritialStatus(request.getMaritialStatus());


        employeeRequestEntity.setRequestData(employeeRequestJsonData.toString());






        employeeRequestRepository.save(employeeRequestEntity);

 

        BeanUtils.copyProperties(request,employeeEntity);
        employeeEntity.setBirthDate(LocalDate.parse(request.getBirthDate()));
        employeeRepository.save(employeeEntity);




        return new ResponseEntity<CustomMessageWithId>(new CustomMessageWithId("Submit Successfully and will be check.",false,employeeEntity.getEmployeeNo()), HttpStatus.OK);
    }
}
