package com.example.xcale.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "ShoppingCart")
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @Column(name = "idshoppingcart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idShoppingCart;

    @Column(name = "codshoppingcard")
    private String codShoppingCard;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @Column(name = "creationdate")
    @Nullable
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @Nullable
    @Column(name = "eventdate")
    private LocalDateTime eventDate;

    private BigDecimal total;

    private String state;



    @OneToMany(mappedBy = "shoppingCartID.shoppingCart", cascade = CascadeType.REMOVE)
    private List<ShoppingCartDetail> detailsShoppingCart = new ArrayList<>();

}
