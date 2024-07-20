package com.gamestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamestore.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart , Long>{

}
