package com.gamestore.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestore.domain.CartItems;
import com.gamestore.domain.Game;
import com.gamestore.domain.ShoppingCart;
import com.gamestore.repository.CartItemRepository;
import com.gamestore.repository.ShoppingCartRepository;
import com.gamestore.services.CartItemService;
import com.gamestore.services.GameService;
import com.gamestore.services.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private GameService gameService;
	
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		BigDecimal cartTotalPrice = new BigDecimal(0);
		
		List<CartItems> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItems cartItems : cartItemList) {
			if(cartItems.getGame().getStockAvailable() > 0) {
				cartItemService.updateCartItem(cartItems);
				cartTotalPrice = cartTotalPrice.add(cartItems.getSubTotal());
				
			}
		}
		
		shoppingCart.setFullTotal(cartTotalPrice);
		shoppingCartRepository.save(shoppingCart);
		
		return shoppingCart;
	}


	public void clearCartData(ShoppingCart shoppingCart) {
		List<CartItems> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		for(CartItems cartItem : cartItemList) {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}
		
		shoppingCart.setFullTotal(new BigDecimal(0));
		shoppingCartRepository.save(shoppingCart);
	}
	
	@Override
	public ShoppingCart findOne(Long shoppingCartId) {
		return shoppingCartRepository.findById(shoppingCartId).orElse(null);
	}

	public void addToCart(Long gameId, Long shoppingCartId, int quantity, BigDecimal subTotal) {
		cartItemService.addGameInCart(gameService.findOne(gameId), shoppingCartRepository.findById(shoppingCartId).orElse(null).getUser(), quantity);
    }


}
