package de.telran.onlineshop.service;

import de.telran.onlineshop.configure.MapperUtil;
import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.mapper.Mappers;
import de.telran.onlineshop.repository.CartRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;
    private final Mappers mappers;

    private List<UserDto> userList;


    //@PostConstruct
    void init() {
////        userList = new ArrayList<>();
////        userList.add(new User(1L, "Петя Петров", "petrov@gmail.com", "+49123123123", "111", Role.CLIENT));
////        userList.add(new User(2L, "Вася Васечкин", "vasiliy@gmail.com", "+4912312777", "222", Role.CLIENT));
////        userList.add(new User(3L, "Гусь", "gus@gmail.com", "+491231255555", "777", Role.ADMINISTRATOR));


//        CartEntity cart1 = new CartEntity();
//        cart1 = cartRepository.save(cart1);
//        UsersEntity user1 = new UsersEntity(null, "Петя Петров1111", "petrov@gmail.com", "+49123123123", "111", Role.CLIENT, cart1, null, null);
//        usersRepository.save(user1);
//
//        CartEntity cart2 = new CartEntity();
//        cart2 = cartRepository.save(cart2);
//        UsersEntity user2 = new UsersEntity(null, "Вася Васечкин", "vasiliy@gmail.com", "+4912312777", "222", Role.CLIENT, cart2, null, null, null);
//        usersRepository.save(user2);
//
//        CartEntity cart3 = new CartEntity();
//        cart3 = cartRepository.save(cart3);
//        UsersEntity user3 = new UsersEntity(null, "Гусь", "gus@gmail.com", "+491231255555", "777", Role.ADMINISTRATOR, cart3, null, null, null);
//        usersRepository.save(user3);
//
//        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<UserDto> getAllUsers() {
        List<UsersEntity> usersEntities = usersRepository.findAll();
        List<UserDto> userDtoList = MapperUtil.convertList(usersEntities, mappers::convertToUserDto);
        return userDtoList;

//        List<UsersEntity> usersEntities = usersRepository.findAll();
//        return usersEntities.stream()
//              //  .map(entity -> new UserDto(entity.getUserId(), entity.getName(), entity.getEmail(), entity.getPhoneNumber(), entity.getPasswordHash(), entity.getRole()))
//                .map(entity -> new UserDto(entity.getUserId(), entity.getName(), entity.getEmail(), entity.getPhoneNumber(), entity.getPasswordHash()))
//                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) throws FileNotFoundException {
        if(id<0) {
            throw new FileNotFoundException(id+" - не найдено!");
        }
        UsersEntity usersEntity = usersRepository.findById(id).orElse(new UsersEntity());
        UserDto userDto = mappers.convertToUserDto(usersEntity);
        return userDto;
    }
//    public UserDto getUserById(Long id) {
//
//        UsersEntity usersEntity = usersRepository.findById(id).orElse(new UsersEntity());
//        UserDto userDto = mappers.convertToUserDto(usersEntity);
//
//        return userDto;

//        return new UserDto(usersEntity.getUserId(), usersEntity.getName(), usersEntity.getEmail(),
//                usersEntity.getPhoneNumber(), usersEntity.getPasswordHash(), usersEntity.getRole());

        //        return userList.stream()
        //         .filter(user -> user.getUserID() == id)
//                .findFirst()
//                .orElse(null);
   // }

    public UserDto getUserByName(String name) { //user/get?name=Other,k=2
        UsersEntity usersEntity = usersRepository.findByNameNative(name); // используем native

        //Homework Mapper 151224
        UserDto userDto = mappers.convertToUserDto(usersEntity);
        return userDto;

//        return new UserDto(usersEntity.getUserId(), usersEntity.getName(), usersEntity.getEmail(),
//                usersEntity.getPhoneNumber(), usersEntity.getPasswordHash(), usersEntity.getRole());

//        return userList.stream()
//                .filter(user -> user.getName().equals(name))
//                .findFirst()
//                .orElse(null);
    }

    //POST вставить
//    public boolean createUsers(UserDto newUser) {//insert
//
//        UsersEntity createUserEntity = new UsersEntity(null, newUser.getName(), newUser.getEmail(), newUser.getPhoneNumber(), newUser.getPasswordHash(), newUser.getRole(), null, null, null);
//
//        UsersEntity returnUser = usersRepository.save(createUserEntity);
//        return createUserEntity.getUserId() != null;
//        //return userList.add(newUser);
//    }


    public UserDto insertUsers(UserDto usersDto) {//insert

        UsersEntity usersEntity = mappers.convertToUserEntity(usersDto);

        usersEntity.setUserId(null);
        UsersEntity savedUsersEntity = usersRepository.save(usersEntity);

        return mappers.convertToUserDto(savedUsersEntity);
    }

    public UserDto updateUsers(UserDto user) {
        UsersEntity usersEntity = mappers.convertToUserEntity(user); // из Dto в Entity
        UsersEntity returnUserEntity = usersRepository.save(usersEntity); // сохранили в БД
        return mappers.convertToUserDto(returnUserEntity); //из Entity  в Dto

//        UsersEntity createUserEntity = new UsersEntity(user.getUserID(), user.getName(), user.getEmail(),
//                user.getPhoneNumber(), user.getPasswordHash(), user.getRole(), null, null, null);
//        UsersEntity returnUser = usersRepository.save(createUserEntity);
//
//        // трансформируем данные из Entity в Dto и возвращаем пользователю
//        return new UserDto(returnUser.getUserId(), returnUser.getName(), returnUser.getEmail(),
//                returnUser.getPhoneNumber(), returnUser.getPasswordHash(), returnUser.getRole());

//        UserDto result = userList.stream()
//                .filter(u -> u.getUserID() == user.getUserID())
//                .findFirst()
//                .orElse(null);
//        if (result != null) {
//            result.setName(user.getName());
//            result.setEmail(user.getEmail());
//            result.setPhoneNumbmer(user.getPhoneNumbmer());
//        }
//        return result;
    }

    //DELETE удалить
    public void deleteUsersById(Long id) {
        usersRepository.deleteById(id); // 1й вариант реализации метода delete, менее информативно
//
//        // 2й вариант реализации метода delete c предварит. поиском
//        UsersEntity usersEntity = usersRepository.findById(id).orElse(null);
//        if (usersEntity != null) {
//            usersRepository.delete(usersEntity);
//        } else {
//            throw new NullPointerException("Not Found UsersEntity");
//        }

    }

    @PreDestroy
    void destroy() {
        //   userList.clear();
        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
    }
}

