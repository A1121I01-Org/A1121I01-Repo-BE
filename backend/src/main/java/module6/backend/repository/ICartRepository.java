package module6.backend.repository;

import module6.backend.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ICartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "select sum(cart_total_money) as huy_hang from cart where cart_status_id = 4 group by cart_status_id;",
            nativeQuery = true)
    Integer huy();

    @Query(value = "select sum(cart_total_money) as tra_hang from cart where cart_status_id = 3 group by cart_status_id;", nativeQuery = true)
    Integer tra();

    @Query(value = "select sum(cart_total_money) as nhap_hang from cart where cart_status_id = 2 group by cart_status_id;", nativeQuery = true)
    Integer ban();
}
