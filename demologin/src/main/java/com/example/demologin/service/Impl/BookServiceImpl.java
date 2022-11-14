package com.example.demologin.service.Impl;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.customer.Customer;
import com.example.demologin.repository.BookRepository;
import com.example.demologin.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findBookByBookId(id);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.getAllBook();
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Page<Book> findAllBook(Pageable page) {
        return bookRepository.findAllIBook(page);
    }

    @Override
    public Page<Book> searchBookName(String name, Pageable page) {
        return bookRepository.searchBookName(name, page);
    }
}
