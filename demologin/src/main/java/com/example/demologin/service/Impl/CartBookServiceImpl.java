package com.example.demologin.service.Impl;

import com.example.demologin.entity.cart.CartBook;
import com.example.demologin.repository.CartBookRepository;
import com.example.demologin.repository.CartRepository;
import com.example.demologin.service.ICartBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartBookServiceImpl implements ICartBookService {
    @Autowired
    private CartBookRepository cartBookRepository;


    @Override
    public CartBook findCartBookByStatusAndFlagAndBookId1(Long bookId) {
        return cartBookRepository.findCartBookByStatusAndFlagAndBookId1(bookId);
    }

    @Override
    public List<CartBook> findAllCartBook(Long id) {
        return cartBookRepository.findAllCartBook(id);
    }

    @Override
    public List<CartBook> findAllCartBookByCartId(Long cartId) {
        return cartBookRepository.findAllCartBookByCartId(cartId);
    }
}
