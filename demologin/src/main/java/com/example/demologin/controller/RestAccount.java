package com.example.demologin.controller;

import com.example.demologin.entity.classDTO.CustomerAccount;
import com.example.demologin.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/account")
public class RestAccount {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //HieuNT create account
    @PostMapping("/create-Account")
    public ResponseEntity<?> createAccount(@RequestBody CustomerAccount customerAccount, BindingResult bindingResult) throws UsernameNotFoundException {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getAllErrors().forEach((error) -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.put(fieldName, errorMessage);
//            });
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        } else {
//            accountService.createEmployeeAccount(employeeAccount);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
        if (accountService.findAccountByUsername(customerAccount.getAccount().getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            accountService.createCustomerAccount(customerAccount);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
