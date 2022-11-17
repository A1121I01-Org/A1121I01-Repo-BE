package com.example.demologin.service.Impl;

import com.example.demologin.entity.Account;
import com.example.demologin.entity.Role;
import com.example.demologin.entity.classDTO.CustomerAccount;
import com.example.demologin.entity.customer.Customer;
import com.example.demologin.repository.AccountRepository;
import com.example.demologin.repository.CustomerRepository;
import com.example.demologin.repository.RoleRepository;
import com.example.demologin.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public void createCustomerAccount(CustomerAccount customerAccount) {
        if (accountRepository.findAccountByUsername(customerAccount.getAccount().getUsername()) != null) {
            System.out.println("User name existed");
        }else {
            if (customerRepository.findExistCustomerDontHasAccount(customerAccount.getCustomer().getCustomerId()) != null) {
                Set<Role> roles = new HashSet<>();
                Optional<Role> role = roleRepository.findById((long) 2);
                Customer customer = customerRepository.findCustomerByPhone(customerAccount.getCustomer().getCustomerPhone());
                if (role.isPresent()) {
                    Role role1 = role.get();
                    roles.add(role1);
                }
                Account account = customerAccount.getAccount();
                account.setRoles(roles);
                account.setPassword(getEncodedPassword(customerAccount.getAccount().getPassword()));
                customer.setCustomerAccountId(account);
                customerRepository.save(customer);
            } else {
                Set<Role> roles = new HashSet<>();
                customerRepository.save(customerAccount.getCustomer());
                Optional<Role> role = roleRepository.findById((long) 2);
                if (role.isPresent()) {
                    Role role1 = role.get();
                    roles.add(role1);
                }
                Account account = customerAccount.getAccount();
                account.setRoles(roles);
                account.setPassword(getEncodedPassword(customerAccount.getAccount().getPassword()));
                customerAccount.getCustomer().setCustomerAccountId(account);
                customerRepository.save(customerAccount.getCustomer());
            }
        }
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
