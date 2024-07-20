package com.gamestore.services;

import com.gamestore.domain.BillAddress;
import com.gamestore.domain.Order;
import com.gamestore.domain.Payment;
import com.gamestore.domain.ShippingAddress;
import com.gamestore.domain.ShoppingCart;
import com.gamestore.domain.User;

public interface OrderService {
	
	Order newOrder(ShoppingCart shoppingCart , ShippingAddress shippingAddress , Payment payment , BillAddress billingAddress , String shippingMethod , User user);
	
	Order findById(Long id);
}
