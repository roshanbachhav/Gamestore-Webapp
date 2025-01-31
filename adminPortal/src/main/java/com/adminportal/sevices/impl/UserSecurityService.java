package com.adminportal.sevices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adminportal.Repository.UserRepository;
import com.adminportal.domain.User;

@Service
public class UserSecurityService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByUsername(username);
		
		if(null == user)
		{
			throw new UsernameNotFoundException("Username Not found");
		}
		
		return user;
	}

}