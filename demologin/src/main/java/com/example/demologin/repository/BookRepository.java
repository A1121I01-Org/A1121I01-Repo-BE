package com.example.demologin.repository;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findBookByBookId(Long id);

    @Query(value = "select * from book where  book_flag = 0", nativeQuery = true)
    List<Book> getAllBook();

    @Query(value = "select * from book where book_flag = 0 order by book_id desc ", nativeQuery = true)
    Page<Book> findAllIBook(Pageable pageable);

    @Query(value = "select * from book where book_name like %:name% and book_flag = 0", nativeQuery = true)
    Page<Book> searchBookName(@Param("name") String name, Pageable pageable);


}
