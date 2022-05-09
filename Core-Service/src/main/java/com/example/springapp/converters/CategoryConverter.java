package com.example.springapp.converters;

import com.example.springapp.dto.CategoryDto;
import com.example.springapp.entities.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    public List<CategoryDto> entityToDto(List<Category> categories){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(Category category: categories){
            categoryDtoList.add(new CategoryDto(category.getName()));
        }
        return categoryDtoList;
    }

    public Category dtoToEntity(CategoryDto categoryDto) {
        throw new UnsupportedOperationException();
    }
}
