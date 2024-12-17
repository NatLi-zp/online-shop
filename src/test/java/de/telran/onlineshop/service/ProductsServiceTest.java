package de.telran.onlineshop.service;

import de.telran.onlineshop.dto.ProductDto;
import de.telran.onlineshop.entity.*;
import de.telran.onlineshop.mapper.Mappers;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    @Mock
    private ProductsRepository productsRepositoryMock;

    @Mock
    private CategoriesRepository categoriesRepositoryMock;

    @Mock
    private Mappers mappersMock;

    @InjectMocks
    private ProductsService productsServiceTest; // объект тестирования (unit-объект)

    private ProductsEntity productEntityTest1;
    private ProductDto productDtoTest1;
    private Timestamp timestamp;
    private CategoriesEntity categoryEntity1;

    @BeforeEach
    void setUp() {

        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());

        CategoriesEntity categoryEntity1 = categoriesRepositoryMock.findById(3L).orElse(null);

        productEntityTest1 = new ProductsEntity(
                1L,
                "Телефон",
                "Мобильный телефон Samsumg-A5",
                120.20,
                "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg",
                110.10,
                timestamp,
                timestamp,
                categoryEntity1,
                new HashSet<FavoritesEntity>(),
                new HashSet<OrderItemsEntity>(),
                new HashSet<CartItemsEntity>());

        productDtoTest1 = new ProductDto(
                1L,
                "Телефон",
                "Мобильный телефон Samsumg-A5",
                120.20,
                "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg",
                110.10,
                timestamp,
                timestamp);
    }

    @Test
    void getAllProducts() {
        ProductsEntity productEntityTest2 = new ProductsEntity(
                2L,
                "Телефон2",
                "Мобильный телефон Samsumg-A522",
                130.20,
                "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg",
                100.10,
                timestamp,
                timestamp,
                categoryEntity1,
                new HashSet<FavoritesEntity>(),
                new HashSet<OrderItemsEntity>(),
                new HashSet<CartItemsEntity>());

        ProductDto productDtoTest2 = new ProductDto(
                2L,
                "Телефон2",
                "Мобильный телефон Samsumg-A522",
                130.20,
                "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg",
                100.10,
                timestamp,
                timestamp);

        when(productsRepositoryMock.findAll()).thenReturn(List.of(productEntityTest1, productEntityTest2));
        when(mappersMock.convertToProductDto(productEntityTest1)).thenReturn(productDtoTest1);
        when(mappersMock.convertToProductDto(productEntityTest2)).thenReturn(productDtoTest2);

        List<ProductDto> actualProductDtoList = productsServiceTest.getAllProducts();

        //проверка
        assertNotNull(actualProductDtoList);
        assertTrue(actualProductDtoList.size() > 0);
        assertEquals(2, actualProductDtoList.size());
        assertEquals(1, actualProductDtoList.get(0).getProductID());
        assertEquals(productDtoTest1, actualProductDtoList.get(0));
        verify(productsRepositoryMock).findAll(); // был ли запущен этот метод
        verify(mappersMock, times(2)).convertToProductDto(any(ProductsEntity.class)); // был ли запущен этот метод и ск. раз

    }

    @Test
    void getProductById() {
        Long testId = 1L;
        when(productsRepositoryMock.findById(testId)).thenReturn(Optional.of(productEntityTest1));
        when(mappersMock.convertToProductDto(productEntityTest1)).thenReturn(productDtoTest1);

        ProductDto actualProductDto = productsServiceTest.getProductById(testId);

        //проверка
        assertNotNull(actualProductDto);
        assertEquals(testId, actualProductDto.getProductID());
        assertEquals(productDtoTest1, actualProductDto);
        verify(productsRepositoryMock).findById(testId); // был ли запущен этот метод
        verify(mappersMock, times(1)).convertToProductDto(any(ProductsEntity.class));
    }

    @Test
    void getProductByName() {
        String testName = "Телефон";
        when(productsRepositoryMock.findByNameNative(testName)).thenReturn(productEntityTest1);
        when(mappersMock.convertToProductDto(productEntityTest1)).thenReturn(productDtoTest1);

        ProductDto actualProductDto = productsServiceTest.getProductByName(testName);

        //проверка
        assertNotNull(actualProductDto);
        assertEquals(testName, actualProductDto.getName());
        assertEquals(productDtoTest1, actualProductDto);
        verify(productsRepositoryMock).findByNameNative(testName); // был ли запущен этот метод
        verify(mappersMock, times(1)).convertToProductDto(any(ProductsEntity.class));
    }


    @Test
    void insertProducts() {
        ProductsEntity productEntityTestInput = new ProductsEntity(
                1L,
                "Телефон",
                "Мобильный телефон Samsumg-A5",
                120.20,
                "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg",
                110.10,
                timestamp,
                timestamp,
                categoryEntity1,
                new HashSet<FavoritesEntity>(),
                new HashSet<OrderItemsEntity>(),
                new HashSet<CartItemsEntity>());

        ProductDto productDtoTestInput = new ProductDto(
                1L,
                "Телефон",
                "Мобильный телефон Samsumg-A5",
                120.20,
                "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg",
                110.10,
                timestamp,
                timestamp);

        when(mappersMock.convertToProductEntity(productDtoTestInput)).thenReturn(productEntityTestInput);
        when(productsRepositoryMock.save(productEntityTestInput)).thenReturn(productEntityTest1);
        when(mappersMock.convertToProductDto(productEntityTest1)).thenReturn(productDtoTest1);

        ProductDto actualProductDto = productsServiceTest.insertProducts(productDtoTestInput); // запуск реального метода

        // проверка
        assertNotNull(actualProductDto);
        assertNotNull(actualProductDto.getProductID());
        assertNotEquals(productEntityTestInput.getProductId(), actualProductDto.getProductID());
        assertNotEquals(productDtoTestInput, actualProductDto);

        verify(mappersMock).convertToProductEntity(productDtoTestInput); //был ли запущен этот метод
        verify(productsRepositoryMock).save(productEntityTestInput); //был ли запущен этот метод
        verify(mappersMock).convertToProductDto(productEntityTest1); //был ли запущен этот метод

    }

    @Test
    void updateProducts() {
        when(mappersMock.convertToProductEntity(productDtoTest1)).thenReturn(productEntityTest1);
        when(productsRepositoryMock.save(productEntityTest1)).thenReturn(productEntityTest1);
        when(mappersMock.convertToProductDto(productEntityTest1)).thenReturn(productDtoTest1);

        ProductDto actualProductDto = productsServiceTest.updateProducts(productDtoTest1);

        // проверка
        assertNotNull(actualProductDto);
        assertEquals(productDtoTest1.getProductID(), actualProductDto.getProductID());
        assertEquals(productDtoTest1, actualProductDto);

        verify(mappersMock).convertToProductEntity(productDtoTest1); //был ли запущен этот метод
        verify(productsRepositoryMock).save(productEntityTest1); //был ли запущен этот метод
        verify(mappersMock).convertToProductDto(productEntityTest1); //был ли запущен этот метод
    }


    @Test
    void deleteProductsById() {
        Long testId = 1L;
        when(productsRepositoryMock.findById(testId)).thenReturn(Optional.of(productEntityTest1));

        productsServiceTest.deleteProductsById(testId); // запуск реального метода
        verify(productsRepositoryMock).delete(productEntityTest1); //был ли запущен этот метод

    }

    // Exception
    @Test
    void deleteProductsByIdNotFoundTest() {
        Long testId = 1L;
        when(productsRepositoryMock.findById(testId)).thenReturn(Optional.ofNullable(null));
        assertThrows(NullPointerException.class, () -> productsServiceTest.deleteProductsById(testId));
    }

}