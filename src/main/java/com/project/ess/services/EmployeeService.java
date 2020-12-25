package com.project.ess.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.dto.AddressRequestDTO;
import com.project.ess.dto.EmployeeDTO;
import com.project.ess.dto.EmployeeRequestApproveDTO;
import com.project.ess.dto.EmployeeRequestDTO;
import com.project.ess.entity.*;
import com.project.ess.entity.approval.EmployeeRequestStatus;
import com.project.ess.execptions.CustomGenericException;
import com.project.ess.execptions.CustomMessageWithId;
import com.project.ess.execptions.CustomMessageWithRequestNo;
import com.project.ess.model.EmployeeNeedApproveResponse;
import com.project.ess.model.EmployeeRequestResponse;
import com.project.ess.model.EmployeeResponse;
import com.project.ess.model.UploadFileResponse;
import com.project.ess.model.jsondata.AddressRequestJsonData;
import com.project.ess.model.jsondata.EmployeeRequestJsonData;
import com.project.ess.projection.EmploymentBaseProj;
import com.project.ess.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    EmployeeRequestStatusRepository employeeRequestStatusRepository;

    @Autowired
    HRAdminRepository hrAdminRepository;

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
    public ResponseEntity<CustomMessageWithRequestNo> updateEmployeeData(String employee, MultipartFile file, String email){
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
//
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");

        String formatDateTime = LocalDateTime.now().format(formatter);

        employeeRequestEntity.setRequestNo("EMPREQ/"+formatDateTime+"/"+employeeEntity.getEmployeeNo());






        employeeRequestRepository.save(employeeRequestEntity);
        EmployeeRequestStatus employeeRequestStatus=new EmployeeRequestStatus();
        employeeRequestStatus.setStatus("PENDING");
        employeeRequestStatus.setEmployeeRequestEntity(employeeRequestEntity);
        employeeRequestStatusRepository.save(employeeRequestStatus);
 

        BeanUtils.copyProperties(request,employeeEntity);
        employeeEntity.setBirthDate(LocalDate.parse(request.getBirthDate()));
        employeeRepository.save(employeeEntity);




        return new ResponseEntity<CustomMessageWithRequestNo>(new CustomMessageWithRequestNo("Submit Successfully and will be check.",false,employeeRequestEntity.getRequestNo()), HttpStatus.OK);
    }


    public List<EmployeeRequestResponse> getListEmployeeRequest(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        List<EmployeeRequestResponse> allList= employeeRequestRepository.findData(employeeEntity);


//       .forEach(x->{
//
//
//            EmployeeRequestJsonData employeeRequestJsonData=new EmployeeRequestJsonData();
//            try {
//                employeeRequestJsonData=objectMapper.readValue(x.getRequestData(),EmployeeRequestJsonData.class);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            EmployeeRequestResponse employeeRequestResponse=new EmployeeRequestResponse();
//
//            BeanUtils.copyProperties(employeeRequestJsonData,employeeRequestResponse);
//
//            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("api/v1/downloadFile/")
//                    .path(x.getFileName())
//                    .toUriString();
//            employeeRequestResponse.setAttachment(fileDownloadUri);
//            employeeRequestResponse.setRequestNo(x.getRequestNo());
//            employeeRequestResponse.setRequestDatetime(x.getRequestDateTime());
//            employeeRequestResponse.setStatus(x.());
////            allList.add(modelMapper.map(x,EmployeeRequestResponse.class));
//
//            allList.add(employeeRequestResponse);
//        });

        return allList;
    }

    public List<EmploymentBaseProj> getMyteamByEmpNo(String email){

        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );
        return employmentRepository.getMyTeam(employeeEntity);
    }

    public List<EmployeeNeedApproveResponse> getAllEmpRequestNeedApprove(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        return employeeRequestRepository.getListEmpNeedApprove();
    }

    public List<EmployeeNeedApproveResponse> getAllEmpRequestHistory(String email){
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->  new CustomGenericException("This Employee Doesnt Exist")
        );


        return employeeRequestRepository.getListEmpHistory(employeeEntity);
    }

    @Transactional
    public ResponseEntity<CustomMessageWithId> cancelEmployeeRequest(String requestNo,String status,HRAdminEntity hraId,String remark) throws JsonProcessingException {


        EmployeeRequestEntity employeeRequestEntity=employeeRequestRepository.findByRequestNo(requestNo);
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeRequestEntity.getEmployeeNo().getEmployeeNo()).get();
        EmployeeRequestStatus employeeRequestStatus=employeeRequestStatusRepository.findByEmployeeRequestEntity(employeeRequestEntity);

        if(!employeeRequestStatus.getStatus().equalsIgnoreCase("PENDING")){
            throw new CustomGenericException("This Request Cant Be "+status.substring(0,1)+status.substring(1).toLowerCase());
        }

        ObjectMapper objectMapper=new ObjectMapper();
        EmployeeRequestJsonData employeeRequestJsonData=objectMapper.readValue(employeeRequestEntity.getRequestData(),EmployeeRequestJsonData.class);



        BeanUtils.copyProperties(employeeRequestJsonData,employeeEntity);

        employeeRequestStatus.setStatus(status);

        if(hraId!=null){
            employeeRequestStatus.setApprovedDatetime(LocalDateTime.now());
            employeeRequestStatus.setHraId(hraId);
            employeeRequestStatus.setRemark(remark);
        }


        employeeRequestStatusRepository.save(employeeRequestStatus);

        employeeRepository.save(employeeEntity);


        return new ResponseEntity<>(new CustomMessageWithId("Request Was "+status.substring(0,1)+status.substring(1).toLowerCase(),false,null),HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<CustomMessageWithId> approveEmpRequest(EmployeeRequestApproveDTO request,String email) throws JsonProcessingException {
        EmployeeRequestEntity employeeRequestEntity=employeeRequestRepository.findByRequestNo(request.getRequestNo());
        EmployeeEntity employeeEntity=employeeRepository.findByEmail(email).orElseThrow(
                ()->new CustomGenericException("This Employee Doesnt Exist")
        );
        EmployeeRequestStatus employeeRequestStatus=employeeRequestStatusRepository.findByEmployeeRequestEntity(employeeRequestEntity);




        HRAdminEntity hrAdminEntity=hrAdminRepository.findByEmployee(employeeEntity).orElseThrow(
                ()->new CustomGenericException("You Dont have Access To this Roles")
        );

        if(!employeeRequestStatus.getStatus().equalsIgnoreCase("PENDING")){
            throw new CustomGenericException("This Request Cant Be "+request.getStatus().substring(0,1)+request.getStatus().substring(1).toLowerCase());
        }

        if(request.getStatus().equalsIgnoreCase("REJECTED")){
            cancelEmployeeRequest(request.getRequestNo(),request.getStatus(),hrAdminEntity,request.getRemark());
        }else{
            employeeRequestStatus.setStatus(request.getStatus());
            employeeRequestStatus.setRemark(request.getRemark());
            employeeRequestStatus.setHraId(hrAdminEntity);
            employeeRequestStatus.setApprovedDatetime(LocalDateTime.now());
            employeeRequestStatusRepository.save(employeeRequestStatus);
        }

        return new ResponseEntity<>(new CustomMessageWithId(request.getStatus().substring(0,1)+request.getStatus().substring(1).toLowerCase()+" Successfully",false,null),HttpStatus.OK);

    }

}
