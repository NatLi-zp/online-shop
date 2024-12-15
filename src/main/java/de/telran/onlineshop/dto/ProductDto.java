package de.telran.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.telran.onlineshop.entity.CategoriesEntity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//2) Products - товары
@JsonInclude(JsonInclude.Include.NON_NULL) //если равно null - скрыть в выводе.Include.NON_NULL) /
public class ProductDto {
    private Long productID;
    private String name;
    private String description;
    private Double price; //BigDecimal
    //private Category category;
    //private Long categoryID;
    private String imageURL;
    private Double discountPrice; //BigDecimal
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private CategoryDto category; //мое 111224

    @JsonManagedReference  // мое 111224
    private Set<FavoritesDto> favorites = new HashSet<>();

     public ProductDto() {
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Set<FavoritesDto> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<FavoritesDto> favorites) {
        this.favorites = favorites;
    }

    // public ProductDto(Long productID, String name, String description, Double price, Long categoryID, String imageURL, Double discountPrice, Timestamp createdAt, Timestamp updatedAt) {
   public ProductDto(Long productID, String name, String description, Double price, String imageURL, Double discountPrice, Timestamp createdAt, Timestamp updatedAt) {

       this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        //this.categoryID = categoryID;
        this.imageURL = imageURL;
        this.discountPrice = discountPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

   // public Long getCategoryID() {
//        return categoryID;
//    }

    //public void setCategoryID(Long categoryID) {
   //     this.categoryID = categoryID;
    //}

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(productID, that.productID) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(imageURL, that.imageURL) && Objects.equals(discountPrice, that.discountPrice) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, description, price, imageURL, discountPrice, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
              //  ", categoryID=" + categoryID +
                ", imageURL='" + imageURL + '\'' +
                ", discountPrice=" + discountPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
