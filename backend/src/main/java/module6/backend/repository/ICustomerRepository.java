package module6.backend.repository;

import module6.backend.entity.cart.Cart;
import module6.backend.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    // HuyenNTD - Thong ke khach hang tiem nang

    @Query(value = "select customer_code , customer_name , count(cart_customer_id) as SLDonHang, sum(cart_total_money) as TongGiaTri from customer\n" +
            "join cart on cart.cart_customer_id = customer.customer_id\n" +
            "group by cart_customer_id", nativeQuery = true)
    List<String> findAllCustomer();

    @Query(value = "select customer_code, customer_name, count(cart_customer_id) as SLDonHang, sum(cart_total_money) as TongGiaTri from customer\n" +
            "join cart on cart.cart_customer_id = customer.customer_id\n" +
            "join cart_status on cart_status.cart_status_id = cart.cart_status_id\n" +
            "where (month(cart_date_create) between :fromMonth and :toMonth) and year(cart_date_create) = :year and cart_status_name = 'đã thanh toán'\n" +
            "group by cart_customer_id", nativeQuery = true)
    List<String> findForPotentialCustomers(@Param("fromMonth") String fromMonth,
                                                         @Param("toMonth") String toMonth,
                                                         @Param("year") String year);
}


//@Query(value = "select customer_code, customer_name, count(cart_customer_id) as SLDonHang, sum(cart_total_money) as TongGiaTri from customer\n" +
//        "join cart on cart.cart_customer_id = customer.customer_id \n" +
//        "join cart_status on cart_status.cart_status_id = cart.cart_status_id\n" +
//        "group by cart_customer_id", nativeQuery = true)