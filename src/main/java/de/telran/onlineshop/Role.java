package de.telran.onlineshop;

public enum Role {
    CLIENT("Клиент"),
    ADMINISTRATOR("Администратор");

    private String name;

    Role() {
    }

    Role(String name) {
        this.name = name;
    }
}
