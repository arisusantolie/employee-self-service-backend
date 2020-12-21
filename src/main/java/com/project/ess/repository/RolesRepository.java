package com.project.ess.repository;

import com.project.ess.entity.UserEntity;
import com.project.ess.entity.security.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RolesRepository extends JpaRepository<RoleEntity,Long> {

    public RoleEntity findByName(String name);

    @Query(value = "select * from user_roles where user_id=:userId" ,nativeQuery = true)
    List<Map<String,Object>> getListExistRoles(@Param("userId") Long userId);
}
