package de.telran.onlineshop.dto;

import java.util.Objects;

//4) CartItems - товары в корзине
public class CartItems {
    private Long cartItemID;
    private Cart cart;
    private ProductDto product;
    private int quantity;

    public CartItems() {
    }

    public CartItems(Long cartItemID, Cart cart, ProductDto product, int quantity) {
        this.cartItemID = cartItemID;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(Long cartItemID) {
        this.cartItemID = cartItemID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItems cartItems = (CartItems) o;
        return Objects.equals(cartItemID, cartItems.cartItemID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cartItemID);
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "cartItemID=" + cartItemID +
                ", cart=" + cart +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
