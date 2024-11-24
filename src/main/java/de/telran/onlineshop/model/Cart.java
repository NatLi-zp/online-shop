package de.telran.onlineshop.model;

import java.util.Objects;

//Cart - корзина
public class Cart {
    private Long cartID;
    private User user;

    public Cart() {
    }

    public Cart(Long cartID, User user) {
        this.cartID = cartID;
        this.user = user;
    }

    public Long getCartID() {
        return cartID;
    }

    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
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