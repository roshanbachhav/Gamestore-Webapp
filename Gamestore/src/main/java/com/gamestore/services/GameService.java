package com.gamestore.services;

import java.util.List;

import com.gamestore.domain.Game;

public interface GameService {
	
	List<Game> findAll();
	
	Game findOne(Long id);
	
	List<Game> findGameByTitle(String keyword);

}
