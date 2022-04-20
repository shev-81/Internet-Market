package com.example.springapp.converters;

import com.example.springapp.dto.UserDto;
import com.example.springapp.entities.Role;
import com.example.springapp.entities.User;
import com.example.springapp.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@Component
public class UserConverter {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public User dtoToEntity(UserDto userDto) {
        User u = new User();
        List<Role> roleList = new ArrayList<>();
        u.setId(0L);
        u.setUsername(userDto.getName());
        u.setEmail(userDto.getMail());
        u.setPassword(passwordEncoder.encode(userDto.getPass()));
        u.setRoles(roleList);  //todo надо ли это?
        return u;
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), getRole(user) , user.getEmail(), "");
    }

    @Transactional
    public String getRole (User user){
        return userService.findByUsername(user.getUsername()).get().getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));
    }
}
