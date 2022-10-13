package module6.backend.repository;

import module6.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Query(value = "insert into customer (customer_name, customer_code, customer_avatar, customer_address, customer_phone, customer_email, customer_type_id) values (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void createCustomer(String name, String code, String avt, String address, String phone, String email, Long customerType);


    @Query(value = "SELECT * FROM CUSTOMER WHERE customer_code = :code", nativeQuery = true)
    Customer getCustomerByCode(@Param("code") String codeCustomer);

    @Query(value = "SELECT * FROM CUSTOMER WHERE customer_email = :email", nativeQuery = true)
    Customer getCustomerByEmail(@Param("email") String email);

    @Query(value = "SELECT customer_email FROM customer ", nativeQuery = true)
    String[] getAllEmail();

    @Query(value = "SELECT customer_phone FROM customer ", nativeQuery = true)
    String[] getAllPhone();

    // Thắng code tìm kiếm Nhà cung cấp theo customer code
    Customer findByCustomerCode(String codeCustomer);

    // Thắng code thêm mới Nhà cung cấp (Import)
    @Query(value = "INSERT INTO `customer` (`customer_name`, `customer_code`, `customer_address`, `customer_phone`, `customer_email`, `customer_type_id`) VALUES (?1, ?2, ?3, ?4, ?5, ?6);", nativeQuery = true)
    @Transactional
    @Modifying
    void createCustomerImport(String name, String code, String address, String phone, String email, Long customerTypeId);

    // Thắng code tìm kiếm nhà cung cấp
    @Query(value = "SELECT * FROM customer where customer_type_id = 3 and customer_id > 0;", nativeQuery = true)
    List<Customer> findAllCustomerImport();

    // Thắng code tìm kiếm kiểm tra code customer có tồn tại không
    @Query(value = "SELECT customer_code FROM customer;", nativeQuery = true)
    List<String> findAllCustomerImportString();
}
