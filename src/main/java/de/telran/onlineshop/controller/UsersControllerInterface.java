package de.telran.onlineshop.controller;

import de.telran.onlineshop.dto.CategoryDto;
import de.telran.onlineshop.dto.UserDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.util.List;

@Tag(name = "Users", description = "Контроллер для работы с пользователями",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по пользователям",
                url = "https://example.com/docs/users-controller"
        )
)
public interface UsersControllerInterface {
    @Operation(
            summary = "Все пользователи",
            description = "Позволяет получить всех пользователей"
    )
    ResponseEntity<List<UserDto>> getAllUsers();

    @Operation(
            summary = "Поиск по Id",
            description = "Позволяет найти информацию по идентификатору Id пользователя"
    )
    ResponseEntity<UserDto> getUserById(
            @Parameter(description = "Идентификатор пользователя", required = true, example = "1")
            Long id) throws FileNotFoundException;

    @Hidden
        //скрытие данного endpoint из openApi
    ResponseEntity<Void> deleteUsers(@PathVariable Long id);
}