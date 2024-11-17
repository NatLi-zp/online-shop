package de.telran.onlineshop.controller;

import de.telran.onlineshop.Role;
import de.telran.onlineshop.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    private List<User> userList;

    @PostConstruct
    void init() {
        userList = new ArrayList<>();
        userList.add(new User(1, "Петя Петров", "petrov@gmail.com", "+49123123123", "111", Role.CLIENT));
        userList.add(new User(2, "Вася Васечкин", "vasiliy@gmail.com", "+4912312777", "222", Role.CLIENT));
        userList.add(new User(3, "Гусь", "gus@gmail.com", "+491231255555", "777", Role.ADMINISTRATOR));

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    //GET прочитать
    @GetMapping
    //select
    List<User> getAllUsers() {
        return userList;
    }

    @GetMapping(value = "/find/{id}")
    User getUserById(@PathVariable int id) {
        return userList.stream()
                .filter(user -> user.getUserID() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping(value = "/get")
    User getUserByName(@RequestParam String name) { //user/get?name=Other,k=2
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    //POST вставить
    @PostMapping //Jackson
    public boolean createUsers(@RequestBody User newUser) { //insert
        return userList.add(newUser);
    }

    //PUT изменить
    @PutMapping //Jackson
    public User updateUsers(@RequestBody User updUser) { // update
        User result = userList.stream()
                .filter(user -> user.getUserID() == updUser.getUserID())
                .findFirst()
                .orElse(null);
        if (result != null) {
            result.setName(updUser.getName());
            result.setEmail(updUser.getEmail());
            result.setPhoneNumbmer(updUser.getPhoneNumbmer());
            result.setPasswordHash(updUser.getPasswordHash());
            result.setRole(updUser.getRole());
        }
        return result;
    }

    //DELETE удалить
    @DeleteMapping(value = "/{id}") //delete
    public void deleteUsers(@PathVariable Long id) {
        Iterator<User> it = userList.iterator();
        while (it.hasNext()) {
            User current = it.next();
            if (current.getUserID() == id) {
                it.remove();
            }
        }
    }

    @PreDestroy
    void destroy() {
        userList.clear();
        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
    }
}
