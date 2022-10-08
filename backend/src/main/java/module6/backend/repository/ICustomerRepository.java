package module6.backend.repository;

import module6.backend.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    // Thắng code tìm kiếm Nhà cung cấp theo customer code
    Customer findByCustomerCode(String codeCustomer);

    // Thắng code thêm mới Nhà cung cấp (Import)
    @Query(value = "INSERT INTO `customer` (`customer_name`, `customer_code`, `customer_avatar`, `customer_address`, `customer_phone`, `customer_email`, `customer_type_id`) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7);", nativeQuery = true)
    @Transactional
    @Modifying
    void createCustomerImport(String name, String code, String avatar, String address, String phone, String email, Long customerTypeId);

}
