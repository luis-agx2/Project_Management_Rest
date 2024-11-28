package com.lag.projectmanagement.repository;

import com.lag.projectmanagement.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT p.roles FROM PersonEntity p " +
            "JOIN AccountEntity ua " +
            "ON p.account.email = ua.email " +
            "WHERE p.account.email = :email")
    Set<RoleEntity> findPersonRolesByEmail(String email);
}
