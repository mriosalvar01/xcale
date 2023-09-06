package com.example.xcale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "shopping_cart_detail")
public class ShoppingCartDetail {

    @EmbeddedId
    @JsonIgnore
    private ShoppingCartID shoppingCartID;

    private Integer quantity;

    @Transient
    private Product product;

    @Transient
    private BigDecimal subTotalPrice;

    public ShoppingCartDetail(){

    }
    public ShoppingCartDetail(ShoppingCart shoppingCart, Product product, Integer quantity) {
        this.shoppingCartID = new ShoppingCartID();
        shoppingCartID.setShoppingCart(shoppingCart);
        shoppingCartID.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return this.shoppingCartID.getProduct();
    }

    @Transient
    @Nullable
    public BigDecimal getSubTotalPrice() {
        return this.shoppingCartID.getProduct().getPrice().multiply(new BigDecimal(quantity));
    }

}
