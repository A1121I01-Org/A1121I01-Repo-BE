package com.example.demologin.service;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.book.BookCategory;
import com.example.demologin.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> findBookById(Long id);

    List<Book> getAllBook();

    void updateBook(Book book);

    void createBook(Book book);

    Page<Book> findAllBook(Pageable page);

    Page<Book> searchBookName(String name, Pageable page);

    List<Book> findBookByBookFlagAndBookStatus(Long[] bookId);

    void saveBook(String bookCode, String bookName, String image, String content, Double price, String translator,
                    String weight, LocalDate publishDate, Integer quantity, String bookPublisher, String bookAuthor,
                    Long bookPromotionPd, Long bookCategoryId);

    List<Book> findAllByBookCategoryId( Long categoryId);

    Optional<Book> existsByBookId( Long bookId);





}
