package com.example.xcale.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ShoppingCartID implements Serializable {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idshoppingcart")
    private ShoppingCart shoppingCart;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idproduct")
    private Product product;



}
