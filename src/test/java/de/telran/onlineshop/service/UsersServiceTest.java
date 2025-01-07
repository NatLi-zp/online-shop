package de.telran.onlineshop.service;

import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.*;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.mapper.Mappers;
import de.telran.onlineshop.repository.CartRepository;
import de.telran.onlineshop.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock  // объект, поведение которого мы будем имитировать
    private UsersRepository usersRepositoryMock;

    @Mock
    private CartRepository cartRepositoryMock;

    @Mock
    private Mappers mappersMock;

    @InjectMocks
    private UsersService usersServiceTest; // объект тестирования (unit-объект)

    private UsersEntity userEntityTest1;
    private UserDto userDToTest1;

    @BeforeEach
    void setUp() {
        userEntityTest1 = new UsersEntity(
                1L,
                "Вася Пупкин",
                "vasya@i.com",
                "+4912345977",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<OrdersEntity>()
                //   new HashSet<AddressEntity>()
        );

        userDToTest1 = new UserDto(
                1L,
                "Вася Пупкин",
                "vasya@i.com",
                "+4912345977",
                "Password1",
                Role.CLIENT
        );
    }

    @Test
    void getAllUsers() {

        UsersEntity userEntityTest2 = new UsersEntity(
                2L,
                "Дуня Смирнова",
                "dunya@i.com",
                "+4912345977",
                "Password2",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<OrdersEntity>()
             //   new HashSet<AddressEntity>()
        );

        UserDto userDToTest2 = new UserDto(
                2L,
                "Дуня Смирнова",
                "dunya@i.com",
                "+4912345977",
                "Password2",
                Role.CLIENT
        );


        when(usersRepositoryMock.findAll()).thenReturn(List.of(userEntityTest1, userEntityTest2));
        when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDToTest1);
        when(mappersMock.convertToUserDto(userEntityTest2)).thenReturn(userDToTest2);

        List<UserDto> actualUserDtoList = usersServiceTest.getAllUsers();

        //проверка
        assertNotNull(actualUserDtoList);
        assertTrue(actualUserDtoList.size() > 0);
        assertEquals(2, actualUserDtoList.size());
        assertEquals(1, actualUserDtoList.get(0).getUserID());
        assertEquals(userDToTest1, actualUserDtoList.get(0));
        verify(usersRepositoryMock).findAll(); // был ли запущен этот метод
        verify(mappersMock, times(2)).convertToUserDto(any(UsersEntity.class)); // был ли запущен этот метод и ск. раз
    }

    @Test
    void getUserById() {
        Long testId = 1L;
        when(usersRepositoryMock.findById(testId)).thenReturn(Optional.of(userEntityTest1));
        when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDToTest1);

        UserDto actualUserDto = usersServiceTest.getUserById(testId);

        //проверка
        assertNotNull(actualUserDto);
        assertEquals(testId, actualUserDto.getUserID());
        assertEquals(userDToTest1, actualUserDto);
        verify(usersRepositoryMock).findById(testId); // был ли запущен этот метод
        verify(mappersMock, times(1)).convertToUserDto(any(UsersEntity.class));
    }

    @Test
    void getUserByName() {
    }

    @Test
    void createUsers() {


    }

    @Test
    void updateUsers() {

        when(mappersMock.convertToUserEntity(userDToTest1)).thenReturn(userEntityTest1);
        when(usersRepositoryMock.save(userEntityTest1)).thenReturn(userEntityTest1);
        when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDToTest1);

        UserDto actualUserDto = usersServiceTest.updateUsers(userDToTest1);

        // проверка
        assertNotNull(actualUserDto);
        assertEquals(userDToTest1.getUserID(), actualUserDto.getUserID());
        assertEquals(userDToTest1, actualUserDto);

        verify(mappersMock).convertToUserEntity(userDToTest1); //был ли запущен этот метод
        verify(usersRepositoryMock).save(userEntityTest1); //был ли запущен этот метод
        verify(mappersMock).convertToUserDto(userEntityTest1); //был ли запущен этот метод
    }


    @Test
    void insertUsersTest() {
        UsersEntity userEntityTestInput = new UsersEntity(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+4912345977",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<OrdersEntity>()
           //     new HashSet<AddressEntity>()
        );

        UserDto userDtoTestInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+4912345977",
                "Password1",
                Role.CLIENT
        );

        when(mappersMock.convertToUserEntity(userDtoTestInput)).thenReturn(userEntityTestInput);
        when(usersRepositoryMock.save(userEntityTestInput)).thenReturn(userEntityTest1);
        when(mappersMock.convertToUserDto(userEntityTest1)).thenReturn(userDToTest1);

        UserDto actualUserDto = usersServiceTest.insertUsers(userDtoTestInput); // запуск реального метода

        // проверка
        assertNotNull(actualUserDto);
        assertNotNull(actualUserDto.getUserID());
        assertNotEquals(userDtoTestInput.getUserID(), actualUserDto.getUserID());
        assertNotEquals(userDtoTestInput, actualUserDto);

        verify(mappersMock).convertToUserEntity(userDtoTestInput); //был ли запущен этот метод
        verify(usersRepositoryMock).save(userEntityTestInput); //был ли запущен этот метод
        verify(mappersMock).convertToUserDto(userEntityTest1); //был ли запущен этот метод

    }

    @Test
    void deleteUsersByIdTest() {

        Long testId = 1L;
        when(usersRepositoryMock.findById(testId)).thenReturn(Optional.of(userEntityTest1));

        usersServiceTest.deleteUsersById(testId); // запуск реального метода
        verify(usersRepositoryMock).delete(userEntityTest1); //был ли запущен этот метод
    }

    // Exception
    @Test
    void deleteUsersByIdNotFoundTest() {
        Long testId = 1L;
        when(usersRepositoryMock.findById(testId)).thenReturn(Optional.ofNullable(null));
        assertThrows(NullPointerException.class, () -> usersServiceTest.deleteUsersById(testId));
    }

    @Test
    void initTest() {
        when(cartRepositoryMock.save(any(CartEntity.class))).thenReturn(new CartEntity());
        when(usersRepositoryMock.save(any(UsersEntity.class))).thenReturn(new UsersEntity());
        usersServiceTest.init();
        verify(cartRepositoryMock,times(3)).save(any(CartEntity.class));
        verify(usersRepositoryMock,times(3)).save(any(UsersEntity.class));
    }
}