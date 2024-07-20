package com.gamestore.services;

import com.gamestore.domain.BillAddress;
import com.gamestore.domain.UserBilling;

public interface BillingAddressService {

	BillAddress addByUserBilling(UserBilling userBilling, BillAddress billingAddress);

}
