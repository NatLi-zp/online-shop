package de.telran.onlineshop.service;

import de.telran.onlineshop.Role;
import de.telran.onlineshop.model.Product;
import de.telran.onlineshop.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UsersService {

    private List<User> userList;


    @PostConstruct
    void init() {
        userList = new ArrayList<>();
        userList.add(new User(1L, "Петя Петров", "petrov@gmail.com", "+49123123123", "111", Role.CLIENT));
        userList.add(new User(2L, "Вася Васечкин", "vasiliy@gmail.com", "+4912312777", "222", Role.CLIENT));
        userList.add(new User(3L, "Гусь", "gus@gmail.com", "+491231255555", "777", Role.ADMINISTRATOR));

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public User getUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getUserID() == id)
                .findFirst()
                .orElse(null);
    }

    public User getUserByName(@RequestParam String name) { //user/get?name=Other,k=2
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    //POST вставить
    public boolean createUsers(@RequestBody User newUser) { //insert
        return userList.add(newUser);
    }

    public User updateUser(User user) {
        User result = userList.stream()
                .filter(u -> u.getUserID() == user.getUserID())
                .findFirst()
                .orElse(null);
        if (result != null) {
            result.setName(user.getName());
            result.setEmail(user.getEmail());
            result.setPhoneNumbmer(user.getPhoneNumbmer());
        }
        return result;
    }

    //DELETE удалить
    public void deleteUser(@PathVariable int id) {
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

