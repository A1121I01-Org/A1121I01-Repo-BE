package module6.backend.service;

import module6.backend.entity.account.Account;
import module6.backend.entity.employee.Employee;

public interface IAccountService {
    Account createAccountForExistEmployee(Account account, String code);
    Account findAccountByUsername(String username);
    Boolean existAccountByUsername(String username);
    String getEncodedPassword(String password);
    void initRoleAndAccount();
    Account updateAccount(Account account);
    //NhiVP code tao employee moi va account moi
    Object createEmployeeAndAccount(Employee employee);
}
