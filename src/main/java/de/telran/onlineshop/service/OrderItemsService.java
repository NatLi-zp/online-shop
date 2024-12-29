package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.*;
import de.telran.onlineshop.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;
    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;

    //@PostConstruct
    void init() {
        OrdersEntity order1 = ordersRepository.findById(1L).orElse(null);
        ProductsEntity product1 = productsRepository.findById(2L).orElse(null);
        OrderItemsEntity orderItems1 = new OrderItemsEntity(null, order1, product1);
        orderItemsRepository.save(orderItems1);

        OrdersEntity order2 = ordersRepository.findById(2L).orElse(null);
        ProductsEntity product2 = productsRepository.findById(1L).orElse(null);
        OrderItemsEntity orderItems2 = new OrderItemsEntity(null, order2, product2);
        orderItemsRepository.save(orderItems2);
    }

}
