package com.example.xcale.rest;

import com.example.xcale.dto.*;
import com.example.xcale.model.ShoppingCart;
import com.example.xcale.services.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;


    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping(value = "")
    public List<ShoppingCartResponse> getAllShoppingCart() {
        return shoppingCartService.getAllShoppingCart();
    }

    @GetMapping(value = "/{code}")
    public ShoppingCartResponse getShoppingCartByCode(@PathVariable(value = "code") String code) {
        return shoppingCartService.getShoppingCartByCode(code);
    }

    @PostMapping(value = "")
    public CreateShoppingCartResponse createShoppingCart(@RequestBody CreateShoppingCartRequest shoppingCart) {
        return shoppingCartService.createShoppingCart(shoppingCart);
    }

    @PutMapping(value = "/addOrUpdateItem")
    public CreateShoppingCartResponse addOrUpdateItem(@RequestBody AddItemShoppingCart shoppingCart) {
        return shoppingCartService.addItemsShoppingCart(shoppingCart);
    }

    @DeleteMapping(value = "/{uuid}")
    public DeleteShoppingCartResponse deleteShoppingCart(@PathVariable(value = "uuid") String uuid) {
        return shoppingCartService.deleteShoppingCart(uuid);
    }



}
