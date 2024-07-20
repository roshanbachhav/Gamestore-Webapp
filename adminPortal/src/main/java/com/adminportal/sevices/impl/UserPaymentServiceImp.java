package com.adminportal.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.Repository.UserPaymentRepository;
import com.adminportal.domain.UserPayment;
import com.adminportal.sevices.UserPaymentService;

@Service
public class UserPaymentServiceImp implements UserPaymentService {

	@Autowired
	private UserPaymentRepository userPaymentRepository;

	@Override
	public List<UserPayment> findAll() {
		List<UserPayment> userPayments = new ArrayList<>();
		userPaymentRepository.findAll().forEach(userPayments::add);
		return userPayments;
	}

}
