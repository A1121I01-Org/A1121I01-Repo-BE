package com.example.demologin.entity.classDTO;

import com.example.demologin.entity.Account;
import com.example.demologin.entity.customer.Customer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;



public class CustomerAccount {
    private Customer customer;
    private Account account;

    public CustomerAccount(Customer customer) {
        this.customer = customer;
    }

    public CustomerAccount(Account account) {
        this.account = account;
    }

    public CustomerAccount(Customer customer, Account account) {
        this.customer = customer;
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
