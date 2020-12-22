package com.project.ess.repository;

import com.project.ess.entity.AttempdailyEntity;
import com.project.ess.entity.approval.AttempdailyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttempdailyStatusRepository extends JpaRepository<AttempdailyStatus,Long > {

    public AttempdailyStatus findByAttempdailyEntity(AttempdailyEntity attempdailyEntity);
}
