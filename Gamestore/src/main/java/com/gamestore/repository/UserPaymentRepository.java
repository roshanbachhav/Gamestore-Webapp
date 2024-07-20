package com.gamestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamestore.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{
	
}
