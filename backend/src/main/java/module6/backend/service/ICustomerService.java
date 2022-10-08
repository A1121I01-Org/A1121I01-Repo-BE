package module6.backend.service;

import module6.backend.entity.customer.Customer;

import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> findCustomerById(Long id);
}
