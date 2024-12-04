package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CartEntity;
import de.telran.onlineshop.entity.CartItemsEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.repository.CartItemsRepository;
import de.telran.onlineshop.repository.CartRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemsService {

    private final CartItemsRepository cartItemsRepository;
    private final CartRepository cartRepository;
    private final ProductsRepository productsRepository;

    void init() {
        CartEntity cart1 = cartRepository.findById(1l).orElse(null);
        ProductsEntity product1 = productsRepository.findById(1l).orElse(null);
        CartItemsEntity cartItemsEntity1 = new CartItemsEntity(null, 10, cart1, product1);
        cartItemsRepository.save(cartItemsEntity1);

        CartEntity cart2 = cartRepository.findById(2l).orElse(null);
        ProductsEntity product2 = productsRepository.findById(2l).orElse(null);
        CartItemsEntity cartItemsEntity2 = new CartItemsEntity(null, 15, cart2, product2);
        cartItemsRepository.save(cartItemsEntity2);
    }

    @PreDestroy
    void destroy() {
        //userList.clear();
        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
    }
}
