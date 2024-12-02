package de.telran.onlineshop.entity;

//6) OrderItems - товары в заказе

import de.telran.onlineshop.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OrderItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderItemsEntity {
    @Id
    @Column(name = "OrderItemID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private OrdersEntity order;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductsEntity product;

}
