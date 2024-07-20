package com.gamestore.services.impl;

import org.springframework.stereotype.Service;

import com.gamestore.domain.BillAddress;
import com.gamestore.domain.UserBilling;
import com.gamestore.services.BillingAddressService;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {
	
	public BillAddress addByUserBilling(UserBilling userBilling, BillAddress billingAddress) {
		billingAddress.setUserBillName(userBilling.getUserBillName());
		billingAddress.setStreet(userBilling.getStreet());
		billingAddress.setState(userBilling.getState());
		billingAddress.setCountry(userBilling.getCountry());
		billingAddress.setZipcode(userBilling.getZipcode());
		billingAddress.setCity(userBilling.getCity());
		
		return billingAddress;
	}
}
