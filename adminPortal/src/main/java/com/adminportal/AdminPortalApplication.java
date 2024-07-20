package com.adminportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adminportal.domain.User;
import com.adminportal.domain.security.Role;
import com.adminportal.domain.security.UserRole;
import com.adminportal.sevices.UserServices;
import com.adminportal.utility.SecurityUtility;

@SpringBootApplication
public class AdminPortalApplication implements CommandLineRunner{
	
	@Autowired
	private UserServices userService;

	public static void main(String[] args) {
		SpringApplication.run(AdminPortalApplication.class, args);
	}

}
