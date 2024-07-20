package com.gamestore.services;

import java.util.Set;

import com.gamestore.domain.User;
import com.gamestore.domain.UserBilling;
import com.gamestore.domain.UserPayment;
import com.gamestore.domain.UserShipping;
import com.gamestore.domain.security.UserRole;
import com.gamestore.domain.security.passwordResetToken;

public interface UserService {
	passwordResetToken getPasswordResetToken(final String token);
	void createPasswordResetTokenForUser(final User user, final String token);
	User findByOne(Long id);
	User findByUsername(String username);
	User findByEmail(String email);
	User findByAddress(String address);
	User createUser(User user , Set<UserRole> userRole) throws Exception;
	User save(User user);
	void updateUser(User user);
	void updateUserBilling(UserPayment userPayment , UserBilling userBilling ,User user);
	void setUserDefaultOperation(Long paymentId ,User user);
	void addShippingDetails(UserShipping userShipping, User user);
}
