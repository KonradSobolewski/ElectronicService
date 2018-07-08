package com.example.electronicservice.services;

import com.example.electronicservice.dao.CategoryDao;
import com.example.electronicservice.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public Optional<Category> getCategoryByName(String name){
        return categoryDao.findByName(name);
    }
}
