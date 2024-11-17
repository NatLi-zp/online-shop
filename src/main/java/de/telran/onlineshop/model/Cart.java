package de.telran.onlineshop.model;

import java.util.Objects;

public class Cart {
    private int categoryID;
    private int userID;

    public Cart() {
    }

    public Cart(int categoryID, int userID) {
        this.categoryID = categoryID;
        this.userID = userID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return categoryID == cart.categoryID && userID == cart.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, userID);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "categoryID=" + categoryID +
                ", userID=" + userID +
                '}';
    }
}
