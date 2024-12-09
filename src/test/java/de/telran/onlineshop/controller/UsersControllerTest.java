package de.telran.onlineshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.service.CategoriesService;
import de.telran.onlineshop.service.UsersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockBean
    private UsersService usersServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getAllUsers() throws Exception {
        when(usersServiceMock.getAllUsers()).thenReturn(List.of(new UserDto(1L, "Test",
                "petrov@gmail.com", "+49123123123", "111", Role.CLIENT)));

        this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userID").exists())
                .andExpect(jsonPath("$..userID").value(1))
                .andExpect(jsonPath("$..name").value("Test"))
        ;
    }

    @Test
    void getUserById() throws Exception {
        //Long testId = 1L;
        //when(usersServiceMock.getUserById(testId)).thenReturn(new UserDto(testId,"Test"));
        when(usersServiceMock.getUserById(anyLong())).thenReturn(new UserDto(1L, "Test",
                "petrov@gmail.com", "+49123123123", "111", Role.CLIENT));

        this.mockMvc.perform(get("/users/find/{id}", 1)) ///users/find/1
                .andDo(print()) //печать лога вызова
                //  .andExpect(status().isOk())  //не работает
                .andExpect(jsonPath("$.userID").exists())
                .andExpect(jsonPath("$.userID").value(1))
                .andExpect(jsonPath("$.name").value("Test"));
    }

//    @Test
//    void getUserByName() throws Exception {
//        Long testId = 1L;
//        String testName = "Test";
//        when(usersServiceMock.getUserByName(testName)).thenReturn(new UserDto(testId,"Test",null,null,null,null));
//
//        this.mockMvc.perform(get("/users/get/{name}", 1)) ///users//get/1
//                .andDo(print()) //печать лога вызова
//                //  .andExpect(status().isOk())  //не работает
//                .andExpect(jsonPath("$.userID").exists())
//                .andExpect(jsonPath("$.userID").value(1))
//                .andExpect(jsonPath("$.name").value("Test"));
//    }

    @Test
    void createUsers() throws Exception {
        when(usersServiceMock.createUsers(any(UserDto.class))).thenReturn(true);
        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "userID": null,
                                    "name": "TestName"
                                }
                                """
                        ))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateUsers() throws Exception {
        UserDto inputUser = new UserDto(1L, "NewTestName", "petrov@gmail.com", "+49123123123", "111", Role.CLIENT);
        UserDto expectedUser = inputUser; // редактирование прошло успешно

        when(usersServiceMock.updateUsers(inputUser))
                .thenReturn(new UserDto(inputUser.getUserID(), inputUser.getName(), inputUser.getEmail(), inputUser.getPhoneNumber(), inputUser.getPasswordHash(), inputUser.getRole())); //.thenReturn(expectedCategory)

        this.mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputUser))) // jackson: object -> json
                .andDo(print())
                //.andExpect(status().isAccepted()) // Не работает
                .andExpect(jsonPath("$.userID").exists())
                .andExpect(jsonPath("$.userID").value(expectedUser.getUserID()))
                .andExpect(jsonPath("$.name").value(expectedUser.getName()));
    }

    @Test
    void deleteUsers() throws Exception {

        Long inputId = 1L;

        this.mockMvc.perform(delete("/users/{id}", inputId)) ///categories/1
                .andDo(print());
        //  .andExpect(status().isOk()); // Не работает

        //return void
        verify(usersServiceMock).deleteUsers(inputId);
    }
}