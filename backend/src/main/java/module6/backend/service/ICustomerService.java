package module6.backend.service;

import module6.backend.entity.customer.Customer;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> getAllCustomer();

    List<Customer> getAllCustomerWithPagination(int index);

    void deleteCustomerById(Long id1, Long id2);

    List<Customer> searchCustomerByNameAndPhone( String name,String phone);

    Optional<Customer> findCustomerById(Long id);

}
