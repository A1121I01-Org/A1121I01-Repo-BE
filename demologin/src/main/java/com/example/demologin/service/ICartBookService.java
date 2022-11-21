package com.example.demologin.service;

import com.example.demologin.entity.cart.CartBook;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartBookService {

    CartBook findCartBookByStatusAndFlagAndBookId1( Long bookId);

    List<CartBook> findAllCartBook(Long id);

    List<CartBook> findAllCartBookByCartId(Long cartId);


}
