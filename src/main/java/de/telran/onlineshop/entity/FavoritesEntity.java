package de.telran.onlineshop.entity;

//7) Favorites - избранные товары

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Favorites")
public class FavoritesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoritesID")
    private long FavoritesId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userId")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "productId")
    private ProductsEntity product;

}
