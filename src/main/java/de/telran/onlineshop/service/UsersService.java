package de.telran.onlineshop.service;

import de.telran.onlineshop.Role;
import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.model.Category;
import de.telran.onlineshop.model.User;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    private List<User> userList;


    @PostConstruct
    void init() {
//        userList = new ArrayList<>();
//        userList.add(new User(1L, "Петя Петров", "petrov@gmail.com", "+49123123123", "111", Role.CLIENT));
//        userList.add(new User(2L, "Вася Васечкин", "vasiliy@gmail.com", "+4912312777", "222", Role.CLIENT));
//        userList.add(new User(3L, "Гусь", "gus@gmail.com", "+491231255555", "777", Role.ADMINISTRATOR));

        UsersEntity user1 = new UsersEntity(1L, "Петя Петров", "petrov@gmail.com", "+49123123123", "111", Role.CLIENT);
        usersRepository.save(user1);
        UsersEntity user2 = new UsersEntity(2L, "Вася Васечкин", "vasiliy@gmail.com", "+4912312777", "222", Role.CLIENT);
        usersRepository.save(user2);
        UsersEntity user3 = new UsersEntity(3L, "Гусь", "gus@gmail.com", "+491231255555", "777", Role.ADMINISTRATOR);
        usersRepository.save(user3);

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<User> getAllUsers() {

        //return userList;
        List<UsersEntity> usersEntities = usersRepository.findAll();
        return usersEntities.stream()
                .map(entity -> new User(entity.getUserId(), entity.getName(), entity.getEmail(), entity.getPhoneNumber(), entity.getPasswordHash(), entity.getRole()))
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        Optional<UsersEntity> entity = usersRepository.findById(id);

        return new User(entity.get().getUserId(), entity.get().getName(), entity.get().getEmail(), entity.get().getPhoneNumber(), entity.get().getPasswordHash(), entity.get().getRole());

        //        return userList.stream()
       //         .filter(user -> user.getUserID() == id)
//                .findFirst()
//                .orElse(null);
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

