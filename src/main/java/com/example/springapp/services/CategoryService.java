package com.example.springapp.services;

import com.example.springapp.entities.Category;
import com.example.springapp.repositories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoriesRepository categoriesRepository;

    public List<Category> getAllCategories(){
        return categoriesRepository.getAll();
    }

    public Category getCategoryByName(String nameCategory){
        return categoriesRepository.findByName(nameCategory);
    }

    public Integer getIdCategoryByName(String nameCategory){
        return categoriesRepository.findIdByName(nameCategory);
    }
}

