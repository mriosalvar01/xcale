package com.example.xcale.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "product")
public class Product {

    @Id
    @Column(name = "idproduct")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @Column(name = "codproduct")
    private String codProduct;

    private String name;

    @Column(name = "creationdate")
    private LocalDateTime creationDate;

    private BigDecimal price;

    private String state;


}
