package com.gamestore;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gamestore.domain.User;
import com.gamestore.domain.security.Role;
import com.gamestore.domain.security.UserRole;
import com.gamestore.repository.RoleRepository;
import com.gamestore.services.UserService;
import com.gamestore.utility.SecurityUtility;

@SpringBootApplication
public class GamestoreApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(GamestoreApplication.class, args);
	}

}
