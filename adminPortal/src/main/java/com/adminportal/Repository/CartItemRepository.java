package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.domain.CartItems;

@Repository
public interface CartItemRepository extends CrudRepository<CartItems, Long>{
	void deleteByGameId(Long gameId);
}
