package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.service.CategoriesService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
//@Tag(name = "Categories", description = "Контроллер для работы с категориями товаров",
//        externalDocs = @ExternalDocumentation(
//                description = "Ссылка на общую документацию по категориям",
//                url = "https://example.com/docs/categories-controller"
//        )
//)
@Slf4j
public class CategoriesController implements CategoriesControllerInterface {

    //@Autowired - иньекция через value (не рекомендуемая из-за Reflection)
    private CategoriesService categoryService;

    //@Autowired - иньекция через конструктор (рекомендуемая !!!), авто аннотирование с версии 3.0
    public CategoriesController(CategoriesService categoryService) {
        this.categoryService = categoryService;
    }

    // @Autowired - иньекция через сеттер (обязательно использовать аннотацию)
    public void setCategoryService(CategoriesService categoryService) {
        this.categoryService = categoryService;
    }

//    @Operation(
//            summary = "Все категории",
//            description = "Позволяет получить все категории товаров"
//    )
    @GetMapping  //select
    public List<CategoryDto> getAllCategories() {

        long start = System.currentTimeMillis();
        List<CategoryDto> result = categoryService.getAllCategories();
        log.info("getAllCategories - " + (System.currentTimeMillis() - start));
        return categoryService.getAllCategories();

    }

//    @Operation(
//            summary = "Поиск по  id",
//            description = "Позволяет найти информ. по идентиф.id категории товаров"
//    )
    @GetMapping(value = "/find/{id}")
    public CategoryDto getCategoryById(
            @Parameter(description = "Идентификатор категории", required = true, example = "1")
            @Min(value = 0, message = "Id не может быть отрицат.")
            @PathVariable Long id) throws FileNotFoundException{ ///categories/find/3
        return categoryService.getCategoryById(id);
    }

    // Экранирование кириллицы для url - https://planetcalc.ru/683/
    @GetMapping(value = "/get")
    public CategoryDto getCategoryByName(@RequestParam String name) { ///categories/get?name=Other,k=2
        return categoryService.getCategoryByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping //Jackson
    public boolean createCategories(@RequestBody @Valid CategoryDto newCategory) { //insert
        return categoryService.createCategories(newCategory);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping
    public CategoryDto updateCategories(@RequestBody CategoryDto updCategory) { //update
        return categoryService.updateCategories(updCategory);
    }

    //@Hidden // скрытие данного endpoint из openApi
    @DeleteMapping(value = "/{id}")
    public void deleteCategories(@PathVariable Long id) { //delete
        categoryService.deleteCategories(id);
    }

    // альтернативная обработка ошибочной ситуации Exception
    @ExceptionHandler({IllegalArgumentException.class, FileNotFoundException.class})
    public ResponseEntity handleTwoException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) //.BAD_REQUEST)
                .body(exception.getMessage());
    }

    // альтернативная обработка ошибочной ситуации Exception
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleException(Exception exception) {
//        return ResponseEntity
//                .status(HttpStatus.I_AM_A_TEAPOT)
//                .body("Извините, что-то пошло не так. Попробуйте позже!" + exception.getMessage());
//    }

}