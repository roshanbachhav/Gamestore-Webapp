package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;


import com.adminportal.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
}
