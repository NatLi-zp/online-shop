package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//Cart - корзина
@Entity
@Table(name = "Cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userId")
    private UsersEntity user;


//    //my
//    @OneToMany(mappedBy = "cart")
//    private Set<CartItemsEntity> cartItems = new HashSet<>();
}


