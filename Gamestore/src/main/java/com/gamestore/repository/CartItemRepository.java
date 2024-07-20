package com.gamestore.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gamestore.domain.CartItems;
import com.gamestore.domain.Order;
import com.gamestore.domain.ShoppingCart;

@Transactional
public interface CartItemRepository extends CrudRepository<CartItems, Long> {
	
	List<CartItems> findByShoppingCart(ShoppingCart shoppingCart);

	CartItems findByGameIdAndShoppingCartId(Long gameId, Long shoppingCartId);
	
	List<CartItems> findByOrder(Order order);
}
