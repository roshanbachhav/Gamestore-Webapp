package com.adminportal.Controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Game;
import com.adminportal.domain.User;
import com.adminportal.sevices.GameService;
import com.adminportal.sevices.UserServices;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	@Autowired
	private UserServices userService;
	
	@GetMapping("/addProducts")
	public String addproduct(Model m) {
		Game game = new Game();
		m.addAttribute("game" , game);
		return "addProducts";
	}
	
	
	@PostMapping("/addProducts")
	public String addproductPost(
				@ModelAttribute("game") Game game,
				HttpServletRequest request
			) {
		gameService.save(game);
		
		MultipartFile ImageOfGame = game.getImage();
		
		try {
			byte[] ImageByte = ImageOfGame.getBytes();
			String name = game.getId()+".jpg";
			BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/Games/"+name)));
			BOS.write(ImageByte);
			BOS.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:gameData";
	}
	
	@RequestMapping("/gameData")
	public String gameData(Model m) {
		List<Game> gameData = gameService.findAll();
		m.addAttribute("gameData", gameData);
		
		
		return "gameData";
	}

	@GetMapping("/delete")
	public String DeleteGame(Model m , @RequestParam Long gameId){
		gameService.deleteById(gameId);
		List<Game> gameData = gameService.findAll();
		m.addAttribute("gameData", gameData);
		return "redirect:/admin/game/gameData";
	}

	@RequestMapping("/product/update") 
	public String updateData(@RequestParam("id") Long id , Model m){
		Game game = gameService.findOneGame(id);
		m.addAttribute("game" , game);
		return "updateProduct";
	}
	
	@PostMapping("/product/update")
	public String updateProduct(@ModelAttribute("game") Game game , HttpServletRequest request) {
		
		gameService.save(game);
		
		MultipartFile productImage = game.getImage();
		
		if(!productImage.isEmpty()) {
			try {
				byte[] ImageByte = productImage.getBytes();
				String name = game.getId()+".jpg";
				
				Files.delete(Paths.get("src/main/resources/static/images/Games/" + name));
				
				BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/Games/"+name)));
				BOS.write(ImageByte);
				BOS.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/admin/game/gameData";
	}
}
