package com.example.demologin.repository;


import com.example.demologin.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //HieuNT code tim customer chua co account
    @Query(value = "select * from customer where customer_id =?1 and customer_account_id is null and customer_flag = 0", nativeQuery = true)
    Customer findExistCustomerDontHasAccount(Long code);

    //HieuNT code tim customer bang code
    @Query(value = "SELECT * from customer where customer_phone =?1 and customer_flag = 0", nativeQuery = true)
    Customer findCustomerByPhone(String phone);

    @Query(value = "SELECT customer_email FROM customer ", nativeQuery = true)
    String[] getAllEmail();

    @Query(value = "SELECT customer_phone FROM customer ", nativeQuery = true)
    String[] getAllPhone();
}
