package module6.backend.service;

import module6.backend.entity.account.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountService {
    Account findAccountById(@Param("id") Long id);
    void updatePassword(String password, Long id);
}
