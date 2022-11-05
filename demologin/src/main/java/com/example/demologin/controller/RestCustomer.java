package com.example.demologin.controller;

import com.example.demologin.entity.Customer;
import com.example.demologin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class RestCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getList() {
        return customerRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid Customer customer) {
        Customer saveCustomer = customerRepository.save(customer);
        URI uri = URI.create("/customers" + saveCustomer.getCustomerId());

        return ResponseEntity.created(uri).body(saveCustomer);
    }
}
