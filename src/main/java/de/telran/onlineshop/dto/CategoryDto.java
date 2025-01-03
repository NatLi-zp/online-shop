package de.telran.onlineshop.dto;

//Category - категории товаров
import java.util.Objects;

public class CategoryDto {
    private long categoryID;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(long categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDto category = (CategoryDto) o;
        return categoryID == category.categoryID && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", name='" + name + '\'' +
                '}';
    }
}
