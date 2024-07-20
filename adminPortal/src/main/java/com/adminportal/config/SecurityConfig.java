package com.adminportal.config;

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

import com.adminportal.sevices.impl.UserSecurityService;
import com.adminportal.utility.SecurityUtility;

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
			"/newUser",
			"/forgetPassword",
			"login",
			"/fonts/**"
	};
	
//	 @Bean
//	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//	                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
//	                .requestMatchers(PUBLIC_MATCHERS).permitAll()
//	                .anyRequest().authenticated()
//	            )
//	            .formLogin(formLogin -> formLogin
//	                .loginPage("/admin/login")
//	                .defaultSuccessUrl("/admin/main", true)
//	                .failureUrl("/admin/login?error")
//	                .permitAll()
//	            )
//	            .logout(logout -> logout
//	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	                .logoutSuccessUrl("/?logout")
//	                .invalidateHttpSession(true)
//	                .deleteCookies("JSESSIONID")
//	                .permitAll()
//	            )
//	            .sessionManagement(sessionManagement -> sessionManagement
//	                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//	            )
//	            .rememberMe(rememberMe -> rememberMe
//	                .key("remember-me-key")
//	                .rememberMeServices(rememberMeServices())
//	            );
//
//	        return http.build();
//	    }
	
	 @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	        .authorizeHttpRequests((authorizeHttpRequests) ->
				authorizeHttpRequests
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		        .requestMatchers(PUBLIC_MATCHERS).permitAll()
		        .anyRequest().authenticated()
				)
	            .formLogin(formLogin ->
	                formLogin
	                    .failureUrl("/admin/login?error")
	                    .defaultSuccessUrl("/admin/main", true)
	                    .loginPage("/admin/login")
	                    .permitAll()
	            )
	            .logout(logout -> logout
		                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		                .logoutSuccessUrl("/?logout")
		                .invalidateHttpSession(true)
		                .deleteCookies("JSESSIONID")
		                .permitAll()
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
    ServletContextInitializer adminServletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.getSessionCookieConfig().setName("ADMINSESSIONID");
            }
        };
    }

}
