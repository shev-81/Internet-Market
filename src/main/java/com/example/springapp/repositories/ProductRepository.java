package com.example.springapp.repositories;

import com.example.springapp.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.price < :price")
    List<Product> lowPrice(Integer price);

    @Query("select p from Product p where p.price > :price")
    List<Product> hiPrice(Integer price);

    @Query("select p from Product p where p.price between :priceOne and :priceTwo")
    List<Product> findAllBetweenPrice(Integer priceOne, Integer priceTwo);
    }



    // @Query("select s from Student s where s.score between ?1 and ?2")
//    List<Student> findAllByScoreBetween(Integer min, Integer max);

    // @Query("select s from Student s where s.name = :name")
//    Optional<Student> findByName(String name);

//    @Query("select s from Student s where s.score < 20")
//    List<Student> findLowRatingStudents();

//    @Query("select s.score from Student s where s.name = ?1")
//    Integer hqlGetScoreByName(String name);

//    @Query(value = "select score from students where name = :name", nativeQuery = true)
//    Integer nativeSqlGetScoreByName(String name);

// Если бы у студентов был List<Book>, то не ленивая загрузка книг:
// @Query("select s from Student s join fetch s.books where s.id = :id")
// Optional<Student> findByIdWithBooks(String name);