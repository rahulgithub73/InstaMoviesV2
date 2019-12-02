package org.cod.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GamesController {
	
	@GetMapping("/ticTacToe")
	String ticTacToe() {
		return "games/tic-tac-toe";
	}
	
	@GetMapping("/canvasTetris")
	String canvasTetris() {
		return "games/canvas-tetris";
	}
	
	

}
