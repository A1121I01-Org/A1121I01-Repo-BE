package module6.backend.repository;

import module6.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from customer where customer_type_id  > 0 and customer_flag = 0", nativeQuery = true)
    List<Customer> getAllCustomer();

    @Query(value = "select * from customer where customer_type_id  > 0 and customer_flag = 0", nativeQuery = true)
    List<Customer> findCustomerById();

    @Query(value = "SELECT * from customer  left join customer_type on customer_type.customer_type_id = customer.customer_id where customer.customer_flag = 0 group by customer.customer_id limit ?1,5", nativeQuery = true)
    List<Customer> getAllCustomerWithPagination(int index);

    @Query(value = "UPDATE FROM customer   SET customer_name=?1, customer_code=?2, customer_avatar=?3, customer_address=?4, customer_phone=?5, customer_email=?6, customer_type_id=?7 WHERE customer_id=?8", nativeQuery = true)
    Customer updateCustomer(String name, String code, String avatar, String address, String phone, String email, Long typeId, Long id);

    //    @Query(" UPDATE FROM customer SET customer_name=?1, customer_code=?2, customer_avatar=?3, customer_address=?4, customer_phone=?5, customer_email=?6, customer_type_id=?7 WHERE customer_id=?8", nativeQuery= true)
//    void deleteCustomerById(Long id);
    @Query(value = "select * from customer where customer_id = ?1 and customer_type_id > 0 and customer_flag = 0 ", nativeQuery = true)
    Optional<Customer> findCustomerById(Long id);

}
