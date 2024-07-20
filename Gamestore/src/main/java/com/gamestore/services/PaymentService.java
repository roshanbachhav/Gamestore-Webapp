package com.gamestore.services;

import com.gamestore.domain.Payment;
import com.gamestore.domain.UserPayment;

public interface PaymentService {
	Payment addByUserPaymentDetails(UserPayment userPayment, Payment payment);
}
