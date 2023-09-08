package com.example.xcale.dto;

import com.example.xcale.model.Product;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDetailResponse {

    private Integer quantity;

    private Product product;

    private BigDecimal subTotalPrice;
}
