package com.gamestore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestore.domain.UserShipping;
import com.gamestore.repository.UserShippingRepository;
import com.gamestore.services.UserShippingService;

@Service
public class UserShippingServiceImpl implements UserShippingService  {
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	public UserShipping findById(Long id) {
		return userShippingRepository.findById(id).orElse(null);
	}
	
	public void removeById(Long id) {
		userShippingRepository.deleteById(id);
	}

}
