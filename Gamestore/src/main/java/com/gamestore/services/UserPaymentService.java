package com.gamestore.services;

import com.gamestore.domain.UserPayment;

public interface UserPaymentService {
	
	UserPayment findById(Long id);
	void removeById(Long id);
	
}
