package de.telran.onlineshop.model;

import de.telran.onlineshop.Role;

import java.util.Objects;

//1) Users - пользователи
public class User {
    private int userID;
    private String name;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private Role role;

    public User() {
    }

    public User(int userID, String name, String email, String phoneNumbmer, String passwordHash, Role role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumbmer;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
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
