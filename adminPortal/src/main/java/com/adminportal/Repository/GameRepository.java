package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
	
}
