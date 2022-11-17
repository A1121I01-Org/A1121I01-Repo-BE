package com.example.demologin.service;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.cart.Cart;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICartService {
    void updateCart(Integer quantity , Double money , Long id);

    void insertBookIntoCart(String name, String img, String publisher, String translator, Double price, Integer quantity, Double totalMoney, Date dateCreate, Long cartStatusId, Long accountId, Double promotionPercent);

    List<Cart> getAllCartWithUser(Long idAccount);

    void deleteCartByCartIdAndAccountId(Long cartId);

    Optional<Cart> existsByCartId(Long cartId,  String bookName);

    void updateQuantityCart( Integer quantity,Long cartId);

}
