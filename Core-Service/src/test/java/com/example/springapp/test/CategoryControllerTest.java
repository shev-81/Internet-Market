//package com.example.springapp.test;
//
//import com.example.springapp.entities.Category;
//import com.example.springapp.repositories.CategoriesRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
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
//public class CategoryControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CategoriesRepository categoriesRepository;
//
//    @Test
//    // @WithMockUser(username = "Bob", authorities = "USER")
//    public void getAllGenresTest() throws Exception {
//        Category category = new Category();
//        category.setId(1L);
//        category.setName("Technics");
//        List<Category> allCategory = new ArrayList<>(Arrays.asList(category));
//
//        given(categoriesRepository.getAll()).willReturn(allCategory);
//
//        mvc
//                .perform(
//                        get("/api/v1/categories")
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(allCategory.get(0).getName())));
//    }
//}
