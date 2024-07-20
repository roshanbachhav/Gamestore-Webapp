package com.adminportal.sevices;

import java.util.List;
import java.util.Set;

import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;

public interface UserServices {
	User createUser(User user , Set<UserRole> userRole) throws Exception;
	User save(User user);
	void deleteById(Long id);
	List<User> findAll();
}