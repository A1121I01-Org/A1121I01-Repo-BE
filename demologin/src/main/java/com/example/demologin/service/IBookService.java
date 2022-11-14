package com.example.demologin.service;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> findBookById(Long id);

    List<Book> getAllBook();

    void updateBook(Book book);

    void createBook(Book book);

    Page<Book> findAllBook(Pageable page);

    Page<Book> searchBookName(String name, Pageable page);
}
