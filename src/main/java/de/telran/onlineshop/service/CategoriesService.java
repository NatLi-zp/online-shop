package de.telran.onlineshop.service;

import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.repository.CategoriesRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //добавиться конструктор, в который включаться все private final характеристики
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    private Random random1; // уточнение имени бина при ситуации, когда в контейнере есть неск. бинов типа Random

    @Autowired
    //@Qualifier("taskRandom2")
    @Qualifier("random2")
    private Random otherRandom; //

    //Поиск в контейнере: по типу данных, по имени, по значению в аннотации  Qualifier

    private List<CategoryDto> categoryList;

    @PostConstruct
    void init() {
        CategoriesEntity category1 = new CategoriesEntity(null, "Продукты");
        categoriesRepository.save(category1);
        CategoriesEntity category2 = new CategoriesEntity(null, "Быт.химия");
        categoriesRepository.save(category2);
        CategoriesEntity category3 = new CategoriesEntity(null, "Радиотехника");
        categoriesRepository.save(category3);
        CategoriesEntity category4 = new CategoriesEntity(null, "Игрушки");
        categoriesRepository.save(category4);
        CategoriesEntity category5 = new CategoriesEntity(null, "Одежда");
        categoriesRepository.save(category5);
        CategoriesEntity category6 = new CategoriesEntity(null, "Other");
        category6 = categoriesRepository.save(category6);

        //случайно меняем какую-то категорию
//        Long idUpdate = random1.nextLong(5) + 1;
//        CategoriesEntity updateCategory = new CategoriesEntity(idUpdate, "Другие");
//        categoriesRepository.save(updateCategory);
//
//        category6.setName("Другие");
//        categoriesRepository.save(category6);


        System.out.println("Выполняем логику при создании объекта " + this.getClass().getName());
    }

    // работает с БД
    public List<CategoryDto> getAllCategories() {
        List<CategoriesEntity> categoriesEntities = categoriesRepository.findAll();
        return categoriesEntities.stream()
                .map(entity -> new CategoryDto(entity.getCategoryId(), entity.getName()))
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) { ///categories/find/3
        CategoriesEntity categoriesEntity = categoriesRepository.findById(id).orElse(new CategoriesEntity());

        return new CategoryDto(categoriesEntity.getCategoryId(), categoriesEntity.getName());

//        return categoryList.stream()
//                .filter(category -> category.getCategoryID() == id)
//                .findFirst()
//                .orElse(null);
    }

    public CategoryDto getCategoryByName(String name) { ///categories/get?name=Other,k=2
        //CategoriesEntity categoriesEntity = categoriesRepository.findByName(name); // используем OQL - объектный
        CategoriesEntity categoriesEntity = categoriesRepository.findByNameNative(name); // используем native

        return new CategoryDto(categoriesEntity.getCategoryId(), categoriesEntity.getName());

//        return categoryList.stream()
//                .filter(category -> category.getName().equals(name))
//                .findFirst()
//                .orElse(null);
    }

    public boolean createCategories(CategoryDto newCategory) {//insert
        CategoriesEntity createCategoryEntity = new CategoriesEntity(null, newCategory.getName());
        CategoriesEntity returnCategory = categoriesRepository.save(createCategoryEntity);
        return createCategoryEntity.getCategoryId() != null;
        //   return categoryList.add(newCategory);
    }

    //PUT изменить
    public CategoryDto updateCategories(CategoryDto updCategory) { //update
        CategoriesEntity updateCategoryEntity  = new CategoriesEntity(updCategory.getCategoryID(), updCategory.getName());
        CategoriesEntity returnCategory = categoriesRepository.save(updateCategoryEntity );

        // трансформируем данные из Entity в Dto и возвращаем пользователю
        return new CategoryDto(returnCategory.getCategoryId(), returnCategory.getName());

//        CategoryDto result = categoryList.stream()
//                .filter(category -> category.getCategoryID() == updCategory.getCategoryID())
//                .findFirst()
//                .orElse(null);
//        if (result != null) {
//            result.setName(updCategory.getName());
//        }
//        return result;
    }

    public void deleteCategories(Long id) { //delete
        // categoriesRepository.deleteById(id); // 1й вариант реализации метода delete, менее информативно

        // 2й вариант реализации метода delete c предварит. поиском
        CategoriesEntity categories = categoriesRepository.findById(id).orElse(null);
        if (categories == null) {
            throw new RuntimeException("Нет такого объекта с Id: " + id);
        } else {
            categoriesRepository.delete(categories);
        }

//        Iterator<CategoryDto> it = categoryList.iterator();
//        while (it.hasNext()) {
//            CategoryDto current = it.next();
//            if (current.getCategoryID() == id) {
//                it.remove();
//            }
//        }
    }

    @PreDestroy
    void destroy() {
       // categoryList.clear();
        System.out.println("Выполняем логику при окончании работы с  объектом " + this.getClass().getName());
    }
}