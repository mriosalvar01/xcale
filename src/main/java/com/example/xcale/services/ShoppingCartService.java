package com.example.xcale.services;

import com.example.xcale.dto.AddItemShoppingCart;
import com.example.xcale.dto.CreateShoppingCartRequest;
import com.example.xcale.dto.CreateShoppingCartResponse;
import com.example.xcale.dto.DeleteShoppingCartResponse;
import com.example.xcale.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    public List<ShoppingCart> getAllShoppingCart();

    public ShoppingCart getShoppingCartByCode(String code);

    public CreateShoppingCartResponse createShoppingCart(CreateShoppingCartRequest shoppingCart);

    public CreateShoppingCartResponse addItemsShoppingCart(AddItemShoppingCart shoppingCart);

    DeleteShoppingCartResponse deleteShoppingCart(String uuid);
}
