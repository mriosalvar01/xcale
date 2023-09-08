package com.example.xcale.services;

import com.example.xcale.dto.*;
import com.example.xcale.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    public List<ShoppingCartResponse> getAllShoppingCart();

    public ShoppingCartResponse getShoppingCartByCode(String code);

    public CreateShoppingCartResponse createShoppingCart(CreateShoppingCartRequest shoppingCart);

    public CreateShoppingCartResponse addItemsShoppingCart(AddItemShoppingCart shoppingCart);

    DeleteShoppingCartResponse deleteShoppingCart(String uuid);
}
