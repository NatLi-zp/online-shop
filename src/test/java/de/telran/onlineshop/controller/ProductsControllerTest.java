package de.telran.onlineshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.ProductDto;
import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.service.CategoriesService;
import de.telran.onlineshop.service.ProductsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc; // для имитации запросов пользователей

    @MockBean
    private ProductsService productsServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    private static Timestamp timestamp;

    @BeforeAll
    static void setStart() {
        Date currentDate = new Date();
        timestamp = new Timestamp(currentDate.getTime());
    }
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

//    @Test
//    void setProductsService() {
//    }

    @Test
    void getAllProducts() throws Exception {
//        Date currentDate = new Date();
//        Timestamp timestamp = new Timestamp(currentDate.getTime());

        when(productsServiceMock.getAllProducts()).thenReturn(List.of(new ProductDto(1L, "Test", "TestName", 120.20, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", 110.10, timestamp, timestamp)));
        this.mockMvc.perform(get("/products"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..productID").exists())
                .andExpect(jsonPath("$..productID").value(1))
                .andExpect(jsonPath("$..name").value("Test"));
    }

    @Test
    void getProductById() throws Exception {
        // Long testId = 1L;
        //when(productsServiceMock.getProductById(testId)).thenReturn(new ProductDto(testId,"Test"));
        when(productsServiceMock.getProductById(anyLong())).thenReturn(new ProductDto(1L, "Test", "TestName", 120.20, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", 110.10, timestamp, timestamp));
        this.mockMvc.perform(get("/products/find/{id}", 1)) ///products/find/1
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productID").exists())
                .andExpect(jsonPath("$.productID").value(1))
                .andExpect(jsonPath("$.name").value("Test"));
    }

//    @Test
//    void getProductByName() {
//    }

    @Test
    void createProducts() throws Exception {
        when(productsServiceMock.createProducts(any(ProductDto.class))).thenReturn(true);
        this.mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "productID": null,
                                    "name": "TestName"
                                }
                                """
                        ))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateProducts() throws Exception {
        ProductDto inputProduct = new ProductDto(1L, "NewTestName", null, null, null, null, timestamp, timestamp);
        ProductDto expectedProduct = inputProduct; // редактирование прошло успешно

        when(productsServiceMock.updateProducts(inputProduct))
                .thenReturn(new ProductDto(inputProduct.getProductID(), inputProduct.getName(),
                        inputProduct.getDescription(), inputProduct.getPrice(), inputProduct.getImageURL(), inputProduct.getDiscountPrice(), timestamp, timestamp)); //.thenReturn(expectedProduct)

        this.mockMvc.perform(put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputProduct))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.productID").exists())
                .andExpect(jsonPath("$.productID").value(expectedProduct.getProductID()))
                .andExpect(jsonPath("$.name").value(expectedProduct.getName()));
    }

    @Test
    void deleteProducts() throws Exception {
        Long inputId = 1L;

        this.mockMvc.perform(delete("/products/{id}", inputId)) ///products/1
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk());

        //return void
        verify(productsServiceMock).deleteProducts(inputId);
    }
}