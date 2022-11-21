package com.example.demologin.service.Impl;

import com.example.demologin.entity.book.BookCategory;
import com.example.demologin.repository.CategoryRepository;
import com.example.demologin.service.ICartService;
import com.example.demologin.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<BookCategory> getListCategory() {
        return categoryRepository.getListCategory();
    }
}
