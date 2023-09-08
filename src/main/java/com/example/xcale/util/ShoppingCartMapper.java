package com.example.xcale.util;

import com.example.xcale.dto.ShoppingCartDetailResponse;
import com.example.xcale.dto.ShoppingCartResponse;
import com.example.xcale.model.Product;
import com.example.xcale.model.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartMapper {
    public static ShoppingCartResponse convertToShoppingCartResponse(ShoppingCart shoppingCart) {
        return ShoppingCartResponse
                .builder()
                    .idShoppingCart(shoppingCart.getIdShoppingCart())
                    .codShoppingCard(shoppingCart.getCodShoppingCard())
                    .creationDate(shoppingCart.getCreationDate())
                    .eventDate(shoppingCart.getEventDate())
                    .total(shoppingCart.getTotal())
                    .state(shoppingCart.getState())
                .detailsShoppingCart(shoppingCart
                        .getDetailsShoppingCart()
                        .stream()
                        .map(shoppingCartDetail -> ShoppingCartDetailResponse
                                .builder()
                                    .quantity(shoppingCartDetail.getQuantity())
                                    .product(shoppingCartDetail.getShoppingCartID().getProduct())
                                    .subTotalPrice(shoppingCartDetail.getSubTotalPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
