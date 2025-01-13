package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.OrdersEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.entity.enums.Status;
import de.telran.onlineshop.repository.CartRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CartsService {
    private final CartRepository cartRepository;
    private final UsersRepository usersRepository;

    //@PostConstruct
    void init() {

//        UsersEntity user1 = usersRepository.findById(1L).orElse(null);
//        CartEntity cart1 = new CartEntity(null, user1,null);
//        cartRepository.save(cart1);
//
//        UsersEntity user2 = usersRepository.findById(2L).orElse(null);
//        CartEntity cart2 = new CartEntity(null, user2,null);
//        cartRepository.save(cart2);

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }
}

