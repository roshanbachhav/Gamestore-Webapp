package com.adminportal.domain.security;

import java.util.Calendar;
import java.util.Date;

import com.adminportal.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class passwordResetToken {
    public static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class , fetch = FetchType.EAGER)
    @JoinColumn(nullable = false , name = "user_id")
    private User user;


    private Date expiryDate;

    public passwordResetToken(final String token , final User user){
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
    

    public passwordResetToken(){}
    
    private Date calculateExpiryDate(final int etim){
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE , etim);
        return new Date(cal.getTime().getTime());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public static int getExpiration() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "passwordResetToken [ id -> " + id + ", token -> " + token + ", user -> " + user + ", expiryDate -> " + expiryDate
                + " ] ";
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void updateToken(final String token){
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
}
