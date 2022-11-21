package com.example.demologin.util;

import com.example.demologin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneCustom, String> {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void initialize(PhoneCustom constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean check = true;
        String[] phone = customerRepository.getAllPhone();
        for (int i =0;i < phone.length;i++){
            if (value.equals(phone[i])) {
                check = false;
                break;
            }
        }
        return check;
    }


}
