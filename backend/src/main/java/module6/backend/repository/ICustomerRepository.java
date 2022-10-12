package module6.backend.repository;

import module6.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
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
}
