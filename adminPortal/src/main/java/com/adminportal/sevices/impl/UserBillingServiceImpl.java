package com.adminportal.sevices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.Repository.UserBillingRepository;
import com.adminportal.domain.UserBilling;
import com.adminportal.sevices.UserBillingService;

@Service
public class UserBillingServiceImpl implements UserBillingService{

	@Autowired
	private UserBillingRepository userBillingRepository;
	
	@Override
	public List<UserBilling> findAll() {
		// TODO Auto-generated method stub
		return (List<UserBilling>)userBillingRepository.findAll();
	}
	
	

}
