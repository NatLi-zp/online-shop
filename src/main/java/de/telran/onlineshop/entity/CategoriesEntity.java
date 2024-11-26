package de.telran.onlineshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CategoriesEntity {

    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерится на уровне БД,само
    private Long categoryId;

    @Column(name = "Name")
    private String name;
}
