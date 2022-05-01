package com.example.springapp.controllers;

import com.example.springapp.converters.CategoryConverter;
import com.example.springapp.dto.CategoryDto;
import com.example.springapp.entities.Category;
import com.example.springapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @GetMapping
    public List<CategoryDto> allCategories(){
        List<Category> listCategory = categoryService.getAllCategories();
        List<CategoryDto> listCategoryDto = categoryConverter.entityToDto(listCategory);
        return listCategoryDto;
    }

}
