package de.telran.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.enums.Role;
import de.telran.onlineshop.entity.CartEntity;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//1) Users - пользователи

@JsonInclude(JsonInclude.Include.NON_NULL) //если равно null - скрыть в выводе
public class UserDto {
    private Long userID;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL) //если равно null - скрыть в выводе
    private String email;
    private String phoneNumber;
    private String passwordHash;

    private Set<FavoritesDto> favorites = new HashSet<>();

    private CartDto cart;

    public UserDto(Long userID, String name, String email, String phoneNumber, String passwordHash) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
    }

    public UserDto() {
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

    public Set<FavoritesDto> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<FavoritesDto> favorites) {
        this.favorites = favorites;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto user = (UserDto) o;
        return Objects.equals(userID, user.userID) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(userID);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(phoneNumber);
        result = 31 * result + Objects.hashCode(passwordHash);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}