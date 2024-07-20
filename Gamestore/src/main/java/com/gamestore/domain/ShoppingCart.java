package com.gamestore.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private BigDecimal fullTotal;
	
	@OneToMany(mappedBy="shoppingCart" , cascade = CascadeType.ALL , fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CartItems> cartItemList;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getFullTotal() {
		return fullTotal;
	}

	public void setFullTotal(BigDecimal fullTotal) {
		this.fullTotal = fullTotal;
	}

	public List<CartItems> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItems> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
