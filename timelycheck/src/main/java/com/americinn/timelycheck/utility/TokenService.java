package com.americinn.timelycheck.utility;

import com.americinn.timelycheck.entity.PasswordResetToken;
import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class TokenService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public PasswordResetToken generateToken(Staff user) {
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setStaff(user);
        token.setExpirationDate(LocalDateTime.now().plusHours(9)); // Set expiration time (e.g., 1 hour)
        return tokenRepository.save(token);
    }


    public boolean validateToken(String token) {
        PasswordResetToken passwordResetToken = tokenRepository.findByToken(token);
        if (passwordResetToken == null) {
            return false; // Token does not exist
        }
        return LocalDateTime.now().isBefore(passwordResetToken.getExpirationDate());
    }



}
