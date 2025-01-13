package de.telran.onlineshop.entity;

import de.telran.onlineshop.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank // может быть и на уровне Entity, а не только на Dto
    private String name;

    @Email
    @Column(name = "Email")
    private String email;


    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

    @OneToOne(mappedBy = "user")
    private CartEntity cart;

    @OneToMany(mappedBy = "user")
    private Set<FavoritesEntity> favorites = new HashSet<>();

    //my
//    @OneToMany(mappedBy = "user")
//    private Set<OrdersEntity> orders = new HashSet<>();

//    @ManyToMany
//    @JoinTable(name = "UsersAddresses",
//            joinColumns = @JoinColumn(name = "UserID"),
//            inverseJoinColumns = @JoinColumn(name = "AddressID"))
//    private Set<AddressEntity> addresses = new HashSet<>();

}


