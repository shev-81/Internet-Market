package com.example.springapp.controllers;

import com.example.springapp.converters.CategoryConverter;
import com.exemple.spring.core.CategoryDto;
import com.example.springapp.entities.Category;
import com.example.springapp.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Категории", description = "Методы работы с категориями продуктов")
public class CategoriesController {
    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @GetMapping
    @Operation(
            summary = "Запрос на получение списка категорий товаров",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))
                                    )
                    )
            }
    )
    public List<CategoryDto> allCategories(){
        List<Category> listCategory = categoryService.getAllCategories();
        List<CategoryDto> listCategoryDto = categoryConverter.entityToDto(listCategory);
        return listCategoryDto;
    }

}
