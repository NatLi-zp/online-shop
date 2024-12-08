package de.telran.onlineshop.repository;

import de.telran.onlineshop.entity.ProductsEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductsRepositoryTest {

    @Autowired
    private ProductsRepository productsRepositoryTest;
    private CategoriesRepository categoriesRepositoryTest;

    private static final String NAME_TEST = "Test";
    private static ProductsEntity productsEntityNewTest;

    @BeforeAll
    static void setStart() {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        //productsEntityNewTest = new ProductsEntity(null, NAME_TEST,"Мобильный телефон Samsumg-A5", 120.20, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", 110.10, timestamp, timestamp, category1, null, null,null);;
        productsEntityNewTest = new ProductsEntity(null, NAME_TEST, "Мобильный телефон Samsumg-A5", 120.20, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", 110.10, timestamp, timestamp, null, null, null, null);
        ;

        System.out.println("Выполняется setStart(единоразово, перед запуском всех тестов)!");
    }

    @AfterAll
    static void setEnd() {
        System.out.println("Выполняется setEnd(единоразово, после выполнения всех тестов!");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Выполняется setUp (перед каждым тестом)!");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Выполняется tearDown (после каждого тестом)!");
    }

    @Test
    void findAllTest() {
        List<ProductsEntity> productsTest = productsRepositoryTest.findAll();
        assertNotNull(productsTest);
    }

    @Test
    void insertTest() {
        String nameExpected = NAME_TEST;
        ProductsEntity productsTest = productsEntityNewTest;

        ProductsEntity productsActual = productsRepositoryTest.save(productsTest);

        assertNotNull(productsActual);
        assertTrue(productsActual.getProductId() != null);
        assertEquals(nameExpected, productsActual.getName());
    }

    @Test
    void updateTest() {
        // when
        ProductsEntity productsExpected = generateTestData();

        // test
        String nameExpected = "NewTest";
        // if(true) throw new NullPointerException("Imitation");
        productsExpected.setName(nameExpected);
        ProductsEntity productsActual = productsRepositoryTest.save(productsExpected);

        //assert
        assertNotNull(productsActual);
        assertEquals(nameExpected, productsActual.getName());

    }

    @Test
    void findByNameTest() {
        //when
        ProductsEntity productsExpected = generateTestData();

        // run
        String nameExpected = productsExpected.getName();
        ProductsEntity productsActual = productsRepositoryTest.findByName(nameExpected);

        //assert
        assertNotNull(productsActual);
        assertEquals(nameExpected, productsActual.getName());

        // подчищаем ?
    }

    @Test
    void deleteTest() {
        //when
        ProductsEntity productsExpected = generateTestData();

        //run

        // 1 вариант удаление
        Long idTest = productsExpected.getProductId();
        productsRepositoryTest.deleteById(idTest);

        // 2 вариант удаление
        productsRepositoryTest.delete(productsExpected);

        //проверка
        // 1 вариант
        Optional<ProductsEntity> productsActualOptional = productsRepositoryTest.findById(idTest);
        assertFalse(productsActualOptional.isPresent());

        // 2 вариант
        ProductsEntity productsActual = productsRepositoryTest.findById(idTest).orElse(null);
        assertNull(productsActual);
    }

    @Test
    void findByNameNativeTest() {
        //when
        ProductsEntity productsExpected = generateTestData();

        // run
        String nameExpected = productsExpected.getName();
        ProductsEntity productsActual = productsRepositoryTest.findByNameNative(nameExpected);

        //assert
        assertNotNull(productsActual);
        assertEquals(nameExpected, productsActual.getName());

        // подчищаем ?
    }

    private ProductsEntity generateTestData() {
        ProductsEntity productsInsert = productsRepositoryTest.save(productsEntityNewTest);
        assertNotNull(productsInsert);
        assertTrue(productsInsert.getProductId() != null);
        assertEquals(NAME_TEST, productsInsert.getName());
        return productsInsert;
    }
}