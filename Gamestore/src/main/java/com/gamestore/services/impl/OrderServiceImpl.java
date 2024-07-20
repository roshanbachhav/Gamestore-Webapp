package com.gamestore.services.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestore.domain.BillAddress;
import com.gamestore.domain.CartItems;
import com.gamestore.domain.Game;
import com.gamestore.domain.Order;
import com.gamestore.domain.Payment;
import com.gamestore.domain.ShippingAddress;
import com.gamestore.domain.ShoppingCart;
import com.gamestore.domain.User;
import com.gamestore.repository.OrderRepository;
import com.gamestore.services.CartItemService;
import com.gamestore.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartItemService cartItemService;

	@Override
	public synchronized Order newOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, Payment payment,
			BillAddress billingAddress, String shippingMethod, User user) {

		BigDecimal fixedCharge = new BigDecimal("150");
		BigDecimal internationalShippingCharge = new BigDecimal("3000");

		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("orderConfirm");
		order.setShippingAddress(shippingAddress);
		order.setPayment(payment);
		order.setShippingMethod(shippingMethod);

		List<CartItems> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for (CartItems cartItem : cartItemList) {
			Game game = cartItem.getGame();
			cartItem.setOrder(order);
			game.setStockAvailable(game.getStockAvailable() - cartItem.getQuantity());
		}

		BigDecimal totalAmount = shoppingCart.getFullTotal().add(fixedCharge);

		if (shippingMethod.equals("international")) {
			totalAmount = totalAmount.add(internationalShippingCharge);
		}

		order.setCartItems(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setTotalAmount(totalAmount);

		Calendar shippingDate = Calendar.getInstance();
		if (shippingMethod.equals("international")) {
			shippingDate.add(Calendar.DAY_OF_YEAR, 24);
		} else {
			shippingDate.add(Calendar.DAY_OF_YEAR, 12);
		}
		order.setShippingDate(shippingDate.getTime());

		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		return order;

	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

}
