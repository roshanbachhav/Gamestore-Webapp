package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.domain.Order;

@Repository
public interface OrderServiceRepository extends CrudRepository<Order, Long>{
	
	

}
