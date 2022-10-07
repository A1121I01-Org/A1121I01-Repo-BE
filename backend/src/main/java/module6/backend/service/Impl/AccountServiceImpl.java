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
        Role adminRole = roleRepository.findRoleByRoleName("ROLE_ADMIN");
        Role accountantRole = roleRepository.findRoleByRoleName("ROLE_ACCOUNTANT");
        Role sellRole = roleRepository.findRoleByRoleName("ROLE_SELL");
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
    public Account updateAccount(Account account) {
        return accountRepository.updateAccount(account.getUsername(), account.getPassword(), account.getAccountId());
    }

    //NhiVP code tao account cho nhan vien ton tai nhung chua co account
    @Override
    public Account createAccountForExistEmployee(Account account, String code) {
        Employee employee = employeeRepository.findEmployeeByCode(code);
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        if (employee.getEmployeePositionId().getPositionId() == 2) {
            role.setRoleId(Long.valueOf(2));
        } else {
            role.setRoleId(Long.valueOf(3));
        }
        roles.add(role);
        account.setRoles(roles);
        account.setAccountFlag(false);

        employee.setEmployeeAccountId(account);

        employeeRepository.save(employee);
        return account;
    }

    //NhiVP code tao nhan vien va account moi
    @Override
    public Object createEmployeeAndAccount(Employee employee) {
//        employeeRepository.saveEmployeeAndAccount(employee.getEmployeeCode(),employee.getEmployeeName(),employee.getEmployeeDateOfBirth(),employee.getEmployeeGender(),employee.getEmployeeAddress(),
//                employee.getEmployeePhone(), employee.getEmployeeAccountId().getAccountId(),employee.getEmployeePositionId().getPositionId());
        employeeRepository.save(employee);
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role();
        if (employee.getEmployeePositionId().getPositionId() == 1) {
            role.setRoleId(Long.valueOf(1));
        } else {
            role.setRoleId(Long.valueOf(2));
        }
        roleSet.add(role);
        employee.getEmployeeAccountId().setRoles(roleSet);

        accountRepository.save(employee.getEmployeeAccountId());
//        employeeRepository.saveEmployeeAndAccount(employee.getEmployeeCode(),employee.getEmployeeName(),employee.getEmployeeDateOfBirth(),employee.getEmployeeGender(),employee.getEmployeeAddress(),
//                employee.getEmployeePhone(), employee.getEmployeeAccountId().getAccountId(),employee.getEmployeePositionId().getPositionId());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee createTest(Employee employee, String username, String password, Long positionId, String code) {
        if (employeeRepository.findExistEmployeeHasAccount(code)!=null){
            System.out.println("Ton tai nhan vien va da co tai khoan");
        }else if (employeeRepository.findExistEmployeeDontHasAccount(code)!=null){
            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findById(positionId).get();
            roles.add(role);
            Account account = new Account(username,password);
            account.setRoles(roles);
            employee.setEmployeeAccountId(account);
            employeeRepository.save(employee);
        }else {
            Set<Role> roles = new HashSet<>();
            employeeRepository.save(employee);
            Role role = roleRepository.findById(positionId).get();
            roles.add(role);
            Account account = new Account(username,password);
            account.setRoles(roles);
            employee.setEmployeeAccountId(account);
            employeeRepository.save(employee);
        }
        return employee;
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Boolean existAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username) != null;
    }

    @Override
    public String getEncodedPassword(String password) {
        return null;
    }

}
