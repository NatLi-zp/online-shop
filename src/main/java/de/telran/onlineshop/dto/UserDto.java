package de.telran.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.enums.Role;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//1) Users - пользователи

@JsonInclude(JsonInclude.Include.NON_NULL) // если = null ,скрыть в выводе
public class UserDto {
    private Long userID;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL) // если = null ,скрыть в выводе
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private Role role;

    @JsonManagedReference  // мое 111224
    private Set<FavoritesDto> favorites = new HashSet<>();

    @JsonBackReference  // мое 111224
    private CartDto cart;

    public UserDto() {
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

    public UserDto(Long userID, String name, String email, String phoneNumber, String passwordHash, Role role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.role = role;
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

    public String getPhoneNumbmer() {
        return phoneNumber;
    }

    public void setPhoneNumbmer(String phoneNumbmer) {
        this.phoneNumber = phoneNumbmer;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto users = (UserDto) o;
        return userID == users.userID && Objects.equals(name, users.name) && Objects.equals(email, users.email) && Objects.equals(phoneNumber, users.phoneNumber) && Objects.equals(passwordHash, users.passwordHash) && role == users.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, email, phoneNumber, passwordHash, role);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumbmer='" + phoneNumber + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role=" + role +
                '}';
    }
}
