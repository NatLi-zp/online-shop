package de.telran.onlineshop.repository;

import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.entity.enums.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepositoryTest;

    private static final String NAME_TEST = "Test";
    private static UsersEntity usersEntityNewTest;
    //private CartRepository cartRepositoryTest;

    @BeforeAll
    static void setStart() {

        //CartEntity cart1 = new CartEntity();
        //cart1 = cartRepository.save(cart1);
        //usersEntityNewTest = new UsersEntity(null, NAME_TEST, "petrov@gmail.com", "+49123123123", "111", Role.CLIENT, cart1, null, null, null);
        usersEntityNewTest = new UsersEntity(null, NAME_TEST, "petrov@gmail.com", "+49123123123", "111", Role.CLIENT, null, null, null, null);

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
        List<UsersEntity> usersTest = usersRepositoryTest.findAll();
        //System.out.println(usersTest.toString());
        assertNotNull(usersTest);
    }

    @Test
    void insertTest() {
        String nameExpected = NAME_TEST;
        UsersEntity usersTest = usersEntityNewTest;

        UsersEntity usersActual = usersRepositoryTest.save(usersTest);

        assertNotNull(usersActual);
        assertTrue(usersActual.getUserId() != null);
        assertEquals(nameExpected, usersActual.getName());
    }

    @Test
    void updateTest() {
        // when
        UsersEntity usersExpected = generateTestData();

        // test
        String nameExpected = "NewTest";
        // if(true) throw new NullPointerException("Imitation");
        usersExpected.setName(nameExpected);
        UsersEntity usersActual = usersRepositoryTest.save(usersExpected);

        //assert
        assertNotNull(usersActual);
        assertEquals(nameExpected, usersActual.getName());

    }

    @Test
    void findByNameTest() {
        //when
        UsersEntity usersExpected = generateTestData();

        // run
        String nameExpected = usersExpected.getName();
        UsersEntity usersActual = usersRepositoryTest.findByName(nameExpected);

        //assert
        assertNotNull(usersActual);
        assertEquals(nameExpected, usersActual.getName());

        // подчищаем ?
    }

    @Test
    void deleteTest() {
        //when
        UsersEntity usersExpected = generateTestData();

        //run

        // 1 вариант удаление
        Long idTest = usersExpected.getUserId();
        usersRepositoryTest.deleteById(idTest);

        // 2 вариант удаление
        usersRepositoryTest.delete(usersExpected);

        //проверка
        // 1 вариант
        Optional<UsersEntity> usersActualOptional = usersRepositoryTest.findById(idTest);
        assertFalse(usersActualOptional.isPresent());

        // 2 вариант
        UsersEntity usersActual = usersRepositoryTest.findById(idTest).orElse(null);
        assertNull(usersActual);
    }

    @Test
    void findByNameNativeTest() {
        //when
        UsersEntity usersExpected = generateTestData();

        // run
        String nameExpected = usersExpected.getName();
        UsersEntity usersActual = usersRepositoryTest.findByNameNative(nameExpected);

        //assert
        assertNotNull(usersActual);
        assertEquals(nameExpected, usersActual.getName());

        // подчищаем ?
    }

    private UsersEntity generateTestData() {
        UsersEntity usersInsert = usersRepositoryTest.save(usersEntityNewTest);
        assertNotNull(usersInsert);
        assertTrue(usersInsert.getUserId() != null);
        assertEquals(NAME_TEST, usersInsert.getName());
        return usersInsert;
    }
}