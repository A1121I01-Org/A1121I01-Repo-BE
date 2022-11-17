package com.example.demologin.repository;

import com.example.demologin.entity.cart.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook, Long> {

    @Query(value = "SELECT book_id FROM cart_book LEFT JOIN cart ON cart_book.cart_id = cart.cart_id WHERE cart.cart_status_id = 1 AND cart_book.cart_book_flag = 0;", nativeQuery = true)
    Long[] getCartBookByIdAndStatus();


    @Query(value = "SELECT cart_book.cart_book_id, cart_book.cart_id ,cart_book.cart_book_flag,cart_book.book_id,cart_book.cart_book_reason FROM cart_book \n" +
            "LEFT JOIN cart ON cart_book.cart_id = cart.cart_id\n" +
            "WHERE cart.cart_status_id = 1 AND cart_book.cart_book_flag = 0 AND book_id = :id", nativeQuery = true)
    CartBook findCartBookByStatusAndFlagAndBookId1(@Param("id") Long bookId);
}
