package module6.backend.repository;

import module6.backend.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Repository
@Transactional
public interface ICartRepository extends JpaRepository<Cart, Long> {

    @Modifying
    @Query(value = "UPDATE cart SET cart_status_id = 2 , cart_customer_id = :id1, cart_date_create= :date, cart_code = :code WHERE cart_id = :id2", nativeQuery = true)
    void updateCartStatusAndCustomer(@Param("id1") Long idCustomer, @Param("id2") Long idCart, @Param("date") LocalDate date, @Param("code") String code);

    @Modifying
    @Query(value = "UPDATE cart SET cart_quantity = :quantity , cart_total_money = :money WHERE cart_id = :idCart ", nativeQuery = true)
    void updateCart(@Param("quantity") Integer quantity,@Param("money") Integer money,@Param("idCart") Long idCart);

    @Query(value = "SELECT customer_code FROM customer", nativeQuery = true)
    String[] listCustomerCode();

    @Query(value = "SELECT cart_code FROM cart ", nativeQuery = true)
    String[] listCartCode();

    @Query(value = "SELECT * FROM cart WHERE cart_id = :id", nativeQuery = true)
    Cart findByCartId(@Param("id") Long id);
}
