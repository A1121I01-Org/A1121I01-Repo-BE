package com.example.demologin.repository;

import com.example.demologin.entity.book.Book;
import com.example.demologin.entity.book.BookCategory;
import com.example.demologin.entity.cart.Cart;
import com.example.demologin.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findBookByBookId(Long id);

    @Query(value = "select * from book where  book_flag = 0", nativeQuery = true)
    List<Book> getAllBook();

    @Query(value = "select * from book where book_flag = 0 order by book_id desc ", nativeQuery = true)
    Page<Book> findAllIBook(Pageable pageable);

    @Query(value = "select * from book where book_name like %:name% and book_flag = 0", nativeQuery = true)
    Page<Book> searchBookName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT book_code, book_name, book_content, book_price, book_translator, book_quantity, book_publisher, book_author, book_promotion_id, book_category_id FROM book \n" +
            "WHERE book_flag = 0 AND book_status = 0 AND book_id = :id", nativeQuery = true)
    Book findBookByBookFlagAndBookStatus(@Param("id") Long cartId);

    @Query(value = "select book_code, book_name, book_content, book_price, book_translator, book_quantity, book_publisher, book_author, book_promotion_id, book_category_id from book where  book_flag = 0", nativeQuery = true)
    List<Book> findBookBySameBookAuthor();

    @Query(value = "INSERT INTO `book` (`book_code`, `book_name`, `book_image`, `book_content`, " +
            "`book_price`,`book_translator`,`book_weight`, `book_publish_date`, `book_quantity`," +
            " `book_publisher`, `book_author`, `book_promotion_id`, `book_category_id`)" +
            " VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8,?9,?10,?11,?12,?13)", nativeQuery = true)
    @Transactional
    @Modifying
    void createBook(String bookCode, String bookName, String image,String content,Double price,String translator,
                    String weight, LocalDate publishDate, Integer quantity, String bookPublisher,String bookAuthor,
                    Long bookPromotionPd, Long bookCategoryId);

    @Query(value = "SELECT * FROM book WHERE book_category_id= :categoryId ", nativeQuery = true)
    List<Book> findAllByBookCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "select * from book where book_id= :bookId AND book_flag = 0 LIMIT 1", nativeQuery = true)
    Optional<Book> existsByBookId(@Param("bookId") Long bookId);

}
