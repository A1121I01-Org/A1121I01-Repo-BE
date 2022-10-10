package module6.backend.service;

import module6.backend.entity.account.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAllAccount();
}
