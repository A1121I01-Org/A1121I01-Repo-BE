package com.example.demologin.repository;

import com.example.demologin.entity.book.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<BookCategory, Long> {

    @Query(value = "SELECT * FROM category", nativeQuery = true)
    List<BookCategory> getListCategory();
}
