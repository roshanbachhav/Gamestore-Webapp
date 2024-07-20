package com.gamestore.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamestore.domain.BillAddress;
import com.gamestore.domain.CartItems;
import com.gamestore.domain.Order;
import com.gamestore.domain.Payment;
import com.gamestore.domain.ShippingAddress;
import com.gamestore.domain.ShoppingCart;
import com.gamestore.domain.User;
import com.gamestore.domain.UserBilling;
import com.gamestore.domain.UserPayment;
import com.gamestore.domain.UserShipping;
import com.gamestore.services.BillingAddressService;
import com.gamestore.services.CartItemService;
import com.gamestore.services.OrderService;
import com.gamestore.services.PaymentService;
import com.gamestore.services.ShoppingAddressService;
import com.gamestore.services.ShoppingCartService;
import com.gamestore.services.UserPaymentService;
import com.gamestore.services.UserService;
import com.gamestore.services.UserShippingService;
import com.gamestore.utility.INDConst;
import com.gamestore.utility.MailConstructor;

@Controller
public class CheckoutController {

	@Autowired
	private UserService userService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private ShoppingAddressService shoppingAddressService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private BillingAddressService billingAddressService;
	@Autowired
	private UserShippingService userShippingService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingAddressService shippingAddressService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private UserPaymentService userPaymentService;
	@Autowired
	private MailConstructor mailConstructor;

	private ShippingAddress shippingAddress = new ShippingAddress();
	private BillAddress billingAddress = new BillAddress();
	private Payment payment = new Payment();

	@GetMapping("/checkout")
	public String checkout(@RequestParam("id") Long cartId,
			@RequestParam(value = "EmptyFields", required = false) boolean emptyFields, Model m, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		if (!cartId.equals(user.getShoppingCart().getId())) {
	        return "checkout";
	    }
		
		ShoppingCart shoppingCart = user.getShoppingCart();
	    List<CartItems> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		if (cartItemList.size() == 0) {
			m.addAttribute("emptyCart", true);
			return "forward:/Cart/MyCart";
		}

		for (CartItems cartItems : cartItemList) {
			if (cartItems.getGame().getStockAvailable() < cartItems.getQuantity()) {
				m.addAttribute("outOfQuantity", true);
				return "forward:/Cart/MyCart";
			}
		}

		List<UserShipping> userShippingList = user.getUserShippingList();
		List<UserPayment> userPaymentList = user.getUserPaymentList();

		m.addAttribute("userShippingList", userShippingList);
		m.addAttribute("userPaymentList", userPaymentList);

		if (userShippingList.size() == 0) {
			m.addAttribute("EmptyShippingList", true);
		} else {
			m.addAttribute("EmptyShippingList", false);
		}

		if (userPaymentList.size() == 0) {
			m.addAttribute("EmptyPaymentList", true);
		} else {
			m.addAttribute("EmptyPaymentList", false);
		}

		for (UserShipping userShipping : userShippingList) {
			if (userShipping.isUserShippingDefault()) {
				shoppingAddressService.addByUserShippingAddress(userShipping, shippingAddress);
			}
		}

		for (UserPayment userPayment : userPaymentList) {
			if (userPayment.isDefaultPayment()) {
				paymentService.addByUserPaymentDetails(userPayment, payment);
				billingAddressService.addByUserBilling(userPayment.getUserBilling(), billingAddress);
			}
		}

		List<Map.Entry<String, String>> sortedStates = INDConst.indiaStates.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

		m.addAttribute("stateList", sortedStates);
		

		m.addAttribute("user", user);
		m.addAttribute("shipping", shippingAddress);
		m.addAttribute("payment", payment);
		m.addAttribute("billingAddress", billingAddress);
		m.addAttribute("shoppingCart", user.getShoppingCart());
		m.addAttribute("cartItemList", cartItemList);
		
		
		List<CartItems> cartItems = cartItemService.findByShoppingCart(shoppingCart);
		m.addAttribute("gameItemList", cartItems);

		if (emptyFields) {
			m.addAttribute("EmptyFields", true);
		}

		return "checkout";
	}

	@RequestMapping("/checkout")
	public String checkoutFormSubmit(@ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
	                                 @ModelAttribute("payment") Payment payment, 
	                                 @ModelAttribute("billingAddress") BillAddress billingAddress,
	                                 @ModelAttribute("shippingMethod") String shippingMethod, 
	                                 Model m, Principal principal) {

	    User user = userService.findByUsername(principal.getName());
	    ShoppingCart shoppingCart = user.getShoppingCart();

	    if (shoppingCart == null) {
	        m.addAttribute("error", "Shopping cart not found.");
	        return "errorPage";
	    }

	    List<CartItems> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
	    m.addAttribute("cartItemList", cartItemList);

	    if (shippingAddress.getShippingAddressStreet().isEmpty() || 
	        shippingAddress.getShippingAddressCity().isEmpty() || 
	        shippingAddress.getShippingAddressCountry().isEmpty() || 
	        shippingAddress.getShippingAddressState().isEmpty() || 
	        shippingAddress.getShippingAddressZipcode().isEmpty() || 
	        payment.getCardNumber().isEmpty() || 
	        payment.getCvv() == 0 || 
	        billingAddress.getStreet().isEmpty() || 
	        billingAddress.getZipcode().isEmpty() || 
	        billingAddress.getUserBillName().isEmpty()||
	        user.getPhone().isEmpty()) {

	        return "redirect:/checkout?id=" + shoppingCart.getId() + "&EmptyFields";
	    }
	    
	    Order order = orderService.newOrder(shoppingCart, shippingAddress, payment, billingAddress, shippingMethod, user);

	    mailConstructor.sendOrderConfirmationEmail(user, order, Locale.ENGLISH);

	    shoppingCartService.clearCartData(shoppingCart);

	    BigDecimal totalPriceWithInternational = shoppingCart.getFullTotal();

	    BigDecimal internationalFixedPrice = new BigDecimal(3000);

	    if (shippingMethod.equals("international")) {
	        totalPriceWithInternational = totalPriceWithInternational.add(internationalFixedPrice);
	    }

	    m.addAttribute("totalPriceWithInternational", totalPriceWithInternational);
	    m.addAttribute("user", user);
	    m.addAttribute("order", order);
	    m.addAttribute("shipping", shippingAddress);
	    m.addAttribute("payment", payment);
	    m.addAttribute("billingAddress", billingAddress);
	    m.addAttribute("shoppingCart", shoppingCart);
	    m.addAttribute("cartItemList", cartItemList);
	    
	    return "OrderConfirmation";
	}


	@RequestMapping("/setDefaultShipping")
	public String setDefaultShippingAddress(@RequestParam("userShippingId") Long userShippingId, Principal principal,
			Model m) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);

		if (userShipping.getUser().getId() != user.getId()) {
			return "checkout";
		} else {
			shippingAddressService.addByUserShippingAddress(userShipping, shippingAddress);

			List<CartItems> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

			BillAddress billingAddress = new BillAddress();

			List<UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();

			m.addAttribute("EmptyShippingList", false);

			if (userPaymentList.size() == 0) {
				m.addAttribute("EmptyPaymentList", true);
			} else {
				m.addAttribute("EmptyPaymentList", false);
			}
			
			ShoppingCart shoppingCart = user.getShoppingCart();
			List<CartItems> cartItems = cartItemService.findByShoppingCart(shoppingCart);
			m.addAttribute("gameItemList", cartItems);

			List<Map.Entry<String, String>> sortedStates = INDConst.indiaStates.entrySet().stream()
					.sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

			m.addAttribute("stateList", sortedStates);
			m.addAttribute("userShippingList", userShippingList);
			m.addAttribute("userPaymentList", userPaymentList);
			m.addAttribute("user", user);
			m.addAttribute("shipping", shippingAddress);
			m.addAttribute("payment", payment);
			m.addAttribute("billingAddress", billingAddress);
			m.addAttribute("shoppingCart", user.getShoppingCart());
			m.addAttribute("cartItemList", cartItemList);

			return "checkout";
		}
	}

	@RequestMapping("/setDefaultPayment")
	public String setDefaultPayment(@RequestParam("userPaymentId") Long userPaymentId, Principal principal, Model m) {
		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(userPaymentId);
		UserBilling userBilling = userPayment.getUserBilling();

		if (userPayment.getUser().getId() != user.getId()) {
			return "checkout";
		} else {
			paymentService.addByUserPaymentDetails(userPayment, payment);

			List<CartItems> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

			billingAddressService.addByUserBilling(userBilling, billingAddress);

			List<UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();

			List<Map.Entry<String, String>> sortedStates = INDConst.indiaStates.entrySet().stream()
					.sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

			m.addAttribute("stateList", sortedStates);
			m.addAttribute("userShippingList", userShippingList);
			m.addAttribute("userPaymentList", userPaymentList);
			m.addAttribute("user", user);
			m.addAttribute("shipping", shippingAddress);
			m.addAttribute("payment", payment);
			m.addAttribute("billingAddress", billingAddress);
			m.addAttribute("shoppingCart", user.getShoppingCart());
			m.addAttribute("cartItemList", cartItemList);

			if (userShippingList.size() == 0) {
				m.addAttribute("EmptyShippingList", true);
			} else {
				m.addAttribute("EmptyShippingList", false);
			}
			
			ShoppingCart shoppingCart = user.getShoppingCart();
			List<CartItems> cartItems = cartItemService.findByShoppingCart(shoppingCart);
			m.addAttribute("gameItemList", cartItems);

			m.addAttribute("EmptyPaymentList", false);

			return "checkout";
		}
	}
}
