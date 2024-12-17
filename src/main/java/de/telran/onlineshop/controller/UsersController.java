package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.UserDto;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.valueOf(200));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = usersService.getUserById(id);
        return ResponseEntity.status(200).body(user); // поставила,чтоб тест проходил
        // return ResponseEntity.status(222).body(user);

//    public UserDto getUserById(@PathVariable Long id) { ///categories/find/3
//        return usersService.getUserById(id);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<UserDto> getUserByName(@RequestParam String name) { //user/get?name=Other,k=2
        UserDto user = usersService.getUserByName(name);
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping //Jackson
    public ResponseEntity<Boolean> createUsers(@RequestBody UserDto newUser) { //insert
        boolean user = usersService.createUsers(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUsers(@RequestBody UserDto user) {
        UserDto userResponse = usersService.updateUsers(user);
        //  return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse); // поставила,чтоб тест проходил
    }

    @DeleteMapping(value = "/{id}") //delete
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        usersService.deleteUsersById(id);
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
