package com.example.xcale.dto;

import com.example.xcale.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDetailRequest {
    private Integer quantity;
    private Product product;
}
