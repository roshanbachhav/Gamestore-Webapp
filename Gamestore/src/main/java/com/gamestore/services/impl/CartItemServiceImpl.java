package com.gamestore.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestore.domain.CartItems;
import com.gamestore.domain.Game;
import com.gamestore.domain.GameCartItem;
import com.gamestore.domain.Order;
import com.gamestore.domain.ShoppingCart;
import com.gamestore.domain.User;
import com.gamestore.repository.CartItemRepository;
import com.gamestore.repository.GameCartItemRepository;
import com.gamestore.services.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private GameCartItemRepository gameCartItemRepository;

	public List<CartItems> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	@Override
	public CartItems updateCartItem(CartItems cartItems) {
		BigDecimal price = BigDecimal.valueOf(cartItems.getGame().getOurPrice());
		BigDecimal quantity = new BigDecimal(cartItems.getQuantity());

		BigDecimal calcPrice = price.multiply(quantity);

		calcPrice = calcPrice.setScale(2, RoundingMode.HALF_UP);

		cartItems.setSubTotal(calcPrice);

		cartItemRepository.save(cartItems);

		return cartItems;
	}

//	public CartItems addGameInCart(Game game, User user, int quantity) {
//
//		List<CartItems> cartItemLists = findByShoppingCart(user.getShoppingCart());
//
//		for (CartItems cartItem : cartItemLists) {
//			if (game.getId() == cartItem.getGame().getId()) {
//				cartItem.setQuantity(cartItem.getQuantity() + quantity);
//				cartItem.setSubTotal(new BigDecimal(game.getOurPrice()).multiply(new BigDecimal(quantity)));
//				cartItemRepository.save(cartItem);
//				return cartItem;
//			}
//		}
//
//		CartItems cartItem = new CartItems();
//		cartItem.setShoppingCart(user.getShoppingCart());
//		cartItem.setGame(game);
//		cartItem.setQuantity(quantity);
//		cartItem.setSubTotal(new BigDecimal(game.getOurPrice()).multiply(new BigDecimal(quantity)));
//		cartItem = cartItemRepository.save(cartItem);
//
//		GameCartItem gci = new GameCartItem();
//		gci.setCartItems(cartItem);
//		gci.setGame(game);
//		gameCartItemRepository.save(gci);
//
//		return cartItem;
//	}
	
	@Override
	public CartItems addGameInCart(Game game, User user, int quantity) {
		ShoppingCart shoppingCart = user.getShoppingCart();
		CartItems existingItem = cartItemRepository.findByGameIdAndShoppingCartId(game.getId(), shoppingCart.getId());

		if (existingItem != null) {
			existingItem.setQuantity(existingItem.getQuantity() + quantity);
			existingItem.setSubTotal(new BigDecimal(game.getOurPrice()).multiply(new BigDecimal(existingItem.getQuantity())));
			cartItemRepository.save(existingItem);
			return existingItem;
		} else {
			CartItems newItem = new CartItems();
			newItem.setShoppingCart(shoppingCart);
			newItem.setGame(game);
			newItem.setQuantity(quantity);
			newItem.setSubTotal(new BigDecimal(game.getOurPrice()).multiply(new BigDecimal(quantity)));
			newItem = cartItemRepository.save(newItem);

			GameCartItem gci = new GameCartItem();
			gci.setCartItems(newItem);
			gci.setGame(game);
			gameCartItemRepository.save(gci);

			return newItem;
		}
	}

	public CartItems findById(Long id) {
		return cartItemRepository.findById(id).orElse(null);
	}

	@Override
	public void removeCartItemById(CartItems cartItem) {
		gameCartItemRepository.deleteByCartItems(cartItem);
		cartItemRepository.delete(cartItem);
	}

	@Override
	public CartItems save(CartItems cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItems findByGameIdAndShoppingCartId(Long gameId, Long shoppingCartId) {
		return cartItemRepository.findByGameIdAndShoppingCartId(gameId, shoppingCartId);
	}

	@Override
	public List<CartItems> findByOrder(Order order) {
		return cartItemRepository.findByOrder(order);
	}	
}
