package com.gamestore.services;

import java.util.List;

import com.gamestore.domain.CartItems;
import com.gamestore.domain.Game;
import com.gamestore.domain.Order;
import com.gamestore.domain.ShoppingCart;
import com.gamestore.domain.User;

public interface CartItemService {

	List<CartItems> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItems updateCartItem(CartItems cartItems);
	
	CartItems addGameInCart(Game game , User user , int quantity);
	
	CartItems findById(Long id);
	
	void removeCartItemById(CartItems cartItem);
	
	CartItems save(CartItems cartItem);
	
    CartItems findByGameIdAndShoppingCartId(Long gameId, Long shoppingCartId);
    
    List<CartItems> findByOrder(Order order);

}
