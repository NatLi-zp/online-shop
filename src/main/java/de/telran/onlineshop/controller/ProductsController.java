package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.ProductDto;
import de.telran.onlineshop.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    //@Autowired - иньекция через value (не рекомендуемая из-за Reflection)
    private ProductsService productsService;

    //@Autowired - инъекция через конструктор (рекомендуемая !!!), авто аннотирование с версии 3.0
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    // @Autowired - иньекция через сеттер (обязательно использовать аннотацию)
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    //@GetMapping
    //    public List<Product> getAllProducts() {
    //        return productsService.getAllProducts();
    //    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.valueOf(200));
    }

    //    @GetMapping(value = "/find/{id}")
    //    public Product getProductById(@PathVariable Long id) { ///categories/find/3
    //        return productsService.getProductById(id);
    //    }
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) { ///categories/find/3
        ProductDto product = productsService.getProductById(id);
        return ResponseEntity.status(200).body(product);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ProductDto> getProductByName(@RequestParam String name) { //user/get?name=Other,k=2
        ProductDto product = productsService.getProductByName(name);
        return ResponseEntity.status(200).body(product);
    }

    //POST вставить
//    @PostMapping //Jackson
//    public boolean createProducts(@RequestBody Product newProduct) { //insert
//        return productsService.createProducts(newProduct);
//    }
    @PostMapping //Jackson
    public ResponseEntity<Boolean> createProducts(@RequestBody ProductDto newProduct) { //insert
        boolean product = productsService.createProducts(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    //PUT изменить
    @PutMapping //Jackson
    public ResponseEntity<ProductDto> updateProducts(@RequestBody ProductDto updProduct) { // update
        ProductDto product = productsService.updateProducts(updProduct);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    //DELETE удалить
//    @DeleteMapping(value = "/{id}") //delete
//    public void deleteProducts(@PathVariable int id) {
//        productsService.deleteProducts(id);
//    }
    @DeleteMapping(value = "/{id}") //delete
    public ResponseEntity<Void> deleteProducts(@PathVariable Long id) {
        productsService.deleteProducts(id);
        return ResponseEntity.noContent().build();
    }

}
