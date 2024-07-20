package com.gamestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestore.domain.UserPayment;
import com.gamestore.repository.UserPaymentRepository;
import com.gamestore.services.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{
	
	@Autowired
	private UserPaymentRepository userPaymentRepository; 
	
	public UserPayment findById(Long id) {
		return userPaymentRepository.findById(id).orElse(null);
	}
	
	@Override
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
}
