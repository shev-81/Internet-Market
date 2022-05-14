package com.example.springapp.test;

import com.exemple.spring.core.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullRestTest() {
        // Spring page class ...
        List<ProductDto> products = restTemplate.getForObject("/api/v1/products/all", List.class);
        assertThat(products).isNotNull();
        assertThat(products).isNotEmpty();
        System.out.println(products);
    }
}
