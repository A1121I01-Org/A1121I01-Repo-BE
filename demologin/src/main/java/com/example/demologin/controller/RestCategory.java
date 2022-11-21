package com.example.demologin.controller;

import com.example.demologin.entity.book.BookCategory;
import com.example.demologin.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/category")
public class RestCategory {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<BookCategory>> getListCategory() {
        List<BookCategory> bookCategories = categoryService.getListCategory();
        return new ResponseEntity<>(bookCategories, HttpStatus.OK);
    }
}
