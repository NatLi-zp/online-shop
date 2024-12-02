package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductsEntity {

    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private Double price;

//    @Column(name = "CategoryID")
//    private Long categoryId;

    @Column(name = "ImageURL")
    private String imageUrl;

    @Column(name = "DiscountPrice")
    private Double discountPrice;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;

    @Column(name = "UpdatedAt")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "CategoryID") // имя колонки для связи с CategoriesEntity
    private CategoriesEntity category;

    @OneToMany(mappedBy = "product")
    private Set<FavoritesEntity> favorites;

    @OneToMany(mappedBy = "product")
    private Set<OrderItemsEntity> orderItem = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<CartItemsEntity> cartItem = new HashSet<>();

    //    @ManyToMany
//    @JoinTable(name = "category_product",
//            joinColumns = @JoinColumn(name = "ProductID"),
//            inverseJoinColumns = @JoinColumn(name = "CategoryID"))
//    private Set<CategoriesEntity> categories =  new HashSet<>();


    public ProductsEntity(Long productId, String name, String description, Double price, String imageUrl, Double discountPrice, Timestamp createdAt, Timestamp updatedAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.discountPrice = discountPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
