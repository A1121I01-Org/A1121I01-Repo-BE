package module6.backend.service;

import module6.backend.entity.customer.Customer;
import org.springframework.data.repository.query.Param;

public interface ICustomerService {
    void createCustomer(String name, String code, String avt, String address, String phone, String email, Long customerType);

    Customer getCustomerByCode(String codeCustomer);
}
