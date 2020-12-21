package com.project.ess.repository;

import com.project.ess.entity.security.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity,Long> {

    public AuthorityEntity findByName(String name);
}
