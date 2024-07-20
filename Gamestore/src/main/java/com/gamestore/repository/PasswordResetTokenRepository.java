package com.gamestore.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gamestore.domain.User;
import com.gamestore.domain.security.passwordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<passwordResetToken, Long>{
	 
	passwordResetToken findByToken(String token);
	passwordResetToken findByUser(User user);
	 
	 Stream<passwordResetToken> findAllByExpiryDateLessThan(Date now);
	 
	 @Modifying
	 @Query("delete from passwordResetToken t where t.expiryDate <= ?1")
	 void deleteAllExpiredSince(Date now);

}
