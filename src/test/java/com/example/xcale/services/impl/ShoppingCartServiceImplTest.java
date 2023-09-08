package com.example.xcale.services.impl;

import com.example.xcale.dto.*;
import com.example.xcale.model.Product;
import com.example.xcale.model.ShoppingCart;
import com.example.xcale.model.ShoppingCartDetail;
import com.example.xcale.repository.ShoppingCartDetailRepository;
import com.example.xcale.repository.ShoppingCartRepository;
import com.example.xcale.util.ConvertJsonToBean;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartDetailRepository shoppingCartDetailRepository;


    @Test
    void getAllShoppingCart() {

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        ShoppingCart item = new ShoppingCart();
        item.setCodShoppingCard("UIDD");
        List<ShoppingCartDetail> detail = new ArrayList<>();
        Product p = new Product();
        p.setPrice(BigDecimal.ONE);
        ShoppingCartDetail itemDetail = new ShoppingCartDetail(item, p, 1);

        detail.add(itemDetail);
        item.setDetailsShoppingCart(detail);
        shoppingCarts.add(item);
        when(shoppingCartRepository.findAll()).thenReturn(shoppingCarts);
        List<ShoppingCartResponse> result = shoppingCartService.getAllShoppingCart();
        assertTrue(result.size() != 0);
        assertTrue(result.stream().noneMatch(shoppingCart -> shoppingCart.getCodShoppingCard().isEmpty()));
        assertEquals(1, result.size());
    }

    @Test
    void getShoppingCartByCode() throws IOException {

        ShoppingCart item = new ShoppingCart();
        item.setCodShoppingCard("UIDD");
        item.setIdShoppingCart(1);
        item.setCreationDate(LocalDateTime.now());
        item.setEventDate(LocalDateTime.now());
        item.setTotal(BigDecimal.ONE);
        item.setState("1");
        List<ShoppingCartDetail> detail = new ArrayList<>();
        Product p = new Product();
        p.setPrice(BigDecimal.ONE);
        ShoppingCartDetail itemDetail = new ShoppingCartDetail(item, p, 1);
        detail.add(itemDetail);
        item.setDetailsShoppingCart(detail);

        when(shoppingCartRepository.findByCodShoppingCard(anyString())).thenReturn(item);
        when(shoppingCartRepository.save(any())).thenReturn(item);

        ShoppingCartResponse result = shoppingCartService.getShoppingCartByCode("code");
        assertTrue(!item.getCodShoppingCard().isEmpty());
        assertEquals(item.getCodShoppingCard(), result.getCodShoppingCard());
    }

    @Test
    void createShoppingCart() throws IOException {
        CreateShoppingCartRequest createShoppingCartRequest = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/req/createshoppingcart.json",
                        CreateShoppingCartRequest.class);
        ShoppingCart shoppingCart = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/resp/shoppingcart.json", ShoppingCart.class);
        CreateShoppingCartResponse createShoppingCartResponse = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/resp/createshoppingcart.json",
                        CreateShoppingCartResponse.class);
        when(shoppingCartRepository.findByCodShoppingCard(anyString())).thenReturn(shoppingCart);
        when(shoppingCartDetailRepository.saveAll(any())).thenReturn(List.of());
        CreateShoppingCartResponse result = shoppingCartService.createShoppingCart(createShoppingCartRequest);
        assertEquals(createShoppingCartResponse.getCode(), result.getCode());
    }

    @Test
    void addItemsShoppingCart() throws IOException {
        AddItemShoppingCart addItemShoppingCart = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/req/addorupdateitem.json",
                        AddItemShoppingCart.class);
        ShoppingCart item = new ShoppingCart();
        item.setCodShoppingCard("UIDD");
        item.setIdShoppingCart(1);
        item.setCreationDate(LocalDateTime.now());
        item.setEventDate(LocalDateTime.now());
        item.setTotal(BigDecimal.ONE);
        item.setState("1");
        List<ShoppingCartDetail> detail = new ArrayList<>();
        Product p = new Product();
        p.setPrice(BigDecimal.ONE);
        ShoppingCartDetail itemDetail = new ShoppingCartDetail(item, p, 1);
        detail.add(itemDetail);
        item.setDetailsShoppingCart(detail);

        CreateShoppingCartResponse createShoppingCartResponse =
                new CreateShoppingCartResponse("282af4c3-d717-4714-891e-0763fcc33816", "message","00");
        when(shoppingCartRepository.findByCodShoppingCard(anyString())).thenReturn(item);
        when(shoppingCartDetailRepository.findByShoppingCartID(any())).thenReturn(List.of());
        when(shoppingCartDetailRepository.save(any())).thenReturn(new ShoppingCartDetail());
        when(shoppingCartDetailRepository.saveAll(any())).thenReturn(List.of());
        CreateShoppingCartResponse result = shoppingCartService.addItemsShoppingCart(addItemShoppingCart);
        assertEquals(createShoppingCartResponse.getCode(), result.getCode());
    }

    @Test
    void deleteShoppingCart() throws IOException {
        ShoppingCart shoppingCart1 = ConvertJsonToBean
                .getBeanFromJsonFile("src/test/resources/shoppingcart/resp/shoppingcart.json", ShoppingCart.class);
        CreateShoppingCartResponse createShoppingCartResponse =
                new CreateShoppingCartResponse("282af4c3-d717-4714-891e-0763fcc33816", "message","00");
        when(shoppingCartRepository.findByCodShoppingCard(anyString())).thenReturn(shoppingCart1);
        DeleteShoppingCartResponse result = shoppingCartService.deleteShoppingCart("code");
        assertEquals(createShoppingCartResponse.getCode(), result.getCode());
    }
}