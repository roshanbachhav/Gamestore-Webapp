package com.gamestore.services;

import java.math.BigDecimal;

import com.gamestore.domain.ShoppingCart;

public interface ShoppingCartService {
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	void clearCartData(ShoppingCart shoppingCart);
	void addToCart(Long gameId, Long shoppingCartId, int quantity, BigDecimal subTotal);
	ShoppingCart findOne(Long shoppingCartId);
}
