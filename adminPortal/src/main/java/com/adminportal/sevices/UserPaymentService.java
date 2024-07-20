package com.adminportal.sevices;

import java.util.List;

import com.adminportal.domain.UserPayment;

public interface UserPaymentService {

	List<UserPayment> findAll();
	
}
