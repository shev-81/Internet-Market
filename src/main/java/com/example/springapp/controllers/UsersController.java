package com.example.springapp.controllers;

import com.example.springapp.converters.UserConverter;
import com.example.springapp.dto.UserDto;
import com.example.springapp.entities.User;
import com.example.springapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public String loadUsersPage() {
        return "usersfront";
    }

    @GetMapping("/all")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> allUsers() {
        return userService.getAll().stream().map(userConverter::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public void delProducts(@PathVariable Long id){
        userService.deleteById(id);
    }
//    @GetMapping("/users/auth_page")
//    @ResponseBody
//    public String authenticatedPage() {
//        return "authenticated";
//    }
//
//    @GetMapping("/users/admin")
//    @ResponseBody
//    // @PreAuthorize("hasRole('ADMIN')")
//    public String adminPage() {
//        return "admin";
//    }

    @GetMapping("/user_info")
    @ResponseBody
    @PreAuthorize("hasAuthority('READER_BOOK')")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
}
