package com.example.xcale.dto;

import com.example.xcale.model.ShoppingCartDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponse {

    private Integer idShoppingCart;

    private String codShoppingCard;

    private LocalDateTime creationDate;

    private LocalDateTime eventDate;

    private BigDecimal total;

    private String state;

    private List<ShoppingCartDetailResponse> detailsShoppingCart = new ArrayList<>();

}
