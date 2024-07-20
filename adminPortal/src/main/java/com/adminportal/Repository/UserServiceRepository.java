package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.domain.UserShipping;

@Repository
public interface UserServiceRepository extends CrudRepository<UserShipping, Long>{

}
