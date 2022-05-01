package com.example.springapp.repositories;

import com.example.springapp.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends CrudRepository <Category, Long> {
    @Query("select c from Category c")
    List<Category> getAll();

    @Query("select c.id from Category c WHERE c.name = :nameCategory")
    Integer findIdByName(String nameCategory);
}
