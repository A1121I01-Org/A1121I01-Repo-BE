package com.example.demologin.repository;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {


    @Modifying
    @Query(value = "UPDATE cart SET cart_quantity = :quantity , cart_total_money = :money WHERE cart_id = :idCart ", nativeQuery = true)
    void updateCart(@Param("quantity") Integer quantity, @Param("money") Double money, @Param("idCart") Long idCart);

    @Query(value = "INSERT INTO `cart` (`book_name`, `book_image`, `book_publisher`, `book_translator`, `book_price`, `cart_quantity`, `cart_total_money`,`cart_date_create`,`cart_status_id`,  `cart_account_id`,`book_promotion_percent`)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11);", nativeQuery = true)
    @Transactional
    @Modifying
    void insertBookIntoCart(String name, String img, String publisher, String translator, Double price, Integer quantity, Double totalMoney, Date dateCreate, Long cartStatusId, Long accountId, Double promotionPercent);

    @Query(value = "select * from cart where  cart_account_id = :idAccount AND cart_flag =0", nativeQuery = true)
    List<Cart> getAllCartWithUser(@Param("idAccount") Long idAccount);

    @Modifying
    @Query(value = "UPDATE cart SET cart_flag = 1 WHERE cart_id= :cartId ", nativeQuery = true)
    void deleteCartByCartIdAndAccountId(@Param("cartId") Long cartId);

    @Query(value = "select * from cart where cart_id= :cartId AND book_name= :bookName", nativeQuery = true)
    Optional<Cart> existsByCartId(@Param("cartId") Long cartId, @Param("bookName") String bookName);

    @Modifying
    @Query(value = "UPDATE cart SET cart_quantity = :quantity  WHERE cart_id = :cartId ", nativeQuery = true)
    void updateQuantityCart(@Param("quantity") Integer quantity,@Param("cartId") Long cartId);

}
