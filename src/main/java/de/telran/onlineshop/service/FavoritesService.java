package de.telran.onlineshop.service;

import de.telran.onlineshop.configure.MapperUtil;
import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.FavoritesDto;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.CategoriesEntity;
import de.telran.onlineshop.entity.FavoritesEntity;
import de.telran.onlineshop.entity.ProductsEntity;
import de.telran.onlineshop.entity.UsersEntity;
import de.telran.onlineshop.mapper.Mappers;
import de.telran.onlineshop.repository.FavoritesRepository;
import de.telran.onlineshop.repository.ProductsRepository;
import de.telran.onlineshop.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class FavoritesService {
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final FavoritesRepository favoritesRepository;
    private final Mappers mappers;

    //@PostConstruct
    void init() {
        UsersEntity user1 = usersRepository.findById(1L).orElse(null);
        ProductsEntity product1 = productsRepository.findById(2L).orElse(null);
        FavoritesEntity favorite1 = new FavoritesEntity(null, user1, product1);
        favoritesRepository.save(favorite1);
        ProductsEntity product2 = productsRepository.findById(1L).orElse(null);
        FavoritesEntity favorite2 = new FavoritesEntity(null, user1, product2);
        favoritesRepository.save(favorite2);
    }

    public List<FavoritesDto> getAllFavorites() {
        List<FavoritesEntity> favoritesEntities = favoritesRepository.findAll();

        return favoritesEntities.stream()

                .map(entity -> new FavoritesDto(entity.getFavoriteId(), null,null))
                .collect(Collectors.toList());
     //  List<FavoritesEntity> favoritesEntities = favoritesRepository.findAll();
       // List<FavoritesDto> favoritesDtoList = MapperUtil.convertList(favoritesEntities, mappers::convertToFavoritesDto);
      //  return favoritesDtoList;
    }
}
