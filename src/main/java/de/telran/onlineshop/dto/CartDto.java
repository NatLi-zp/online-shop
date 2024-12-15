package de.telran.onlineshop.dto;

import java.util.Objects;

//Cart - корзина
public class CartDto {
    private Long cartID;
    private UserDto user;

    public CartDto() {
    }

    public CartDto(Long cartID, UserDto user) {
        this.cartID = cartID;
        this.user = user;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDto cart = (CartDto) o;
        return Objects.equals(cartID, cart.cartID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cartID);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                '}';
    }
}