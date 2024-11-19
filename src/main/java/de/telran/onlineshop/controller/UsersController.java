package de.telran.onlineshop.controller;

import de.telran.onlineshop.model.Product;
import de.telran.onlineshop.model.User;
import de.telran.onlineshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping  //select
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.valueOf(200));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = usersService.getUserById(id);
        return ResponseEntity.status(222).body(user);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<User> getUserByName(@RequestParam String name) { //user/get?name=Other,k=2
        User user = usersService.getUserByName(name);
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping //Jackson
    public ResponseEntity<Boolean> createUsers(@RequestBody User newUser) { //insert
        boolean user = usersService.createUsers(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateClient(@RequestBody User user) {
        User userResponse = usersService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @DeleteMapping(value = "/{id}") //delete
    public ResponseEntity<Void> deleteUsers(@PathVariable int id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

//    //GET прочитать
//    @GetMapping
//    //select
//    List<User> getAllUsers() {
//        return userList;
//    }
//
//    @GetMapping(value = "/find/{id}")
//    User getUserById(@PathVariable int id) {
//        return userList.stream()
//                .filter(user -> user.getUserID() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    @GetMapping(value = "/get")
//    User getUserByName(@RequestParam String name) { //user/get?name=Other,k=2
//        return userList.stream()
//                .filter(user -> user.getName().equals(name))
//                .findFirst()
//                .orElse(null);
//    }
//
//    //POST вставить
//    @PostMapping //Jackson
//    public boolean createUsers(@RequestBody User newUser) { //insert
//        return userList.add(newUser);
//    }
//
//    //PUT изменить
//    @PutMapping //Jackson
//    public User updateUsers(@RequestBody User updUser) { // update
//        User result = userList.stream()
//                .filter(user -> user.getUserID() == updUser.getUserID())
//                .findFirst()
//                .orElse(null);
//        if (result != null) {
//            result.setName(updUser.getName());
//            result.setEmail(updUser.getEmail());
//            result.setPhoneNumbmer(updUser.getPhoneNumbmer());
//            result.setPasswordHash(updUser.getPasswordHash());
//            result.setRole(updUser.getRole());
//        }
//        return result;
//    }
//
//    //DELETE удалить
//    @DeleteMapping(value = "/{id}") //delete
//    public void deleteUsers(@PathVariable Long id) {
//        Iterator<User> it = userList.iterator();
//        while (it.hasNext()) {
//            User current = it.next();
//            if (current.getUserID() == id) {
//                it.remove();
//            }
//        }
//    }
//
//    @PreDestroy
//    void destroy() {
//        userList.clear();
//        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
//    }
}
