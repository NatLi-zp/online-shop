package de.telran.onlineshop.entity;

//5) Orders - заказы

import de.telran.onlineshop.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrdersEntity {

    @Id
    @Column(name = "OrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "DeliveryAddress")
    private String deliveryAddress;

    @Column(name = "ContactPhone")
    private String contactPhone;

    @Column(name = "DeliveryMethod")
    private String deliveryMethod;

    @Column(name = "Status")
    private Status status;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

//    @ManyToOne
//    @JoinColumn(name = "UserID")
//    private UsersEntity user;

    @OneToMany(mappedBy = "order")
    private Set<OrderItemsEntity> orderItems;

}
