package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.domain.UserPayment;

@Repository
public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{
	

}
