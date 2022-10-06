package com.vti.repository;

import com.vti.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    boolean existsByUsername(String username);

    Account findByUsername(String username);

    @Modifying
    @Query("UPDATE Account SET password = :password WHERE username = :username")
    void changePassword(String username, String password);

}
