package module6.backend.service;

import module6.backend.entity.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> getAllCustomer();

    List<Customer> getAllCustomerWithPagination(int index);

    void updateCustomer(String name, String code, String avatar, String address, String phone, String email, Long typeId, Long id);

    Optional<Customer> findCustomerById(Long id);

}
