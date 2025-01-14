package de.telran.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoritesDto {
    private Long favoriteId;
    private UserDto user;
    private ProductDto product;
}
//public class FavoritesDto {
//
//    private Long favoriteID;
//
//    //@JsonBackReference  // мое 111224
//    private UserDto user;
//
//    //@JsonBackReference  // мое 111224
//    private ProductDto product;
//
//    public FavoritesDto() {
//    }
//
//    public FavoritesDto(Long favoriteId, UserDto user, ProductDto product) {
//        this.favoriteID = favoriteId;
//        this.user = user;
//        this.product = product;
//    }
//
//    public Long getFavoriteID() {
//        return favoriteID;
//    }
//
//    public void setFavoriteID(Long favoriteID) {
//        this.favoriteID = favoriteID;
//    }
//
//    public UserDto getUser() {
//        return user;
//    }
//
//    public void setUser(UserDto user) {
//        this.user = user;
//    }
//
//    public ProductDto getProduct() {
//        return product;
//    }
//
//    public void setProduct(ProductDto product) {
//        this.product = product;
//    }
//}
