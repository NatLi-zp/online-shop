package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.FavoritesDto;
import de.telran.onlineshop.dto.ProductDto;
import de.telran.onlineshop.service.FavoritesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/favorites")
public class FavoritesController {

    //@Autowired - иньекция через value (не рекомендуемая из-за Reflection)
    private FavoritesService favoritesService;

    //@Autowired - иньекция через конструктор (рекомендуемая !!!), авто аннотирование с версии 3.0
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    // @Autowired - иньекция через сеттер (обязательно использовать аннотацию)
    public void setFavoriteService(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping  //select
    public List<FavoritesDto> getAllFavorites() {
        return favoritesService.getAllFavorites();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<FavoritesDto> getFavoriteById(@PathVariable Long id) { ///categories/find/3
        FavoritesDto favoritesDto = favoritesService.getFavoriteById(id);
        return ResponseEntity.status(200).body(favoritesDto);
    }




}