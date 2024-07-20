package com.adminportal.sevices.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.Repository.CartItemRepository;
import com.adminportal.Repository.GameRepository;
import com.adminportal.domain.Game;
import com.adminportal.sevices.GameService;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	public Game save(Game game) {
		return gameRepository.save(game);
	}

	public List<Game> findAll(){
		return (List<Game>) gameRepository.findAll();
	}
	
	@Transactional
	public void deleteById(Long id){
//		cartItemRepository.deleteByGameId(id);
		gameRepository.deleteById(id);
	}
	
	public Game findOneGame(Long id) {
		Optional<Game> game = gameRepository.findById(id); 
		return game.orElse(null);
	}

}
