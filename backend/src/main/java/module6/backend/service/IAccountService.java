package module6.backend.service;

import module6.backend.entity.account.Account;
import module6.backend.entity.employee.Employee;
import org.springframework.data.repository.query.Param;

public interface IAccountService {
    Account findAccountById(@Param("id") Long id);
    void updatePassword(String password, Long id);
    Account createAccountForExistEmployee(Account account, String code);
    Account findAccountByUsername(String username);
    Boolean existAccountByUsername(String username);
    String getEncodedPassword(String password);
    void initRoleAndAccount();
    Account updateAccount(Account account);
    //NhiVP code tao employee moi va account moi
    Object createEmployeeAndAccount(Employee employee);

}
