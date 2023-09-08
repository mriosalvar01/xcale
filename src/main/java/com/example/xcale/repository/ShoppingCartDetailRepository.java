package com.example.xcale.repository;

import com.example.xcale.model.ShoppingCart;
import com.example.xcale.model.ShoppingCartDetail;
import com.example.xcale.model.ShoppingCartID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartDetailRepository extends JpaRepository<ShoppingCartDetail, Integer> {

    List<ShoppingCartDetail> findByShoppingCartID(ShoppingCartID shoppingCartID);

}
