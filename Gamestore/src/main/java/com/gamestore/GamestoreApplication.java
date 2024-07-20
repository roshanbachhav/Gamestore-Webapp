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

	@Override
	public void run(String... args) throws Exception {
//		User user1 = new User();
//		user1.setFirstName("Roshan");
//		user1.setLastName("Bachhav");
//		user1.setUsername("roshan123");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
//		user1.setEmail("bachhavr123@gmail.com");
//
//		Role role1 = new Role();
//		role1.setRoleId(1);
//		role1.setName("ROLE_USER");
//
//		Set<UserRole> userRoles = new HashSet<>();
//		userRoles.add(new UserRole(user1, role1));
//
//		userService.createUser(user1, userRoles);
		
		User user1 = new User();
	    user1.setFirstName("Roshan");
	    user1.setLastName("Bachhav");
	    user1.setUsername("roshan123");
	    user1.setPhone("1234567890");
	    user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
	    user1.setEmail("bachhavr123@gmail.com");

	    Role role1 = new Role();
	    role1.setRoleId(1);
	    role1.setName("ROLE_USER");

	    roleRepository.save(role1);

	    Set<UserRole> userRoles = new HashSet<>();
	    userRoles.add(new UserRole(user1, role1));

	    userService.createUser(user1, userRoles);
	}

}
