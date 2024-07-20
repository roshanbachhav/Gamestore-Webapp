package com.adminportal.sevices;

import java.util.List;
import com.adminportal.domain.Game;

public interface GameService {
	
	Game save(Game game);
		
	List<Game> findAll();

	void deleteById(Long id);

	Game findOneGame(Long id);
}
