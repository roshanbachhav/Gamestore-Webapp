package com.adminportal.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class CartItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int quantity;
	
	private BigDecimal subTotal;
	
	@OneToOne()
	private Game game;
	
	@OneToMany(mappedBy="cartItems")
	@JsonIgnore
	private List<GameCartItem> gameCartItemList;
	
	@ManyToOne
	@JoinColumn(name="shopping_card_id")
	private shoppingCart shoppingCart;
	
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}


	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public List<GameCartItem> getGameCartItemList() {
		return gameCartItemList;
	}


	public void setGameCartItemList(List<GameCartItem> gameCartItemList) {
		this.gameCartItemList = gameCartItemList;
	}


	public shoppingCart getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(shoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
}
