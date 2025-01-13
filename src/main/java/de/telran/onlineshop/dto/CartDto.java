package de.telran.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

//Cart - корзина
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long cartID;

//    @JsonBackReference  // мое 010125
//    private UserDto user;

//    public CartDto() {
//    }
//
//    public CartDto(Long cartID, UserDto user) {
//        this.cartID = cartID;
//        this.user = user;
//    }
//
//    public Long getCartID() {
//        return cartID;
//    }
//
//    public void setCartID(Long cartID) {
//        this.cartID = cartID;
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
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CartDto cart = (CartDto) o;
//        return Objects.equals(cartID, cart.cartID);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(cartID);
//    }
//
//    @Override
//    public String toString() {
//        return "Cart{" +
//                "cartID=" + cartID +
//                '}';
//    }
}