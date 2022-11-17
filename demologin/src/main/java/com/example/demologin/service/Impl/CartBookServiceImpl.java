package com.example.demologin.service.Impl;

import com.example.demologin.entity.cart.CartBook;
import com.example.demologin.repository.CartBookRepository;
import com.example.demologin.repository.CartRepository;
import com.example.demologin.service.ICartBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartBookServiceImpl implements ICartBookService {
    @Autowired
    private CartBookRepository cartBookRepository;


    @Override
    public CartBook findCartBookByStatusAndFlagAndBookId1(Long bookId) {
        return cartBookRepository.findCartBookByStatusAndFlagAndBookId1(bookId);
    }
}
