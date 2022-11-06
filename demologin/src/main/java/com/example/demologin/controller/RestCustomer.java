package com.example.demologin.controller;

import com.example.demologin.entity.Customer;
import com.example.demologin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class RestCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public List<Customer> getList() {
        return customerRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid Customer customer) {
        Customer saveCustomer = customerRepository.save(customer);
        URI uri = URI.create("/customers" + saveCustomer.getCustomerId());

        return ResponseEntity.created(uri).body(saveCustomer);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody @Valid Customer customer) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        customer1.get().setCustomerName(customer.getCustomerName());
        customer1.get().setCustomerAddress(customer.getCustomerAddress());
        customerRepository.save(customer1.get());
        URI uri = URI.create("/customers/" + customer1.get().getCustomerId());

        return ResponseEntity.created(uri).body(customer1.get());
    }


}
