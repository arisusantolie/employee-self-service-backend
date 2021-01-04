package com.project.ess.repository;

import com.project.ess.entity.EmployeeEntity;
import com.project.ess.model.EmployeeProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    public Optional<EmployeeEntity> findByEmail(String email);

    @Query("select new com.project.ess.model.EmployeeProfileResponse(u.birthDate,u.bloodType,u.bpjsNo,u.email,u.employeeNo,u.familyCardNo,u.firstName,u.ktpNo," +
            "u.lastEducation,u.lastName,u.maritialStatus,u.mobilePhone,u.nationality,u.npwpNo,u.religion,us.fileName)  " +
            "from EmployeeEntity u,user us where u=us.employee and u.email=:email ")
    public Optional<EmployeeProfileResponse> findByEmailProfile(String email);

    @Query("select u from EmployeeEntity u,EmploymentEntity employ where u=employ.employee and current_date >=employ.startDate and current_date <=employ.endDate")
    public List<EmployeeEntity> getAllEmployee();
}
