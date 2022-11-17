package com.example.demologin.repository;

import com.example.demologin.entity.Account;
import com.example.demologin.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAllByUsername(String userName);

//    Account findAccountByUsername(String username);
    Account findAccountByAccountId(Long id);

    @Query(value = "SELECT * FROM account WHERE username=?1 and account_flag = 0", nativeQuery = true)
    Account findAccountByUsername(String username);



}
