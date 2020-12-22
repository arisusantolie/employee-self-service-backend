package com.project.ess.repository;

import com.project.ess.entity.FamilyRequestEntity;
import com.project.ess.entity.approval.FamilyRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRequestStatusRepository extends JpaRepository<FamilyRequestStatus,Long> {

    @Modifying
    @Query("delete from FamilyRequestStatus where familyRequestEntity=:familyreq")
    public void deleteFamilyRequesStatusByFamilyReqEntity(@Param("familyreq") FamilyRequestEntity familyRequestEntity);

    public FamilyRequestStatus findByFamilyRequestEntity(FamilyRequestEntity familyRequestEntity);

}
