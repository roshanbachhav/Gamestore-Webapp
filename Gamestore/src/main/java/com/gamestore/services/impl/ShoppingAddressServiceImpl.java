package com.gamestore.services.impl;

import org.springframework.stereotype.Service;

import com.gamestore.domain.ShippingAddress;
import com.gamestore.domain.UserShipping;
import com.gamestore.services.ShoppingAddressService;

@Service
public class ShoppingAddressServiceImpl implements ShoppingAddressService{
	
	public ShippingAddress addByUserShippingAddress(UserShipping userShipping , ShippingAddress shippingAddress) {
		shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet(userShipping.getUserShippingStreet());
		shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());
		shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
		
		return shippingAddress;
	}

}
