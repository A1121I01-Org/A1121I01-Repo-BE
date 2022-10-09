package module6.backend.service.Impl;

import module6.backend.entity.customer.Customer;
import module6.backend.repository.ICustomerRepository;
import module6.backend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.getAllCustomer();
    }

    @Override
    public List<Customer> getAllCustomerWithPagination(int index) {
        return customerRepository.getAllCustomerWithPagination(index);
    }

    @Override
    public void updateCustomer(String name, String code, String avatar, String address, String phone, String email, Long typeId, Long id) {
        customerRepository.updateCustomer(name, code,avatar,address,phone,email,typeId,id);
    }
}
