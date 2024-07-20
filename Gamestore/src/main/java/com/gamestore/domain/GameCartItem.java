package com.gamestore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GameCartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="game_id",nullable = false)
	private Game game;
	
	@ManyToOne
	@JoinColumn(name="cart_item_id")
	private CartItems cartItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public CartItems getCartItems() {
		return cartItems;
	}

	public void setCartItems(CartItems cartItems) {
		this.cartItems = cartItems;
	}
	
	
	
}
