package com.example.xcale.repository;

import com.example.xcale.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    ShoppingCart findByCodShoppingCard(String code);

    @Query("select s from ShoppingCart s where s.eventDate < :eventDateScheduler")
    List<ShoppingCart> findAllWithEventDateBefore(@Param("eventDateScheduler") LocalDateTime eventDateScheduler);

}
