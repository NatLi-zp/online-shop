package de.telran.onlineshop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.OrdersEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.repository.UsersRepository;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // запускаем контейнер Spring для тестирования
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = {"dev"})
public class UsersIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // для имитации запросов пользователей

    @MockBean
    private UsersRepository usersRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getPartSystemAllUsersTest() throws Exception { // частично системный, т к есть запрос в БД
        this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk());
    }

    @Test
    void getIntegrationAllUsersTest() throws Exception { //интеграц. тест, т к имитируем запрос в БД
        when(usersRepositoryMock.findAll()).thenReturn(List.of(new UsersEntity(
                1L,
                "Вася Пупкин",
                "vasya@i.com",
                "+4912345977",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<OrdersEntity>())));

        this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userID").exists())
                .andExpect(jsonPath("$..name").exists());
    }

    @Test
    void createPartSystemUsersTest() throws Exception {
        UserDto usersDtoInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+4912345977",
                "Password1",
                Role.CLIENT);

        String s = objectMapper.writeValueAsString(usersDtoInput);
        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void createIntegrationUsersTest() throws Exception {
        UserDto usersDtoInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+4912345977",
                "Password1",
                Role.ADMINISTRATOR);

        when(usersRepositoryMock.save(any(UsersEntity.class))).thenReturn(
                new UsersEntity(
                        1L,
                        "Вася Пупкин",
                        "vasya@i.com",
                        "+4912345977",
                        "Password1",
                        Role.CLIENT,
                        new CartEntity(),
                        new HashSet<FavoritesEntity>(),
                        new HashSet<OrdersEntity>()));

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$..userID").exists());
    }

    //070125 hw мое
    @Test
    void updateIntegrationUsersTest() throws Exception {

        UserDto usersDtoInput = new UserDto(
                1L,
                "Вася Пупкин update",
                "vasya_updated@i.com",
                "+11111111111",
                "NewPassword123",
                Role.ADMINISTRATOR);

        when(usersRepositoryMock.save(any(UsersEntity.class))).thenReturn(
                new UsersEntity(
                        1L,
                        "Вася Пупкин",
                        "vasya@i.com",
                        "+4912345977",
                        "Password1",
                        Role.CLIENT,
                        new CartEntity(),
                        new HashSet<FavoritesEntity>(),
                        new HashSet<OrdersEntity>()));

        this.mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$..userID").exists())
                .andExpect(jsonPath("$.userID").value(1L))
                .andExpect(jsonPath("$.name").value("Вася Пупкин update"))
                .andExpect(jsonPath("$.email").value("vasya_updated@i.com"))
                .andExpect(jsonPath("$.phone").value("+11111111111"));
    }

    @Test
    void deleteIntegrationUsersTest() throws Exception {
        Long inputId = 1L;
       // when(usersRepositoryMock.findById(inputId)).thenReturn(Optional.ofNullable(null));

        this.mockMvc.perform(delete("/users/{id}", inputId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}