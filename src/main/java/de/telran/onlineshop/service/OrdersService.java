package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.OrdersEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.entity.enums.Status;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.OrdersRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;

    //@PostConstruct
    void init() {

        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());

        UsersEntity user1 = usersRepository.findById(1L).orElse(null);
        OrdersEntity order1 = new OrdersEntity(null, timestamp, "Hamburg", "+491234567", "до квартиры", Status.CREATED, timestamp, user1, null);
        ordersRepository.save(order1);

        UsersEntity user2 = usersRepository.findById(2L).orElse(null);
        OrdersEntity order2 = new OrdersEntity(null, timestamp, "Berlin", "+491234567444", "до подъезда", Status.PAID, timestamp, user2, null);
        ordersRepository.save(order2);

        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }
}
