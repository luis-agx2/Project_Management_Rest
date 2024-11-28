package com.lag.projectmanagement.repository;

import com.lag.projectmanagement.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickName(String nickName);
}
