package de.telran.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoritesDto {

    private Long favoriteId;

    @JsonBackReference  // мое 111224
    private UserDto user;

    @JsonBackReference  // мое 111224
    private ProductDto product;

}
