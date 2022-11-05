package com.example.demologin;

import com.example.demologin.entity.Account;
import com.example.demologin.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AccountRepoTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testAccount() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = "hieu2002";
        String encodePass = passwordEncoder.encode(pass);

        Account newaccount = new Account("hieupro123", encodePass);

        Account saveAccount = accountRepository.save(newaccount);



        assertThat(saveAccount).isNotNull();
        assertThat(saveAccount.getAccountId()).isGreaterThan(0);
    }


}
