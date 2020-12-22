package com.project.ess.repository;

import com.project.ess.entity.AddressRequestEntity;
import com.project.ess.entity.approval.AddressRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRequestStatusRepository  extends JpaRepository<AddressRequestStatus,Long> {

    public AddressRequestStatus findByAddressRequestEntity(AddressRequestEntity addressRequestEntity);

    @Modifying
    @Query("delete from AddressRequestStatus where addressRequestEntity=:address")
    public void deleteAddressRequestStatus(@Param("address") AddressRequestEntity addressRequestEntity);
}
