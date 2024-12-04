package de.telran.onlineshop.entity;

//7) Favorites - избранные товары

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteID")
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userId")
    private UsersEntity user;


    @ManyToOne
    @JoinColumn(name = "ProductID", referencedColumnName = "productId")
    private ProductsEntity product;

}
