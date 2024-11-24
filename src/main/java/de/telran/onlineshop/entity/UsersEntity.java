package de.telran.onlineshop.entity;

import de.telran.onlineshop.Role;
import jakarta.persistence.*;
import lombok.*;

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
}
