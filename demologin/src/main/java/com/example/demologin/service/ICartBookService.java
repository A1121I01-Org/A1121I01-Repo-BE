package com.example.demologin.service;

import com.example.demologin.entity.cart.CartBook;
import org.springframework.data.repository.query.Param;

public interface ICartBookService {

    CartBook findCartBookByStatusAndFlagAndBookId1(@Param("id") Long bookId);
}
