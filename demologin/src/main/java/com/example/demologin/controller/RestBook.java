package com.example.demologin.controller;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.book.Promotion;
import com.example.demologin.repository.BookRepository;
import com.example.demologin.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/books")
public class RestBook {

    @Autowired
    private IBookService bookService;

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//    @GetMapping
//    public List<Book> getList() {
//        return bookService.getAllBook();
//    }

    @GetMapping("")
    public ResponseEntity<Page<Book>> findAllCustomer(@PageableDefault(value = 6) Pageable pageable) {
        Page<Book> bookPage = bookService.findAllBook(pageable);
        if (bookPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(bookPage, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/book-search")
    public ResponseEntity<Page<Book>> searchBookName(
            @PageableDefault(value = 6) Pageable pageable,
            @RequestParam Optional<String> name) {
        Page<Book> bookPage;
        if ((name.isPresent() && !name.get().isEmpty())) {
            bookPage = bookService.searchBookName(name.get(), pageable);
        } else if ((name.isPresent() && !name.get().isEmpty())) {
            bookPage = bookService.searchBookName(name.get(), pageable);
        } else if (name.isPresent() && !name.get().isEmpty()) {
            bookPage = bookService.searchBookName(name.get(), pageable);
        } else {
            bookPage = bookService.findAllBook(pageable);
        }
        return new ResponseEntity<>(bookPage, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody @Valid Book book) {
        try {
            System.out.println(book.getBookCode());
             bookService.createBook(book);
//            URI uri = URI.create("/books" + saveBook.getBookId());
            return new ResponseEntity<>(book,HttpStatus.CREATED);
//            return ResponseEntity.created(uri).body(saveBook);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//    @PatchMapping("update")
//    public ResponseEntity<Book> update(@RequestBody @Valid Book book) {
//          Optional<Book> updateBook = bookService.findBookById(book.getBookId());
//          if (!updateBook.isPresent()){
//              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//          }
//          bookService.updateBook(updateBook.get());
//            return new ResponseEntity<>(updateBook,HttpStatus.NO_CONTENT);
//
////        Optional<Book> book1 = bookRepository.findById(id);
////        book1.get().setBookName(book.getBookName());
////        book1.get().setBookCode(book.getBookCode());
////        book1.get().setBookImage(book.getBookImage());
////        book1.get().setBookContent(book.getBookContent());
////        book1.get().setBookStatus(book.getBookStatus());
////        book1.get().setBookPrice(book.getBookPrice());
////        book1.get().setBookTranslator(book.getBookTranslator());
////        book1.get().setBookWeight(book.getBookWeight());
////        book1.get().setBookPublishDate(book.getBookPublishDate());
////        book1.get().setBookQuantity(book.getBookQuantity());
////        book1.get().setBookFlag(book.getBookFlag());
////        book1.get().setBookPublisher(book.getBookPublisher());
////        book1.get().setBookPublisher(book.getBookPublisher());
////        book1.get().setBookCategoryId(book.getBookCategoryId());
////        book1.get().setBookPromotionId(book.getBookPromotionId());
////        bookRepository.save(book1.get());
////        URI uri = URI.create("/books" + book1.get().getBookId());
////
////        return ResponseEntity.created(uri).body(book1.get());
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("update")
    public ResponseEntity<Book> update(@RequestBody @Valid Book book) {
        Optional<Book> updateBook = bookService.findBookById(book.getBookId());
        if (!updateBook.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.updateBook(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }


}
