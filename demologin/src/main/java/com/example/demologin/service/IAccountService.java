package com.example.demologin.service;

import com.example.demologin.entity.Account;
import com.example.demologin.entity.classDTO.CustomerAccount;

public interface IAccountService {
    //HieuNT code tim account by username
    Account findAccountByUsername(String username);

    //HieuNT code tao customer moi va account moi
    void createCustomerAccount(CustomerAccount customerAccount);

}
