package de.telran.onlineshop.entity;

//8) Categories - категории товаров.
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    @NotBlank // может быть и на уровне Entity, а не только на Dto
    private String name;

    @OneToMany(mappedBy = "category") //, cascade = CascadeType.ALL)
    private Set<ProductsEntity> products = new HashSet<>();

    public CategoriesEntity(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    //    @ManyToMany
//    @JoinTable(name = "category_product",
//            joinColumns = @JoinColumn(name = "CategoryID"),
//            inverseJoinColumns = @JoinColumn(name = "ProductID"))
//    private Set<ProductsEntity> products =  new HashSet<>();

}
