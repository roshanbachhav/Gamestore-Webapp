package com.gamestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamestore.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
