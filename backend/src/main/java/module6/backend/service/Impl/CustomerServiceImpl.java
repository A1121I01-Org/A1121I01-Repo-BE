package module6.backend.service.Impl;

import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;


    @Override
    public void createCustomer(String name, String code, String avt, String address, String phone, String email, Long customerType) {
        customerRepository.createCustomer(name, code, avt, address, phone, email, customerType);
    }

    @Override
    public Customer getCustomerByCode(String codeCustomer) {
        return customerRepository.getCustomerByCode(codeCustomer);
    }
}
