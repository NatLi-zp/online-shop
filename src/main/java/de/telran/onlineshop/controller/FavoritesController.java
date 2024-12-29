package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.FavoritesDto;
import de.telran.onlineshop.service.FavoritesService;
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

//    @GetMapping  //select
//    public List<FavoritesDto> getAllFavorites() {
//        return favoritesService.getAllFavorites();
//    }


}