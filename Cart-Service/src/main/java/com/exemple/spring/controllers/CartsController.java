package com.exemple.spring.controllers;

import com.exemple.spring.core.ProductDto;
import com.exemple.spring.dto.StringResponse;
import com.exemple.spring.models.Cart;
import com.exemple.spring.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


@RestController
@Data
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Корзина", description = "Методы работы с корзиной")
public class CartsController {

    private final CartService cartService;
    private ArrayList<ProductDto> buferAnalitListProductDto;

    @PostConstruct
    private void init(){
        buferAnalitListProductDto = new ArrayList<>();
    }

    @GetMapping("/{uuid}")
    @Operation(
            summary = "Запрос на получение существующей корзины",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Cart.class))
                    )
            }
    )
    public Cart getCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")
    @Operation(
            summary = "Запрос на создание корзины",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    @Operation(
            summary = "Запрос на добавление продукта",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }


    @GetMapping("/{uuid}/decrement/{productId}")
    @Operation(
            summary = "Запрос на уменьшение кол-ва продуктов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public void decrement(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    @Operation(
            summary = "Запрос на удаление продукта",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    @Operation(
            summary = "Запрос на очистку содержимого",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    @Operation(
            summary = "Запрос на соединение корзин - корзины неавторизованного пользователя при его авторизации с существующей корзиной в БД",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema())
                    )
            }
    )
    public void merge(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    @GetMapping("/analit")
    @Operation(
            summary = "Запрос на получение данных о добавляемых в корзины продуктов у пользователей",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))
                            )
                    )
            }
    )
    public ArrayList<ProductDto> getAnalit(){
        buferAnalitListProductDto.clear();
        buferAnalitListProductDto.addAll(cartService.getListProductsForAnalit());
        cartService.getAnalitService().registration(buferAnalitListProductDto);
        cartService.clearListProductsForAnalit();
        return buferAnalitListProductDto;
    }

  // Тестовая цепочка проброса исключений по цепочке МС CartService -> CoreService -> Front
//    @GetMapping("/one")
//
//    public ArrayList<ProductDto> getOne(){
//        if(true){
//            throw new BadConnectionServiceException("Ошибка в сервисе корзины", CART_NOT_FOUND);
//        }
//
//        buferAnalitListProductDto.clear();
//        buferAnalitListProductDto.addAll(cartService.getListProductsForAnalit());
//        cartService.getAnalitService().registration(buferAnalitListProductDto);
//        cartService.clearListProductsForAnalit();
//        return buferAnalitListProductDto;
//    }


    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
