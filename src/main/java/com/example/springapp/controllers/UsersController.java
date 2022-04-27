package com.example.springapp.controllers;

import com.example.springapp.converters.UserConverter;
import com.example.springapp.dto.UserDto;
import com.example.springapp.entities.User;
import com.example.springapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> allUsers() {
        return userService.getAll().stream().map(userConverter::entityToDto).collect(Collectors.toList());
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public void createNewUser(@RequestBody UserDto userDto){
        User newUser = userConverter.dtoToEntity(userDto);
        userService.createNewUser(newUser, userDto.getRoles());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delProducts(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping("/user_info")
    @PreAuthorize("hasAuthority('READER_BOOK')")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
}
