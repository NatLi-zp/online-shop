package de.telran.onlineshop.service;

import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.dto.ProductDto;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.repository.ProductsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor  //добавиться конструктор, в который включаться все private final характеристики
public class ProductsService {

    private final ProductsRepository productsRepository;

    private List<ProductDto> productList;

    @PostConstruct
    void init() {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());

        ProductsEntity product1 = new ProductsEntity(1L, "Телефон", "Мобильный телефон Samsumg-A5", 120.20, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", 110.10, timestamp, timestamp);
        productsRepository.save(product1);
        ProductsEntity product2 = new ProductsEntity(2L, "Конструктор", "Конструктор Lego McLaren P1", 449.99, "https://www.lego.com/cdn/cs/set/assets/blt519dac201a3dd4c1/42172.png?format=webply&fit=bounds&quality=70&width=800&height=800&dpr=1.5", 350.55, timestamp, timestamp);
        productsRepository.save(product2);

//        productList = new ArrayList<>();
//        productList.add(new Product(1L, "Телефон", "Мобильный телефон Samsumg-A5", new BigDecimal("120.20"), 3, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", new BigDecimal("110.10"), timestamp, timestamp));
//        productList.add(new Product(2L, "Конструктор", "Конструктор Lego McLaren P1", new BigDecimal("449.99"), 4, "https://www.lego.com/cdn/cs/set/assets/blt519dac201a3dd4c1/42172.png?format=webply&fit=bounds&quality=70&width=800&height=800&dpr=1.5", new BigDecimal("350.55"), timestamp, timestamp));

        // System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    public List<ProductDto> getAllProducts() {

        // return productList;
        List<ProductsEntity> productsEntities = productsRepository.findAll();
        return productsEntities.stream()
                .map(entity -> new ProductDto(entity.getProductId(), entity.getName(), entity.getDescription(),
               //         entity.getPrice(), entity.getCategoryId(), entity.getImageUrl(), entity.getDiscountPrice(), entity.getCreatedAt(), entity.getUpdatedAt()))
                        entity.getPrice(), entity.getImageUrl(), entity.getDiscountPrice(), entity.getCreatedAt(), entity.getUpdatedAt()))

                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {///products/find/3

        ProductsEntity productsEntity = productsRepository.findById(id).orElse(new ProductsEntity());

        return new ProductDto(productsEntity.getProductId(), productsEntity.getName(), productsEntity.getDescription(), productsEntity.getPrice(),
          //      productsEntity.getCategoryId(), productsEntity.getImageUrl(), productsEntity.getDiscountPrice(), productsEntity.getCreatedAt(), productsEntity.getUpdatedAt());
                 productsEntity.getImageUrl(), productsEntity.getDiscountPrice(), productsEntity.getCreatedAt(), productsEntity.getUpdatedAt());


        //        return productList.stream()
//                .filter(product -> product.getProductID() == id)
//                .findFirst()
//                .orElse(null);
    }

    public ProductDto getProductByName(String name) { //user/get?name=Other,k=2
        ProductsEntity productsEntity = productsRepository.findByNameNative(name); // используем native

        return new ProductDto(productsEntity.getProductId(), productsEntity.getName(), productsEntity.getDescription(), productsEntity.getPrice(),
          //      productsEntity.getCategoryId(), productsEntity.getImageUrl(), productsEntity.getDiscountPrice(), productsEntity.getCreatedAt(), productsEntity.getUpdatedAt());
                 productsEntity.getImageUrl(), productsEntity.getDiscountPrice(), productsEntity.getCreatedAt(), productsEntity.getUpdatedAt());


//        return productList.stream()
//                .filter(product -> product.getName().equals(name))
//                .findFirst()
//                .orElse(null);
    }

    //POST вставить
    public boolean createProducts(ProductDto newProduct) { //insert
     //   ProductsEntity createProductEntity = new ProductsEntity(null, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getCategoryID(), newProduct.getImageURL(), newProduct.getDiscountPrice(), newProduct.getCreatedAt(), newProduct.getUpdatedAt());
        ProductsEntity createProductEntity = new ProductsEntity(null, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getImageURL(), newProduct.getDiscountPrice(), newProduct.getCreatedAt(), newProduct.getUpdatedAt());

        ProductsEntity returnProduct = productsRepository.save(createProductEntity);
        return createProductEntity.getProductId() != null;

        // return productList.add(newProduct);
    }

    //PUT изменить
    public ProductDto updateProducts(ProductDto updProduct) { // update

       // ProductsEntity createProductEntity = new ProductsEntity(updProduct.getProductID(), updProduct.getName(), updProduct.getDescription(), updProduct.getPrice(), updProduct.getCategoryID(), updProduct.getImageURL(), updProduct.getDiscountPrice(), updProduct.getCreatedAt(), updProduct.getUpdatedAt());
        ProductsEntity createProductEntity = new ProductsEntity(updProduct.getProductID(), updProduct.getName(), updProduct.getDescription(), updProduct.getPrice(), updProduct.getImageURL(), updProduct.getDiscountPrice(), updProduct.getCreatedAt(), updProduct.getUpdatedAt());

        ProductsEntity returnProduct = productsRepository.save(createProductEntity);

        // трансформируем данные из Entity в Dto и возвращаем пользователю
      //  return new ProductDto(returnProduct.getProductId(), returnProduct.getName(), returnProduct.getDescription(), returnProduct.getPrice(), returnProduct.getCategoryId(), returnProduct.getImageUrl(), returnProduct.getDiscountPrice(), returnProduct.getCreatedAt(), returnProduct.getUpdatedAt());
        return new ProductDto(returnProduct.getProductId(), returnProduct.getName(), returnProduct.getDescription(), returnProduct.getPrice(), returnProduct.getImageUrl(), returnProduct.getDiscountPrice(), returnProduct.getCreatedAt(), returnProduct.getUpdatedAt());

//        ProductDto result = productList.stream()
//                .filter(product -> product.getProductID() == updProduct.getProductID())
//                .findFirst()
//                .orElse(null);
//        if (result != null) {
//            result.setName(updProduct.getName());
//            result.setCategoryID(updProduct.getCategoryID());
//            result.setDescription(updProduct.getDescription());
//            result.setPrice(updProduct.getPrice());
//            result.setDiscountPrice(updProduct.getDiscountPrice());
//            result.setImageURL(updProduct.getImageURL());
//            result.setCreatedAt(updProduct.getCreatedAt());
//            result.setUpdatedAt(updProduct.getUpdatedAt());
//        }
//        return result;
    }

    //DELETE удалить
    public void deleteProducts(Long id) {
        // productsRepository.deleteById(id); // 1й вариант реализации метода delete, менее информативно

        // 2й вариант реализации метода delete c предварит. поиском
        ProductsEntity products = productsRepository.findById(id).orElse(null);
        if (products == null) {
            throw new RuntimeException("Нет такого объекта с Id: " + id);
        } else {
            productsRepository.delete(products);
        }
//        Iterator<ProductDto> it = productList.iterator();
//        while (it.hasNext()) {
//            ProductDto current = it.next();
//            if (current.getProductID() == id) {
//                it.remove();
//            }
//        }
    }

    @PreDestroy
    void destroy() {
        productList.clear();
        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
    }
}
