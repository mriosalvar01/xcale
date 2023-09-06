package com.example.xcale.scheduler;

import com.example.xcale.model.ShoppingCart;
import com.example.xcale.repository.ShoppingCartRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class VerificaInactiveShoppingCart {

    private final ShoppingCartRepository shoppingCartRepository;
    private final long TWO_MINUTES = 1000 * 120;

    private static final String TIME_ZONE =
            "America/Lima";

    public VerificaInactiveShoppingCart(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Scheduled(fixedDelay = TWO_MINUTES, zone = TIME_ZONE)
    public void deleteShoppingCartInactive() {
        System.out.println("Inicio Hora de Scaneo: " + LocalDateTime.now());

        LocalDateTime localDateVerification = LocalDateTime.now().minusMinutes(10);
        System.out.println("Hora de Consulta para eliminacion: " + localDateVerification);

        System.out.println("Identificadores a ser eliminados : " +
                shoppingCartRepository.findAllWithEventDateBefore(localDateVerification)
                        .stream()
                        .map(ShoppingCart::getCodShoppingCard)
                        .collect(Collectors.toList()).toString());

        shoppingCartRepository.deleteAll(shoppingCartRepository.findAllWithEventDateBefore(localDateVerification));

    }
}
