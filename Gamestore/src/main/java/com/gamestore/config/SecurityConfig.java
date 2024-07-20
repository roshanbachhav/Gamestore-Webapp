package com.gamestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import com.gamestore.services.impl.UserSecurityService;
import com.gamestore.utility.SecurityUtility;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}
	
	@Bean
    SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
	
	private static final String[] PUBLIC_MATCHERS = {
		    "/css/**",
		    "/js/**",
		    "/images/**",
		    "/",
		    "/Account",
		    "/newUser",
		    "/register",
		    "/forgetPassword",
		    "/login",
		    "/fonts/**",
		    "/shop",
		    "/verify"
		};

	
	
	@Bean
    SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
//            	.requestMatchers("/**").hasAuthority("ROLE_USER")
                .requestMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout")
                .deleteCookies("remember-me")
                .permitAll()
            )
            .rememberMe(rememberMe -> rememberMe
                .key("remember-me-key")
                .rememberMeServices(rememberMeServices())
            )
            .sessionManagement(sessionManagement -> sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );

        return http.build();
    }
	
	 @Bean
	 RememberMeServices rememberMeServices() {
	        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("remember-me-key", userSecurityService);
	        rememberMeServices.setAlwaysRemember(true);
	        rememberMeServices.setParameter("remember-me");
	        return rememberMeServices;
	    }


	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	
	 @Bean
	    ServletContextInitializer userServletContextInitializer() {
	        return new ServletContextInitializer() {
	            @Override
	            public void onStartup(ServletContext servletContext) throws ServletException {
	                servletContext.getSessionCookieConfig().setName("USERSESSIONID");
	            }
	        };
	    }

}
