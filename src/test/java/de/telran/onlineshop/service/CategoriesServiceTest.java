package de.telran.onlineshop.service;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.repository.CategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoriesServiceTest {

    @Mock
    private CategoriesRepository categoriesRepositoryMock;

    @InjectMocks
    private CategoriesService categoriesServiceTest; // объект тестирования (unit-объект)

    private CategoriesEntity categoryEntity1;
    private CategoryDto categoryDto1;

    @BeforeEach
    void setUp() {
        categoryEntity1 = new CategoriesEntity(1L, "Продукты");

        categoryDto1 = new CategoryDto(1L, "Продукты");
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void getCategoryById() {
    }

    @Test
    void getCategoryByName() {
    }

    @Test
    void createCategories() {
    }

    @Test
    void updateCategories() {
    }

    @Test
    void deleteCategories() {
    }
}