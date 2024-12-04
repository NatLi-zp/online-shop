package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

//Cart - корзина
@Data
@Entity
@Table(name = "Cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private long cartId;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userId")
    private UsersEntity user;

    //my
    @OneToMany(mappedBy = "cart")
    private Set<CartItemsEntity> cartItems = new HashSet<>();
}


