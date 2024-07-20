package com.gamestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamestore.domain.UserShipping;

public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {
	
}
