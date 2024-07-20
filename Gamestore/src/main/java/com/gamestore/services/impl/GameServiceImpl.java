package com.gamestore.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamestore.domain.Game;
import com.gamestore.repository.GameRepository;
import com.gamestore.services.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	public List<Game> findAll() {
		return (List<Game>) gameRepository.findAll();
	}

	@Override
	public Game findOne(Long id) {
		Optional<Game> optionalGame = gameRepository.findById(id);
		return optionalGame.orElse(null);
	}
	
	
	@Override
	public List<Game> findGameByTitle(String keyword){
		List<Game> gameList = gameRepository.findGameByTitle(keyword);
		List<Game> activeGames = new ArrayList<>();
		
		for(Game game : gameList) {
			if(game.isActive()) {
				activeGames.add(game);
			}
		}
		
		return activeGames;
	}
	
}
