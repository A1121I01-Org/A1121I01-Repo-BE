package module6.backend.service;

import module6.backend.entity.account.Account;

public interface IAccountService {
    Account createAccountForExistEmployee(Account account, String code);
    Account findAccountByUsername(String username);
    Boolean existAccountByUsername(String username);
    String getEncodedPassword(String password);
    void initRoleAndAccount();
}
