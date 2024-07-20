package com.gamestore.services.impl;

import org.springframework.stereotype.Service;

import com.gamestore.domain.Payment;
import com.gamestore.domain.UserPayment;
import com.gamestore.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	public Payment addByUserPaymentDetails(UserPayment userPayment, Payment payment) {
		payment.setType(userPayment.getType());
		payment.setCardName(userPayment.getCardName());
		payment.setCardNumber(userPayment.getCardNumber());
		payment.setExpiryMonth(userPayment.getExpiryMonth());
		payment.setExpiryYear(userPayment.getExpiryYear());
		payment.setCvv(userPayment.getCvv());
		payment.setHolderName(userPayment.getHolderName());
		
		return payment;
	}
}
