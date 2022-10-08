package module6.backend.service;

import module6.backend.entity.customer.Customer;

import java.util.Optional;

public interface ICustomerService {
    //DuyDTT code save và findCustomerById
    Customer save(Customer customer);
//    Optional<Customer> findCustomerById(Long id);
    void updateCustomer(String name,String code,String avatar,String address,String phone,String email,Long typeId, Long id);
    Optional<Customer> findCustomerById(Long id);
    void createCustomer(String name, String code, String avt, String address, String phone, String email, Long customerType);
}
