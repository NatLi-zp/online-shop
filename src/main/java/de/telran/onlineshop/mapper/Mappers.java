package de.telran.onlineshop.mapper;

import de.telran.onlineshop.configure.MapperUtil;
import de.telran.onlineshop.dto.*;
import de.telran.onlineshop.entity.*;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Mappers {

    //@Autowired
    private final ModelMapper modelMapper;

    public UserDto convertToUserDto(UsersEntity usersEntity) {

        modelMapper.typeMap(UsersEntity.class, UserDto.class)
                .addMappings(mapper -> mapper.skip(UserDto::setEmail)); // исключаем этот метод из работы


        UserDto userDto = modelMapper.map(usersEntity, UserDto.class); //автомат
        if (userDto.getPasswordHash() != null)
            userDto.setPasswordHash("***"); // замещаем данных

        // преобразовываем
        if (usersEntity.getFavorites() != null) {
            Set<FavoritesDto> favoritesDtoSet = MapperUtil.convertSet(usersEntity.getFavorites(), this::convertToFavoritesDto);
            userDto.setFavorites(favoritesDtoSet);
        }
//
//        CartDto cartDto = convertToCartDto(usersEntity.getCart()); // второй связанный объект
//        userDto.setCart(cartDto);

        return userDto;

    }


    public CartDto convertToCartDto(CartEntity cartEntity) {
        CartDto cartDto = null;
        if (cartEntity != null)
            cartDto = modelMapper.map(cartEntity, CartDto.class); //автомат
        //artDto.();
        return cartDto;
    }

    public FavoritesDto convertToFavoritesDto(FavoritesEntity favoritesEntity) {
        FavoritesDto favoritesDto = modelMapper.map(favoritesEntity, FavoritesDto.class); //автомат
        favoritesDto.setUser(null);
        return favoritesDto;
    }

    public UsersEntity convertToUserEntity(UserDto userDto) {
        UsersEntity usersEntity = modelMapper.map(userDto, UsersEntity.class); //автомат
        return usersEntity;
    }


    //Homework 111224
    public ProductDto convertToProductDto(ProductsEntity productsEntity) {
        modelMapper.typeMap(ProductsEntity.class, ProductDto.class)
                .addMappings(mapper -> mapper.skip(ProductDto::setDiscountPrice)); // исключаем этот метод из работы
        ProductDto productDto = modelMapper.map(productsEntity, ProductDto.class); //автомат
        if (productDto.getCreatedAt() != null)
            productDto.setCreatedAt(null); // замещаем данных

        // преобразовываем
        if (productsEntity.getFavorites() != null) {
            Set<FavoritesDto> favoritesDtoSet = MapperUtil.convertSet(productsEntity.getFavorites(), this::convertToFavoritesDto);
            //productDto.setFavorites(favoritesDtoSet);
        }

        CategoryDto categoryDto = convertToCategoryDto(productsEntity.getCategory()); // второй связанный объект
        productDto.setCategory(categoryDto);
        return productDto;
    }

    public CategoryDto convertToCategoryDto(CategoriesEntity categoriesEntity) {
        CategoryDto categoryDto = null;
        if (categoriesEntity != null)
            categoryDto = modelMapper.map(categoriesEntity, CategoryDto.class); //автомат
        return categoryDto;
    }

    public ProductsEntity convertToProductEntity(ProductDto productDto) {
        ProductsEntity productsEntity = modelMapper.map(productDto, ProductsEntity.class); //автомат
        return productsEntity;
    }

    //Homework 261224
//    public FavoritesDto convertToFavoriteDto(FavoritesEntity favoritesEntity) {
//        modelMapper.typeMap(FavoritesEntity.class, FavoritesDto.class);
//        FavoritesDto favoritesDto = modelMapper.map(favoritesEntity, FavoritesDto.class); //автомат

//        // преобразовываем
//        if (favoritesEntity.getFavorites() != null) {
//            Set<FavoritesDto> favoritesDtoSet = MapperUtil.convertSet(usersEntity.getFavorites(), this::convertToFavoritesDto);
//            userDto.setFavorites(favoritesDtoSet);
//        }

//        UserDto userDto = convertToUserDto(favoritesEntity.getUser()); // первый связанный объект
//        favoritesDto.setUser(userDto);
//
//        ProductDto productsDto = convertToProductDto(favoritesEntity.getProduct()); // второй связанный объект
//        favoritesDto.setProduct(productsDto);

//        return favoritesDto;
//    }
}