//package com.example.springapp.controllers;
//
//import com.geekbrains.spring.web.data.Student;
//import com.geekbrains.spring.web.repositories.StudentRepository;
//import com.geekbrains.spring.web.services.StudentService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class StudentController {
//    private StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @GetMapping("/students")
//    public List<Student> getAllStudents() {
//        return studentService.getAllStudents();
//    }
//
//    @GetMapping("/students/delete/{id}")
//    public void deleteById(@PathVariable Long id) {
//        studentService.deleteById(id);
//    }
//
//    @GetMapping("/students/change_score")
//    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
//        studentService.changeScore(studentId, delta);
//    }
//}
