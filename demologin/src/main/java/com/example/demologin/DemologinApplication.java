package com.example.demologin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

public class DemologinApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemologinApplication.class, args);
    }

}
