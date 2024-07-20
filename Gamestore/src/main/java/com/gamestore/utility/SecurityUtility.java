package com.gamestore.utility;

import java.security.SecureRandom;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {
    private static final String Gaming = "gtasix";

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(16, new SecureRandom(Gaming.getBytes()));
    }
    
    @Bean
    public static String randomPassword(){
        String sample = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();



        while(stringBuilder.length() < 18)
        {
            int index = (int) (rand.nextFloat()* sample.length());
            stringBuilder.append(sample.charAt(index));
        }
        String Sstr = stringBuilder.toString();
        return Sstr;
    }

}
