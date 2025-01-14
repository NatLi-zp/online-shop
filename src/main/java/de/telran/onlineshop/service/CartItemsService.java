package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.CartItemsEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.repository.CartItemsRepository;
import de.telran.onlineshop.repository.CartRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemsService {

    private final CartItemsRepository cartItemsRepository;
    private final CartRepository cartRepository;
    private final ProductsRepository productsRepository;

    //@PostConstruct
    void init() {
        CartEntity cart1 = cartRepository.findById(1L).orElse(null);
        System.out.println("--------------------------------------"+cart1);
        ProductsEntity product1 = productsRepository.findById(1L).orElse(null);
        CartItemsEntity cartItems1 = new CartItemsEntity(null, 10, cart1,product1);
        cartItemsRepository.save(cartItems1);

        CartEntity cart2 = cartRepository.findById(2L).orElse(null);
        ProductsEntity product2 = productsRepository.findById(2L).orElse(null);
        CartItemsEntity cartItems2 = new CartItemsEntity(null, 15, cart2, product2);
        cartItemsRepository.save(cartItems2);
    }

    @PreDestroy
    void destroy() {
        //userList.clear();
        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
    }
}
