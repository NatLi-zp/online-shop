package de.telran.onlineshop.controller;

import de.telran.onlineshop.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    private List<Product> productList;

    @PostConstruct
    void init() {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());

        productList = new ArrayList<>();
        productList.add(new Product(1, "Телефон", "Мобильный телефон Samsumg-A5", new BigDecimal("120.20"), 3, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", new BigDecimal("110.10"), timestamp, timestamp));
        productList.add(new Product(2, "Конструктор", "Конструктор Lego McLaren P1", new BigDecimal("449.99"), 4, "https://www.lego.com/cdn/cs/set/assets/blt519dac201a3dd4c1/42172.png?format=webply&fit=bounds&quality=70&width=800&height=800&dpr=1.5", new BigDecimal("350.55"), timestamp, timestamp));

        // System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    //GET прочитать
    @GetMapping
    //select
    List<Product> getAllProducts() {
        return productList;
    }

    @GetMapping(value = "/find/{id}")
    Product getProductById(@PathVariable int id) {
        return productList.stream()
                .filter(product -> product.getProductID() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping(value = "/get")
    Product geProductByName(@RequestParam String name) { //user/get?name=Other,k=2
        return productList.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    //POST вставить
    @PostMapping //Jackson
    public boolean createProducts(@RequestBody Product newProduct) { //insert
        return productList.add(newProduct);
    }

    //PUT изменить
    @PutMapping //Jackson
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
    @DeleteMapping(value = "/{id}") //delete
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
