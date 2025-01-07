package de.telran.onlineshop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.*;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // запускаем контейнер Spring для тестирования
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = {"dev"})
public class CategoriesIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // для имитации запросов пользователей

    @MockBean
    private CategoriesRepository categoriesRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getIntegrationAllCategoriesTest() throws Exception {
        when(categoriesRepositoryMock.findAll()).thenReturn(List.of(new CategoriesEntity(1L, "Test")));

        this.mockMvc.perform(get("/categories"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..categoryID").exists())
                .andExpect(jsonPath("$..name").exists());
    }

    @Test
    void createIntegrationCategoriesTest() throws Exception {
        CategoryDto categoryDtoInput = new CategoryDto(
                null,
                "Test");

        when(categoriesRepositoryMock.save(any(CategoriesEntity.class))).thenReturn(
                new CategoriesEntity(
                        1L,
                        "Test"));

        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated());
        // .andExpect(jsonPath("$.name").exists());
    }

    @Test
    void updateIntegrationCategoriesTest() throws Exception {
        CategoryDto categoryDtoInput = new CategoryDto(
                1L,
                "Test");

        when(categoriesRepositoryMock.save(any(CategoriesEntity.class))).thenReturn(
                new CategoriesEntity(
                        1L,
                        "Test111"));

        this.mockMvc.perform(put("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.categoryID").value(1L))
                .andExpect(jsonPath("$.name").value("Test111"));
    }

    @Test
    void deleteIntegrationCategoriesTest() throws Exception {
        Long inputId = 1L;
        //when(categoriesRepositoryMock.findById(inputId)).thenReturn(Optional.ofNullable(null));

        this.mockMvc.perform(delete("/categories/{id}", inputId))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
