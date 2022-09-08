package com.example.springapp.test;


import com.example.spring.auth.entities.Role;
import com.example.spring.auth.entities.User;
import com.example.spring.auth.repositories.RoleRepository;
import com.example.spring.auth.repositories.UserRepository;
import com.example.spring.auth.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void findOneUserTest() {
        User userFromDB = new User();
        userFromDB.setUsername("John");
        userFromDB.setEmail("john@mail.ru");
        // ? Mockito.when().thenReturn();
        Role userRole = new Role();
        userRole.setId(1l);
        userRole.setName("ADMIN");

        Mockito.doReturn(userRole)
                .when(roleRepository)
                .findRoleByName("John");
        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findByUsername("John");
        User userJohn = userService.findByUsername("John").get();
        Assertions.assertNotNull(userJohn);
        Assertions.assertEquals("john@mail.ru", userJohn.getEmail());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
    }
}
