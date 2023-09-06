package com.example.xcale.dto;

import com.example.xcale.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemShoppingCart implements Serializable {
    private String codeShoppingCart;
    private ShoppingCartDetailRequest shoppingCartDetail;
}
