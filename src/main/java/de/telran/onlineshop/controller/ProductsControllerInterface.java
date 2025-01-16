package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.ProductDto;
import de.telran.onlineshop.dto.UserDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.List;

@Tag(name = "Products", description = "Контроллер для работы с товарами",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по товарам",
                url = "https://example.com/docs/products-controller"
        )
)
public interface ProductsControllerInterface {
    @Operation(
            summary = "Список всех товаров",
            description = "Позволяет получить все товары"
    )
    ResponseEntity<List<ProductDto>> getAllProducts();

    @Operation(
            summary = "Поиск по Id",
            description = "Позволяет найти информацию по идентификатору Id товара"
    )
    ResponseEntity<ProductDto> getProductById(
            @Parameter(description = "Идентификатор товара", required = true, example = "1")
            Long id) throws FileNotFoundException;

    @Operation(
            summary = "Поиск по name",
            description = "Позволяет найти информацию по наименованию name товара"
    )
    ResponseEntity<ProductDto> getProductByName(@RequestParam String name);

    @Operation(
            summary = "Добавление нового товара",
            description = "Позволяет добавить новый товар"
    )
    ResponseEntity<Boolean> createProducts(@RequestBody @Valid ProductDto newProduct);

    @Operation(
            summary = "Изменение существующего товара",
            description = "Позволяет изменить товар"
    )
    ResponseEntity<ProductDto> updateProducts(@RequestBody @Valid ProductDto updProduct);

    @Hidden
        //скрытие данного endpoint из openApi
    ResponseEntity<Void> deleteProducts(@PathVariable Long id);
}