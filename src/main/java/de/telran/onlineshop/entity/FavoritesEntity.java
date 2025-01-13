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
    @Column(name = "FavoriteID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductsEntity product;

}
