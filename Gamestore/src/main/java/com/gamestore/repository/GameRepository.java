package com.gamestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gamestore.domain.Game;

public interface GameRepository extends CrudRepository<Game, Long>{
	
	List<Game> findGameByTitle(String keyword);
	
}
