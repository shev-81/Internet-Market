//package com.example.springapp.test;
//
//import com.example.springapp.entities.Category;
//import com.example.springapp.entities.Product;
//import com.example.springapp.repositories.CategoriesRepository;
//import com.example.springapp.repositories.ProductRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ProductControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private ProductRepository productRepository;
//
//    @Test
//    // @WithMockUser(username = "Bob", authorities = "USER")
//    public void getAllGenresTest() throws Exception {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Orange");
//        product.setPrice(BigDecimal.valueOf(112.21));
//
//        List<Product> allProducts = new ArrayList<>(Arrays.asList(product));
//
//        given(productRepository.findAll()).willReturn(allProducts);
//
//        mvc
//                .perform(
//                        get("/api/v1/products/all")
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(allProducts.get(0).getName())));
//    }
//}
