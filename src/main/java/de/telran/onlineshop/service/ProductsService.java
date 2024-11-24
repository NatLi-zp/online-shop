package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.model.Category;
import de.telran.onlineshop.model.Product;
import de.telran.onlineshop.model.User;
import de.telran.onlineshop.repository.ProductsRepository;
import de.telran.onlineshop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  //добавиться конструктор, в который включаться все private final характеристики
public class ProductsService {

    private final ProductsRepository productsRepository;

    private List<Product> productList;

    @PostConstruct
    void init() {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());

        ProductsEntity product1 = new ProductsEntity(1L, "Телефон", "Мобильный телефон Samsumg-A5", 120.20, 3L, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg",110.10, timestamp, timestamp);
        productsRepository.save(product1);
        ProductsEntity product2 = new ProductsEntity(2L, "Конструктор", "Конструктор Lego McLaren P1", 449.99, 4L, "https://www.lego.com/cdn/cs/set/assets/blt519dac201a3dd4c1/42172.png?format=webply&fit=bounds&quality=70&width=800&height=800&dpr=1.5", 350.55, timestamp, timestamp);
        productsRepository.save(product2);

//        productList = new ArrayList<>();
//        productList.add(new Product(1L, "Телефон", "Мобильный телефон Samsumg-A5", new BigDecimal("120.20"), 3, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", new BigDecimal("110.10"), timestamp, timestamp));
//        productList.add(new Product(2L, "Конструктор", "Конструктор Lego McLaren P1", new BigDecimal("449.99"), 4, "https://www.lego.com/cdn/cs/set/assets/blt519dac201a3dd4c1/42172.png?format=webply&fit=bounds&quality=70&width=800&height=800&dpr=1.5", new BigDecimal("350.55"), timestamp, timestamp));

        // System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<Product> getAllProducts() {

       // return productList;
        List<ProductsEntity> productsEntities = productsRepository.findAll();
        return productsEntities.stream()
                .map(entity -> new Product(entity.getProductId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getCategoryId(), entity.getImageUrl(),entity.getDiscountPrice(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public Product getProductById(@PathVariable Long id) { ///products/find/3
        if (id > 7) {
            throw new IllegalArgumentException("Не корректный Id");
        }
        Optional<ProductsEntity> entity = productsRepository.findById(id);

        return new Product(entity.get().getProductId(), entity.get().getName(), entity.get().getDescription(), entity.get().getPrice(), entity.get().getCategoryId(), entity.get().getImageUrl(),entity.get().getDiscountPrice(), entity.get().getCreatedAt(), entity.get().getUpdatedAt());

//        return productList.stream()
//                .filter(product -> product.getProductID() == id)
//                .findFirst()
//                .orElse(null);
    }

    public Product getProductByName(@RequestParam String name) { //user/get?name=Other,k=2
        return productList.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    //POST вставить
    public boolean createProducts(@RequestBody Product newProduct) { //insert

        return productList.add(newProduct);
    }

    //PUT изменить
    public Product updateProducts(@RequestBody Product updProduct) { // update
        Product result = productList.stream()
                .filter(product -> product.getProductID() == updProduct.getProductID())
                .findFirst()
                .orElse(null);
        if (result != null) {
            result.setName(updProduct.getName());
            result.setCategoryID(updProduct.getCategoryID());
            result.setDescription(updProduct.getDescription());
            result.setPrice(updProduct.getPrice());
            result.setDiscountPrice(updProduct.getDiscountPrice());
            result.setImageURL(updProduct.getImageURL());
            result.setCreatedAt(updProduct.getCreatedAt());
            result.setUpdatedAt(updProduct.getUpdatedAt());
        }
        return result;
    }

    //DELETE удалить
    public void deleteProducts(@PathVariable int id) {
        Iterator<Product> it = productList.iterator();
        while (it.hasNext()) {
            Product current = it.next();
            if (current.getProductID() == id) {
                it.remove();
            }
        }
    }

    @PreDestroy
    void destroy() {
        productList.clear();
        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
    }
}
