package com.example.springapp.converters;

import com.example.springapp.dto.UserDto;
import com.example.springapp.entities.Role;
import com.example.springapp.entities.User;
import com.example.springapp.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Component
public class UserConverter {

    private UserService userService;

    public User dtoToEntity(UserDto userDto) {
        User u = new User();
        u.setId(userDto.getId());
        u.setUsername(userDto.getName());
        u.setEmail(userDto.getMail());
        return u;
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), getRole(user) , user.getEmail());
    }

    @Transactional
    public String getRole (User user){
        return userService.findByUsername(user.getUsername()).get().getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));
    }
}
