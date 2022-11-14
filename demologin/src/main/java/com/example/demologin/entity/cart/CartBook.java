package com.example.demologin.entity.cart;

import com.example.demologin.entity.book.Book;

import javax.persistence.*;

@Entity
@Table(name = "cart_book")
public class CartBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartBookId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book bookId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_id")
    private Cart cartId;

    private Boolean cartBookFlag = false;

    private String cartBookReason;

    public CartBook() {
    }

    public CartBook(Long cartBookId, Book bookId, Cart cartId, Boolean cartBookFlag, String cartBookReason) {
        this.cartBookId = cartBookId;
        this.bookId = bookId;
        this.cartId = cartId;
        this.cartBookFlag = cartBookFlag;
        this.cartBookReason = cartBookReason;
    }

    public CartBook(Book bookId, Cart cartId, Boolean cartBookFlag) {
        this.bookId = bookId;
        this.cartId = cartId;
        this.cartBookFlag = cartBookFlag;
    }

    public Long getCartBookId() {
        return cartBookId;
    }

    public void setCartBookId(Long cartBookId) {
        this.cartBookId = cartBookId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public Cart getCartId() {
        return cartId;
    }

    public void setCartId(Cart cartId) {
        this.cartId = cartId;
    }

    public Boolean getCartBookFlag() {
        return cartBookFlag;
    }

    public void setCartBookFlag(Boolean cartBookFlag) {
        this.cartBookFlag = cartBookFlag;
    }

    public String getCartBookReason() {
        return cartBookReason;
    }

    public void setCartBookReason(String cartBookReason) {
        this.cartBookReason = cartBookReason;
    }
}
