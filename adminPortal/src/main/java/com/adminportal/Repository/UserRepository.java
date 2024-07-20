package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
