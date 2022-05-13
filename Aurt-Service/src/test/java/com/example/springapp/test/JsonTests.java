//package com.example.springapp.test;
//
//
//import com.exemple.spring.auth.dto.UserDto;
//import com.exemple.spring.auth.entities.Role;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.JsonTest;
//import org.springframework.boot.test.json.JacksonTester;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@JsonTest
//public class JsonTests {
//    @Autowired
//    private JacksonTester<UserDto> jackson;
//
//    @Test
//    public void jsonSerializationTest() throws Exception {
//        UserDto user = new UserDto(1L,"USER","ADMIN","USER@user.ru","asd");
//        // {
//        //   "id": 1,
//        //   "name": "USER"
//        // }
//
//        assertThat(jackson.write(user))
//                .hasJsonPathNumberValue("$.id")
//                .extractingJsonPathStringValue("$.name").isEqualTo("USER");
//    }

//    @Test
//    public void jsonDeserializationTest() throws Exception {
//        String content = "{\"id" +"\": 2," + "\"name" + "\":" + "\"ADMIN\"}";
//        Role realRole = new Role();
//        realRole.setId(2L);
//        realRole.setName("ADMIN");
//
//        assertThat(jackson.parse(content)).isEqualTo(realRole);
//        assertThat(jackson.parseObject(content).getName()).isEqualTo("ADMIN");
//    }
//}