package com.gamestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamestore.domain.security.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
