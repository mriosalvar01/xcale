package com.example.xcale.scheduler;

import com.example.xcale.model.ShoppingCart;
import com.example.xcale.repository.ShoppingCartRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class VerificaInactiveShoppingCartTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private VerificaInactiveShoppingCart verificaInactiveShoppingCart;

    @Test
    void deleteShoppingCartInactive() {
        List<ShoppingCart> shoppingCartsToDelete = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setEventDate(LocalDateTime.now().minusMinutes(20));
        shoppingCart.setCodShoppingCard("CODE");
        shoppingCartsToDelete.add(shoppingCart);

        when(shoppingCartRepository.findAllWithEventDateBefore(any()))
                .thenReturn(shoppingCartsToDelete);

        verificaInactiveShoppingCart.deleteShoppingCartInactive();

        verify(shoppingCartRepository, times(2)).deleteAll(shoppingCartRepository.findAllWithEventDateBefore(any()));
    }
}