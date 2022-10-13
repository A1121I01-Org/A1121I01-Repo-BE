package module6.backend.service;

import module6.backend.entity.customer.Customer;

import java.util.List;
import java.util.Optional;


public interface ICustomerService {
    void createCustomer(String name, String code, String avt, String address, String phone, String email, Long customerType);

    Customer getCustomerByCode(String codeCustomer);


    List<Customer> getAllCustomer();

    List<Customer> getAllCustomerWithPagination(int index);

    void deleteCustomerById(Long id1, Long id2);

    List<Customer> searchCustomerByNameAndPhone(String name, String phone);

    Optional<Customer> findCustomerById(Long id);

}
