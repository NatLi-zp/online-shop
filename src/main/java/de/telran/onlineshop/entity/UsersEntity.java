package de.telran.onlineshop.entity;

import de.telran.onlineshop.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UsersEntity {

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Column(name = "Role")
    private Role role;

    @OneToOne(mappedBy = "user")
    private CartEntity cart;

    @ManyToMany
    @JoinTable(name = "UsersAddresses",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "AddressID"))
    private Set<AddressEntity> addresses = new HashSet<>();

    public UsersEntity(Long userId, String name, String email, String phoneNumber, String passwordHash, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}


