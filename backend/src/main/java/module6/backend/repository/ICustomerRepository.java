package module6.backend.repository;

import module6.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from customer where customer_type_id  > 0 and customer_flag = 0", nativeQuery = true)
    List<Customer> getAllCustomer();

    @Query(value = "SELECT * from customer  left join customer_type on customer_type.customer_type_id = customer.customer_id where customer.customer_flag = 0 group by customer.customer_id limit ?1,5", nativeQuery = true)
    List<Customer> getAllCustomerWithPagination(int index);

    @Query(value = "UPDATE customer SET customer_flag = 1 , customer_id = ?1  WHERE (customer_id = ?2)", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteCustomerById(Long id1, Long id2);

    @Query(value = "select * from customer where customer_name like %:name% and customer_phone like %:phone%  ", nativeQuery = true)
    List<Customer> searchCustomerByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

    @Query(value = "select * from customer where customer_id = ?1 and customer_type_id > 0 and customer_flag = 0 ", nativeQuery = true)
    Optional<Customer> findCustomerById(Long id);

}
