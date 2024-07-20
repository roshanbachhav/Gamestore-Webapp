package com.gamestore.services;


import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamestore.domain.User;
import com.gamestore.domain.security.passwordResetToken;
import com.gamestore.repository.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public passwordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    public passwordResetToken findByUser(User user) {
        return passwordResetTokenRepository.findByUser(user);
    }

    public Stream<passwordResetToken> findAllByExpiryDateLessThan(Date now) {
        return passwordResetTokenRepository.findAllByExpiryDateLessThan(now);
    }

    @Transactional
    public void deleteAllExpiredSince(Date now) {
        passwordResetTokenRepository.deleteAllExpiredSince(now);
    }
    
    public void createPasswordResetTokenForUser(User user, String token) {
        passwordResetToken passToken = new passwordResetToken(token, user);
        passwordResetTokenRepository.save(passToken);
    }
}
