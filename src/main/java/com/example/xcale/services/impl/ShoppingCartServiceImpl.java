package com.example.xcale.services.impl;

import com.example.xcale.dto.AddItemShoppingCart;
import com.example.xcale.dto.CreateShoppingCartRequest;
import com.example.xcale.dto.CreateShoppingCartResponse;
import com.example.xcale.dto.DeleteShoppingCartResponse;
import com.example.xcale.model.ShoppingCart;
import com.example.xcale.model.ShoppingCartDetail;
import com.example.xcale.model.ShoppingCartID;
import com.example.xcale.repository.ShoppingCartDetailRepository;
import com.example.xcale.repository.ShoppingCartRepository;
import com.example.xcale.services.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartDetailRepository shoppingCartDetailRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ShoppingCartDetailRepository shoppingCartDetailRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartDetailRepository = shoppingCartDetailRepository;
    }

    @Override
    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart getShoppingCartByCode(String code) {
        ShoppingCart shopingCart = shoppingCartRepository.findByCodShoppingCard(code);
        shopingCart.setEventDate(LocalDateTime.now());
        shoppingCartRepository.save(shopingCart);
        return shopingCart;
    }

    @Override
    public CreateShoppingCartResponse createShoppingCart(CreateShoppingCartRequest shoppingCart) {

        UUID uuid = UUID.randomUUID();
        shoppingCart.getShoppingCart().setCodShoppingCard(uuid.toString());
        shoppingCart.getShoppingCart().setCreationDate(LocalDateTime.now());
        shoppingCart.getShoppingCart().setEventDate(LocalDateTime.now());

        shoppingCart.getShoppingCart().setTotal(shoppingCart
                .getShoppingCartDetail()
                .stream()
                .map(shoppingCartDetailRequest -> shoppingCartDetailRequest.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(shoppingCartDetailRequest.getQuantity())))
                .reduce(BigDecimal::add).get());

        ShoppingCart shoppingCartNew = shoppingCartRepository.save(shoppingCart.getShoppingCart());

        List<ShoppingCartDetail> detailsShoppingCart = new ArrayList<>();
        //shoppingCart.getShoppingCart().setIdShoppingCart(10);
        shoppingCart.getShoppingCartDetail()
                .forEach(shoppingCartDetailRequest -> {
                    ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail(shoppingCartNew,
                            shoppingCartDetailRequest.getProduct(),
                            shoppingCartDetailRequest.getQuantity());
                    detailsShoppingCart.add(shoppingCartDetail);

                });

        shoppingCartDetailRepository.saveAll(detailsShoppingCart);

        return new CreateShoppingCartResponse(uuid.toString(), "Success", "00");
    }

    @Override
    public CreateShoppingCartResponse addItemsShoppingCart(AddItemShoppingCart shoppingCart) {

        ShoppingCart shoppingCartSearch = shoppingCartRepository.findByCodShoppingCard(shoppingCart.getCodeShoppingCart());
        ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail(shoppingCartSearch,
                shoppingCart.getShoppingCartDetail().getProduct(),
                shoppingCart.getShoppingCartDetail().getQuantity());

        ShoppingCartID shoppingCartID = new ShoppingCartID();
        shoppingCartID.setShoppingCart(shoppingCartSearch);
        shoppingCartID.setProduct(shoppingCart.getShoppingCartDetail().getProduct());

        if (!shoppingCartDetailRepository.findByShoppingCartID(shoppingCartID).isEmpty()) {
            shoppingCartDetailRepository.delete(shoppingCartDetail);
        }
        shoppingCartDetailRepository.save(shoppingCartDetail);

        updateTotalPriceShoppingCart(shoppingCartSearch.getCodShoppingCard());

        return new CreateShoppingCartResponse(shoppingCartSearch.getCodShoppingCard(), "Success", "00");
    }

    private void updateTotalPriceShoppingCart(String codShoppingCard) {
        ShoppingCart shoppingCartSearch = shoppingCartRepository.findByCodShoppingCard(codShoppingCard);
        shoppingCartSearch.setTotal(shoppingCartSearch
                .getDetailsShoppingCart()
                .stream()
                .map(shoppingCartDetailRequest -> shoppingCartDetailRequest.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(shoppingCartDetailRequest.getQuantity())))
                .reduce(BigDecimal::add).get());
        shoppingCartSearch.setEventDate(LocalDateTime.now());
        shoppingCartRepository.save(shoppingCartSearch);
    }

    @Override
    public DeleteShoppingCartResponse deleteShoppingCart(String uuid) {
        ShoppingCart shoppingCartSearch = shoppingCartRepository.findByCodShoppingCard(uuid);
        shoppingCartRepository.delete(shoppingCartSearch);
        return new DeleteShoppingCartResponse("00", "success");
    }

}
