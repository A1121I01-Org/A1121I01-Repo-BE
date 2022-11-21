package com.example.demologin.util;


import com.example.demologin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailCustom, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(EmailCustom constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean check = true;
        String[] email = customerRepository.getAllEmail();
        for (int i =0;i < email.length;i++){
            if (value.equals(email[i])) {
                check = false;
                break;
            }
        }
        return check;
    }
}
