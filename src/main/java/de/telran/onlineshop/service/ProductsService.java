package de.telran.onlineshop.service;

import de.telran.onlineshop.configure.MapperUtil;
import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.dto.ProductDto;
import de.telran.onlineshop.mapper.Mappers;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor  //добавиться конструктор, в который включаться все private final характеристики
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;
    private final Mappers mappers;

    private List<ProductDto> productList;

    //@PostConstruct
    void init() {

        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());

        //Создание продукта и категории
        // CategoriesEntity category1 = new CategoriesEntity(null,"Продукты");
        //category1 = categoriesRepository.save(category1);

        CategoriesEntity category1 = categoriesRepository.findById(3L).orElse(null);

        ProductsEntity product1 = new ProductsEntity(null, "Телефон", "Мобильный телефон Samsumg-A5", 120.20, "https://m.media-amazon.com/images/I/71mjEVa4BjL._AC_SY879_.jpg", 110.10, timestamp, timestamp, category1, null, null, null);
        productsRepository.save(product1);

        CategoriesEntity category2 = categoriesRepository.findById(4L).orElse(null);
        ProductsEntity product2 = new ProductsEntity(null, "Конструктор", "Конструктор Lego McLaren P1", 449.99, "https://www.lego.com/cdn/cs/set/assets/blt519dac201a3dd4c1/42172.png?format=webply&fit=bounds&quality=70&width=800&height=800&dpr=1.5", 350.55, timestamp, timestamp, category2, null, null, null);
        productsRepository.save(product2);

    }

    public List<ProductDto> getAllProducts() {
        List<ProductsEntity> productsEntities = productsRepository.findAll();
        List<ProductDto> productDtoList = MapperUtil.convertList(productsEntities, mappers::convertToProductDto);
        return productDtoList;
    }

    public ProductDto getProductById(Long id) {///products/find/3
        ProductsEntity productsEntity = productsRepository.findById(id).orElse(new ProductsEntity());
        ProductDto productDto = mappers.convertToProductDto(productsEntity);
        return productDto;

    }

    public ProductDto getProductByName(String name) { //user/get?name=Other,k=2
        ProductsEntity productsEntity = productsRepository.findByNameNative(name); // используем native
        ProductDto productDto = mappers.convertToProductDto(productsEntity);
        return productDto;

    }

    //POST вставить
    public boolean createProducts(ProductDto newProduct) {//insert

        //   ProductsEntity createProductEntity = new ProductsEntity(null, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getCategoryID(), newProduct.getImageURL(), newProduct.getDiscountPrice(), newProduct.getCreatedAt(), newProduct.getUpdatedAt());
        ProductsEntity createProductEntity = new ProductsEntity(null, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getImageURL(), newProduct.getDiscountPrice(), newProduct.getCreatedAt(), newProduct.getUpdatedAt(), null, null, null, null);

        ProductsEntity returnProduct = productsRepository.save(createProductEntity);
        return createProductEntity.getProductId() != null;

        // return productList.add(newProduct);
    }

    public ProductDto insertProducts(ProductDto productsDto) {
        ProductsEntity productsEntity = mappers.convertToProductEntity(productsDto);

        productsEntity.setProductId(null);
        ProductsEntity savedProductsEntity = productsRepository.save(productsEntity);

        return mappers.convertToProductDto(savedProductsEntity);
    }

    //PUT изменить
    public ProductDto updateProducts(ProductDto updProduct) { // update
        ProductsEntity productsEntity = mappers.convertToProductEntity(updProduct); // из Dto в Entity
        ProductsEntity returnProductEntity = productsRepository.save(productsEntity); // сохранили в БД
        return mappers.convertToProductDto(returnProductEntity); //из Entity  в Dto
    }

    //DELETE удалить
    public void deleteProductsById(Long id) {
        //     productsRepository.deleteById(id); // 1й вариант реализации метода delete, менее информативно

        ProductsEntity productsEntity = productsRepository.findById(id).orElse(null);
        if (productsEntity != null) {
            productsRepository.delete(productsEntity);
        } else {
            throw new NullPointerException("Not Found ProductsEntity");
        }
    }

    @PreDestroy
    void destroy() {
        //    productList.clear();
        System.out.println("Выполняем логику при окончании работы с объектом" + this.getClass().getName());
    }
}



