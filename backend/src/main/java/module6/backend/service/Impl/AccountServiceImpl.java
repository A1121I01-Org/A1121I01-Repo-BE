package module6.backend.service.Impl;

import module6.backend.entity.account.Account;
import module6.backend.entity.account.Role;
import module6.backend.entity.employee.Employee;
import module6.backend.repository.IAccountRepository;
import module6.backend.repository.IEmployeeRepository;
import module6.backend.repository.IRoleRepository;
import module6.backend.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IRoleRepository roleRepository;


    //Thêm sẵn 2 acc admin
    @Override
    public void initRoleAndAccount() {
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role accountantRole = new Role();
        accountantRole.setRoleName("ROLE_ACCOUNTANT");
        roleRepository.save(accountantRole);

        Role sellRole = new Role();
        sellRole.setRoleName("ROLE_SELL");
        roleRepository.save(sellRole);

        Account adminUser = new Account();
        adminUser.setUsername("admin");
        adminUser.setPassword("123123");
        adminUser.setAccountFlag(false);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        accountRepository.save(adminUser);

        Account adminUser1 = new Account();
        adminUser1.setUsername("admin1");
        adminUser1.setPassword("123123");
        adminUser1.setAccountFlag(false);
        Set<Role> adminRoles1 = new HashSet<>();
        adminRoles1.add(adminRole);
        adminUser1.setRoles(adminRoles1);
        accountRepository.save(adminUser1);
    }
    @Override
    public Account createAccountForExistEmployee(Account account, String code) {
        return null;
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Boolean existAccountByUsername(String username) {
        return null;
    }

    @Override
    public String getEncodedPassword(String password) {
        return null;
    }

}
