package module6.backend.repository;

import module6.backend.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ICartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart WHERE cart_status_id = 1;", nativeQuery = true)
    List<Cart> findByCartStatusId();

    @Modifying
    @Query(value = "UPDATE cart SET cart_status_id = 2 WHERE cart_id=:cartId ", nativeQuery = true)
    void updateCartStatusId(@Param("cartId") Long cartId);

    @Modifying
    @Query(value = "DELETE from cart where cart_id =:cartId and cart_status_id = 1", nativeQuery = true)
    void deleteCartByCartId(@Param("cartId") Long cartId);
}
