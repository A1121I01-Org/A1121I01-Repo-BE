package module6.backend.service.Impl;

import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
//DuyDTT code save và findCustomerById
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public void createCustomer(String name, String code, String avt, String address, String phone, String email, Long customerType) {
        customerRepository.createCustomer(name,code,avt,address,phone,email,customerType);
    }

    @Override
    public void updateCustomer(String name, String code, String avatar, String address, String phone, String email, Long typeId, Long id) {
        customerRepository.updateCustomer(name,code,avatar,address,phone,email,typeId,id);
    }
}
