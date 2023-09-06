package com.example.xcale.rest;

import com.example.xcale.dto.AddItemShoppingCart;
import com.example.xcale.dto.CreateShoppingCartRequest;
import com.example.xcale.dto.CreateShoppingCartResponse;
import com.example.xcale.dto.DeleteShoppingCartResponse;
import com.example.xcale.model.ShoppingCart;
import com.example.xcale.services.ShoppingCartService;
import com.example.xcale.util.ConvertJsonToBean;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Test
    void getAllShoppingCart() throws IOException {
        List<ShoppingCart> shoppingCarts = ConvertJsonToBean
                .getListFromJsonFile("src/test/resources/shoppingcart/resp/listshoppingcart.json", ShoppingCart.class);
        when(shoppingCartService.getAllShoppingCart()).thenReturn(shoppingCarts);
        List<ShoppingCart> result = shoppingCartController.getAllShoppingCart();
        assertEquals(shoppingCarts, result);
    }

    @Test
    void getShoppingCartByCode() throws IOException {
        ShoppingCart shoppingCart = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/resp/shoppingcart.json", ShoppingCart.class);
        when(shoppingCartService.getShoppingCartByCode(anyString())).thenReturn(shoppingCart);
        ShoppingCart result = shoppingCartController.getShoppingCartByCode("123");
        assertEquals(shoppingCart, result);
    }

    @Test
    void createShoppingCart() throws IOException {
        CreateShoppingCartRequest createShoppingCartRequest = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/req/createshoppingcart.json",
                        CreateShoppingCartRequest.class);

        CreateShoppingCartResponse createShoppingCartResponse = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/resp/createshoppingcart.json",
                        CreateShoppingCartResponse.class);

        when(shoppingCartService.createShoppingCart(any())).thenReturn(createShoppingCartResponse);
        CreateShoppingCartResponse result = shoppingCartController.createShoppingCart(createShoppingCartRequest);
        assertEquals(createShoppingCartResponse, result);
    }

    @Test
    void addOrUpdateItem() throws IOException {
        AddItemShoppingCart createShoppingCartRequest = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/req/addorupdateitem.json",
                        AddItemShoppingCart.class);

        CreateShoppingCartResponse createShoppingCartResponse = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/resp/addorupdateitem.json",
                        CreateShoppingCartResponse.class);

        when(shoppingCartService.addItemsShoppingCart(any())).thenReturn(createShoppingCartResponse);
        CreateShoppingCartResponse result = shoppingCartController.addOrUpdateItem(createShoppingCartRequest);
        assertEquals(createShoppingCartResponse, result);
    }

    @Test
    void deleteShoppingCart() throws IOException {

        DeleteShoppingCartResponse deleteShoppingCartResponse = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/resp/deleteshoppingcart.json",
                        DeleteShoppingCartResponse.class);

        when(shoppingCartService.deleteShoppingCart(any())).thenReturn(deleteShoppingCartResponse);
        DeleteShoppingCartResponse result = shoppingCartController.deleteShoppingCart("f4390151-54a6-412d-82a5-6df1db29d31c");
        assertEquals(deleteShoppingCartResponse, result);
    }
}