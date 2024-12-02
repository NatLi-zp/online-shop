package de.telran.onlineshop.entity;

//4) CartItems - товары в корзине

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CartItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CartItemsEntity {

    @Id
    @Column(name = "CartItemsID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "CartID")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductsEntity product;

    @Column(name = "Quantity")
    private int quantity;

}
