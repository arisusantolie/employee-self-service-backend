package com.project.ess.repository;

import com.project.ess.entity.approval.AddressRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRequestStatusRepository  extends JpaRepository<AddressRequestStatus,Long> {
}
