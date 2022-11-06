package com.example.demologin;

import com.example.demologin.entity.Customer;
import com.example.demologin.entity.Role;
import com.example.demologin.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CustomerRepoTest {

    @Autowired private CustomerRepository customerRepository;
    @Test
    public void update() {
//        List<Customer> customers = customerRepository.findAll();
//        assertThat(customers.size()).isGreaterThan(0);

//        customers.forEach(System.out::println);

        Optional<Customer> customer1 = customerRepository.findById((long) 2);
        customer1.get().setCustomerName("huynh van khai em");

        customerRepository.save(customer1.get());

        System.out.println(customer1.get());

    }
}
