package com.gamestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gamestore.domain.CartItems;
import com.gamestore.domain.GameCartItem;

@Transactional
public interface GameCartItemRepository extends CrudRepository<GameCartItem, Long> {
    void deleteByCartItems(CartItems cartItems);
}
