package com.example.demologin.service.Impl;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.cart.Cart;
import com.example.demologin.repository.CartRepository;
import com.example.demologin.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void updateCart(Integer quantity, Double money, Long id) {
        cartRepository.updateCart(quantity, money, id);
    }

    @Override
    public void insertBookIntoCart(String name, String img, String publisher, String translator, Double price, Integer quantity, Double totalMoney, Date dateCreate, Long cartStatusId, Long accountId, Double promotionPercent) {
         cartRepository.insertBookIntoCart(name,img,publisher,translator,price,quantity,totalMoney,dateCreate,cartStatusId,accountId,promotionPercent);
    }

    @Override
    public List<Cart> getAllCartWithUser(Long idAccount) {
        return cartRepository.getAllCartWithUser(idAccount);
    }

    @Override
    public void deleteCartByCartIdAndAccountId(Long cartId) {
        cartRepository.deleteCartByCartIdAndAccountId(cartId);
    }

    @Override
    public Optional<Cart> existsByCartId(Long cartId,  String bookName) {
        return cartRepository.existsByCartId(cartId, bookName);
    }


    @Override
    public void updateQuantityCart(Integer quantity, Long cartId) {
        cartRepository.updateQuantityCart(quantity,cartId);
    }


}
