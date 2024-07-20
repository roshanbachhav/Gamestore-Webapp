package com.gamestore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamestore.domain.CartItems;
import com.gamestore.domain.Game;
import com.gamestore.domain.ShoppingCart;
import com.gamestore.domain.User;
import com.gamestore.services.CartItemService;
import com.gamestore.services.GameService;
import com.gamestore.services.ShoppingCartService;
import com.gamestore.services.UserService;

@Controller
@RequestMapping("/Cart")
public class CartController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private GameService gameService;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/myCart")
	public String myCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		ShoppingCart shoppingCart = user.getShoppingCart();
		List<CartItems> cartItems = cartItemService.findByShoppingCart(shoppingCart);
		model.addAttribute("gameItemList", cartItems);
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		boolean shoppingCartEmpty = cartItems.isEmpty();

		model.addAttribute("shoppingCartEmpty", shoppingCartEmpty);
		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("user", user);
		
		
		
		return "MyCart";
	}
	

	
	@RequestMapping("/addItems")
	public String addItemInCart(
			@ModelAttribute("game") Game game ,
			@ModelAttribute("quantity") String quantity ,
			Model m ,
			Principal principal
			) {
		
		
		User user = userService.findByUsername(principal.getName());
		game = gameService.findOne(game.getId());
		
		if(Integer.parseInt(quantity) > game.getStockAvailable()) {
			m.addAttribute("outOfStock",true);
	        return "redirect:/gameDetail?id=" + game.getId();
		}
		
		CartItems cartItem = cartItemService.addGameInCart(game , user , Integer.parseInt(quantity));
		m.addAttribute("added",true);
		m.addAttribute("cartItem",cartItem);
        return "redirect:/gameDetail?id=" + game.getId() + "&added=true";
				
	}
	
	@RequestMapping("/updateCartItem")
	public String updateCartQuantity(@ModelAttribute("id") Long id , @ModelAttribute("quantity") int quantity, Model m) {
		
		CartItems cartItem = cartItemService.findById(id);
		cartItem.setQuantity(quantity);
		cartItemService.updateCartItem(cartItem);
		return "redirect:/Cart/myCart?update=true";
	}

	
	@RequestMapping("/deleteCartItem")
	public String deleteUserCartItem(@RequestParam("id") Long id , Model m) {
		cartItemService.removeCartItemById(cartItemService.findById(id));
		return "redirect:/Cart/myCart?delete=true";
	}
	

}
