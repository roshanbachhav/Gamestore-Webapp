package com.gamestore.services;

import com.gamestore.domain.ShippingAddress;
import com.gamestore.domain.UserShipping;

public interface ShoppingAddressService {
	
	ShippingAddress addByUserShippingAddress(UserShipping userShipping , ShippingAddress shippingAddress);

}
